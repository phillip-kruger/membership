package com.github.phillipkruger.membership.service;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import io.restassured.response.Response;
import lombok.extern.java.Log;
import static org.hamcrest.Matchers.*;
import org.junit.Test;

@Log
public class MembershipRestApiTestIT {
    
    @Test
    public void ping(){
        
        Response r = 
                given().
                    contentType("text/plain").
                when().
                    get("/api/ping").
                thenReturn();
                
        String s = r.getBody().as(String.class);
        
        log.severe(s);
    }
    
    @Test
    public void testJoinMember(){
        
        Membership membership = new Membership();
        

        given().
            contentType("application/json").
            body(membership).
        when().
            post("/api");
        
    }
                
}
