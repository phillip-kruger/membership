package com.github.phillipkruger.membership.service;

import com.github.phillipkruger.membership.boundry.Type;
import com.github.phillipkruger.membership.boundry.Membership;
import com.github.phillipkruger.membership.boundry.Person;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import lombok.extern.java.Log;
import static org.hamcrest.Matchers.*;
import org.junit.Assert;
import org.junit.Test;

@Log
public class MembershipRestApiTestIT {
    
    private static final String CONTENT_ROOT = "/membership-service/api/";
    
    @Test
    public void ping(){
        
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
    public void testJoinMember(){
        
        given().
            contentType("application/json").
            body(createMembership()).
        when().
            post(CONTENT_ROOT);
        
    }
    
    private Membership createMembership(){
        Membership membership = new Membership();
        
        membership.setOwner(createPerson());
        membership.setType(Type.FULL);
        return membership;
    }
    
    private Person createPerson(){
        Person person = new Person();
        person.addName("Natus");
        person.addName("Phillip");
        person.setSurname("Kruger");
        
        return person;
    }
    
}
