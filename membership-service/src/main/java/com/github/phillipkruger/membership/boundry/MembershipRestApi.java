package com.github.phillipkruger.membership.boundry;

import com.github.phillipkruger.membership.service.MembershipService;
import javax.ejb.EJB;
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
public class MembershipRestApi {
    
    @EJB
    private MembershipService membershipService;
    
    @GET @Path("ping")
    @Produces(MediaType.TEXT_PLAIN)
    public String ping(){
        log.severe("ping->pong");
        return "pong";
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void joinMember(@NotNull Membership membership) {
        membershipService.createMembership(membership);
    }
    
    
    
}