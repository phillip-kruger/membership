package com.github.phillipkruger.membership.graphql;

import com.github.phillipkruger.membership.Membership;
import com.github.phillipkruger.membership.MembershipFilter;
import com.github.phillipkruger.membership.Person;
import com.github.phillipkruger.membership.service.MembershipService;
import io.leangen.graphql.annotations.GraphQLArgument;
import io.leangen.graphql.annotations.GraphQLMutation;
import io.leangen.graphql.annotations.GraphQLQuery;
import java.util.List;
import java.util.Optional;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.validation.constraints.NotNull;
import lombok.extern.java.Log;

/**
 * GraphQL Api for Membership service
 * @author Phillip Kruger (phillip.kruger@phillip-kruger.com)
 */
@Log
@RequestScoped
public class MembershipGraphQLApi {
    
    @Inject
    private MembershipService membershipService;
    
    @GraphQLQuery(name = "memberships") 
    public List<Membership> getAllMemberships() {
        return getAllMemberships(Optional.empty());
    }
    
    @GraphQLQuery(name = "memberships")
    public List<Membership> getAllMemberships(Optional<MembershipFilter> filter){
        return getAllMemberships(filter,Optional.empty(),Optional.empty());
    }
    
    @GraphQLQuery(name = "memberships")
    public List<Membership> getAllMemberships(@GraphQLArgument(name = "skip") Optional<Integer> skip,
                                            @GraphQLArgument(name = "first") Optional<Integer> first){
        return getAllMemberships(Optional.empty(),skip,first);
    }
    
    @GraphQLQuery(name = "memberships")
    public List<Membership> getAllMemberships(Optional<MembershipFilter> filter,
                                @GraphQLArgument(name = "skip") Optional<Integer> skip,
                                @GraphQLArgument(name = "first") Optional<Integer> first) {
        return membershipService.getAllMemberships(filter, skip, first);   
    }
    
    @GraphQLQuery(name = "people")
    public List<Person> getAllPeople(){
        return membershipService.getAllPeople();   
    }
    
    @GraphQLQuery(name = "membership")
    public Membership getMembership(@GraphQLArgument(name = "membershipId") int id) {
        return membershipService.getMembership(id);
    }
    
    @GraphQLQuery(name = "person")
    public Person getPerson(@GraphQLArgument(name = "personId") int id){
        return membershipService.getPerson(id);   
    }
    
    @GraphQLMutation(name = "createMembership")
    public Membership createMembership(@GraphQLArgument(name = "membership") @NotNull Membership membership){
        return membershipService.createMembership(membership);    
    }
    
    @GraphQLMutation(name = "deleteMembership")
    public Membership deleteMembership(@GraphQLArgument(name = "membershipId") int id){
        return membershipService.deleteMembership(id);    
    }
    
}