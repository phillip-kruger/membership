package com.github.phillipkruger.membership.graphql;

import com.github.phillipkruger.membership.Membership;
import com.github.phillipkruger.membership.MembershipFilter;
import com.github.phillipkruger.membership.Person;
import com.github.phillipkruger.membership.service.MembershipService;

import java.util.List;
import java.util.Optional;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.validation.constraints.NotNull;
import lombok.extern.java.Log;
import org.eclipse.microprofile.graphql.Argument;
import org.eclipse.microprofile.graphql.Mutation;
import org.eclipse.microprofile.graphql.Query;

/**
 * GraphQL Api for Membership service
 * @author Phillip Kruger (phillip.kruger@phillip-kruger.com)
 */
@Log
@RequestScoped
public class MembershipGraphQLApi {
    
    @Inject
    private MembershipService membershipService;
    
    @Query("memberships") 
    public List<Membership> getAllMemberships() {
        return getAllMemberships(Optional.empty());
    }
    
    @Query("memberships")
    public List<Membership> getAllMemberships(Optional<MembershipFilter> filter){
        return getAllMemberships(filter,Optional.empty(),Optional.empty());
    }
    
    @Query("memberships")
    public List<Membership> getAllMemberships(@Argument("skip") Optional<Integer> skip,
                                            @Argument("first") Optional<Integer> first){
        return getAllMemberships(Optional.empty(),skip,first);
    }
    
    @Query("memberships")
    public List<Membership> getAllMemberships(Optional<MembershipFilter> filter,
                                @Argument("skip") Optional<Integer> skip,
                                @Argument("first") Optional<Integer> first) {
        return membershipService.getAllMemberships(filter, skip, first);   
    }
    
    @Query("people")
    public List<Person> getAllPeople(){
        return membershipService.getAllPeople();   
    }
    
    @Query("membership")
    public Membership getMembership(@Argument("membershipId") int id) {
        return membershipService.getMembership(id);
    }
    
    @Query("person")
    public Person getPerson(@Argument("personId") int id){
        return membershipService.getPerson(id);   
    }
    
    @Mutation("createMembership")
    public Membership createMembership(@Argument("membership") @NotNull Membership membership){
        return membershipService.createMembership(membership);    
    }
    
    @Mutation("deleteMembership")
    public Membership deleteMembership(@Argument("membershipId") int id){
        return membershipService.deleteMembership(id);    
    }
    
}