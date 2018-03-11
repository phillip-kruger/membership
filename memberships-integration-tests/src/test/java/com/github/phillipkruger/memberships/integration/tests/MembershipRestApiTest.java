package com.github.phillipkruger.memberships.integration.tests;

import com.github.phillipkruger.membership.Membership;
import static io.restassured.RestAssured.*;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import java.io.File;
import java.io.FileFilter;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import lombok.extern.java.Log;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

@Log
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class MembershipRestApiTest {
    
    private static final String CONTENT_ROOT = "/membership-service/rest/";
    
    @Test
    public void a_ping(){

        Response r = 
                given().
                    contentType("text/plain").
                when().
                    get(CONTENT_ROOT + "ping").
                thenReturn();
                
        ResponseBody body = r.getBody();
        String s = body.asString();
        
        Assert.assertEquals("pong", s);
        
    }
    
    @Test
    public void b_testJoinMember(){
        
        List<Membership> memberships = createMemberships();
        for(Membership membership : memberships){
        
            int statusCode = given().
                contentType("application/json").
                body(membership).
                when().
                post(CONTENT_ROOT).getStatusCode();
        
            Assert.assertEquals(204, statusCode);
        }
    }
    
    @Test
    public void c_testGetAllMembers(){
        
        Response allMembershipsResponse = given().
                contentType("application/json").
                when().
                get(CONTENT_ROOT).
                andReturn();
        String allMembershipsJson = allMembershipsResponse.asString();
        
        log.severe(allMembershipsJson);
        
    }
    
    private List<Membership> createMemberships(){
        List<Membership> memberships = new ArrayList<>();
        
        for(File f:getTestFiles()){
            memberships.add(readFromFile(f));
        }
        
        return memberships;
        
    }
    
    private List<File> getTestFiles(){
        URL testDataFolderURL = this.getClass().getResource("/memberships/");
        File testDataFolder = new File(testDataFolderURL.getFile());
        
        File[] testFiles = testDataFolder.listFiles(new FileFilter() {
            @Override
            public boolean accept(File file) {
                return file.isFile() && file.getName().endsWith(".xml");
            }
        });
        
        List<File> files = new ArrayList<>(Arrays.asList(testFiles));
        
        Collections.sort(files, new Comparator<File>(){
            @Override
            public int compare(File t, File t1) {
                return t.getName().compareTo(t1.getName());
            }
        });
        
        return files;
    }
    
    private Membership readFromFile(File file){
        
        try {
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            Object o = unmarshaller.unmarshal(file);
            return (Membership)o;
        } catch (JAXBException ex) {
            throw new RuntimeException(ex);
        }
    }
    
    private static JAXBContext jaxbContext;
    static{
        try {
            jaxbContext = JAXBContext.newInstance(Membership.class);
        } catch (JAXBException ex) {
            throw new RuntimeException(ex);
        }
    }
    
}
