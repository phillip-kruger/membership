package com.github.phillipkruger.membership.rest;

import com.github.phillipkruger.membership.Membership;
import com.github.phillipkruger.membership.service.MembershipService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
@Consumes(MediaType.APPLICATION_JSON) @Produces(MediaType.APPLICATION_JSON)
@Api(value = "Membership service")
public class MembershipRestApi {
    
    @Inject
    private MembershipService membershipService;
    
    @GET
    @ApiOperation(value = "Retrieve all memberships", notes = "This will return a list of membership objects to the client",response = Membership.class)
    public List<Membership> getAllMemberships(){
        return membershipService.getAllMemberships();
    }
    
    @GET @Path("{id}")
    @ApiOperation(value = "Retrieve a membership", notes = "This will return a certain membership objects to the client",response = Membership.class)
    public Membership getMembership(@NotNull @PathParam(value = "id") int id){
        return membershipService.getMembership(id);
    }
    
    @POST
    @ApiOperation(value = "Create a membership", notes = "This will create a membership ")
    public void joinMember(@NotNull Membership membership) {
        membershipService.createMembership(membership);
    }
    
}