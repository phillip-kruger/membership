package com.github.phillipkruger.membership.rest;

import com.github.phillipkruger.membership.Membership;
import com.github.phillipkruger.membership.service.MembershipService;
import java.util.List;
import javax.inject.Inject;
import javax.validation.constraints.NotNull;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
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
    
    @Inject
    private MembershipService membershipService;
    
    @GET @Path("ping")
    @Produces(MediaType.TEXT_PLAIN)
    public String ping(){
        log.severe("ping->pong");
        return "pong";
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Membership> getAllMemberships(){
        return membershipService.getAllMemberships();
    }
    
    @GET @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Membership getMembership(@NotNull @PathParam(value = "id") int id){
        return membershipService.getMembership(id);
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void joinMember(@NotNull Membership membership) {
        membershipService.createMembership(membership);
    }
    
    
    
}