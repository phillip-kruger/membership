package com.github.phillipkruger.membership.rest;

import com.github.phillipkruger.membership.Membership;
import com.github.phillipkruger.membership.Person;
import com.github.phillipkruger.membership.service.MembershipService;
import java.util.List;
import javax.inject.Inject;
import javax.validation.constraints.NotNull;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import lombok.extern.java.Log;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

/**
 * REST Api for Membership service
 * @author Phillip Kruger (phillip.kruger@phillip-kruger.com)
 */
@Log
@Path("/")
@Consumes(MediaType.APPLICATION_JSON) @Produces(MediaType.APPLICATION_JSON)
@Tag(name = "Membership service",description = "Membership management")
public class MembershipRestApi {
    
    @Inject
    private MembershipService membershipService;
    
    @GET
    @Operation(description = "Get all memberships")
    public List<Membership> getAllMemberships(){
        return membershipService.getAllMemberships();
    }
    
    @GET @Path("/surname/{id}")
    @Operation(description = "Get all surnames")
    public String getMemberSurname(@NotNull @PathParam(value = "id") int id){
        return membershipService.getMembership(id).getOwner().getSurname();
    }
    
    @GET @Path("{id}")
    @Operation(description = "Get a certain membership")
    public Membership getMembership(@NotNull @PathParam(value = "id") int id){
        return membershipService.getMembership(id);
    }
    
    @GET @Path("/person/{id}")
    @Operation(description = "Get a person")
    public Person getPerson(@NotNull @PathParam(value = "id") int id){
        return membershipService.getPerson(id);
    }
    
    @POST
    @Operation(description = "Create a new membership")
    public void joinMember(@NotNull Membership membership) {
        membershipService.createMembership(membership);
    }
    
    @DELETE @Path("{id}")
    @Operation(description = "Create a existing membership")
    public void deleteMember(@NotNull @PathParam(value = "id") int id){
        membershipService.deleteMembership(id);
    }
    
}