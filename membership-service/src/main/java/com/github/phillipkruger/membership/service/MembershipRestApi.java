package com.github.phillipkruger.membership.service;

import io.swagger.annotations.Api;
import javax.validation.constraints.NotNull;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import lombok.extern.java.Log;

/**
 * REST Api for Membership service
 * @author Phillip Kruger (phillip.kruger@phillip-kruger.com)
 */
@Log
@Path("/")
@Api(value = "/")
public class MembershipRestApi {
    
    @GET @Path("/ping")
    @Produces(MediaType.TEXT_PLAIN)
    public String ping(){
        log.severe("ping->pong");
        return "pong";
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void joinMember(@NotNull Membership membership) {
        
        log.info(">>>>>>>>> " + membership);
    }
    
    
    
}