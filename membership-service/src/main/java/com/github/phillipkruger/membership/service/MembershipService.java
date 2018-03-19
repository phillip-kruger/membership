package com.github.phillipkruger.membership.service;

import com.github.phillipkruger.membership.MembershipFilter;
import com.github.phillipkruger.membership.Membership;
import com.github.phillipkruger.membership.Type;
import io.leangen.graphql.annotations.GraphQLArgument;
import io.leangen.graphql.annotations.GraphQLMutation;
import io.leangen.graphql.annotations.GraphQLQuery;
import java.io.StringWriter;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.logging.Level;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.validation.constraints.NotNull;
import lombok.extern.java.Log;

@Log
@Stateless
public class MembershipService {
    
    @PersistenceContext(name="com.github.phillipkruger.membership")
    private EntityManager em;
    
    @GraphQLMutation(name = "createMembership")
    public Membership createMembership(@GraphQLArgument(name = "membership") @NotNull Membership membership){
        membership = em.merge(membership);
        log.log(Level.INFO, "Created membership [{0}]", membership);
        return membership;    
    }
    
    @GraphQLMutation(name = "deleteMembership")
    public Membership deleteMembership(@GraphQLArgument(name = "membershipId") int id){
        Membership membership = getMembership(id);
        if(membership!=null){
            em.remove(membership);
        }
        return membership;
    }
    
    @GraphQLQuery(name = "memberships") 
    public List<Membership> getAllMemberships() {
        return getAllMemberships(Optional.empty());
    }
    
    @GraphQLQuery(name = "memberships")
    public List<Membership> getAllMemberships(Optional<MembershipFilter> filter) {
        if(!filter.isPresent()){
            return em.createNamedQuery(Membership.QUERY_FIND_ALL, Membership.class).getResultList();
        }else{
            MembershipFilter membershipFilter = filter.get();
            return findMemberships(membershipFilter);
        }
    }
    
    @GraphQLQuery(name = "membership")
    public Membership getMembership(@GraphQLArgument(name = "membershipId") int id) {
        return em.find(Membership.class,id);
    }
    
    // TODO: Get this to work with CriteriaBuilder
    // @see http://www.baeldung.com/rest-search-language-spring-jpa-criteria
    private List<Membership> findMemberships(MembershipFilter filter){
        StringWriter queryWriter = new StringWriter();
        queryWriter.write("SELECT m FROM Membership m");
        
        Map<String,Object> params = new LinkedHashMap<>();
        
        if(filter.getType().isPresent()){
            queryWriter.write(SPACE + WHEREORAND[params.size()] + " m.type = :type");
            Type type = filter.getType().get();
            params.put(TYPE, type);
        }
        
        if(filter.getName().isPresent()){
            queryWriter.write(SPACE + WHEREORAND[params.size()] + " :name MEMBER OF m.owner.names");
            String name = filter.getName().get();
            params.put(NAME, name);
        }
        
        if(filter.getSurnameContains().isPresent()){
            queryWriter.write(SPACE + WHEREORAND[params.size()] + " m.owner.surname LIKE :surname");
            String surnameContains = filter.getSurnameContains().get();
            params.put(SURNAME, PERSENTAGE + surnameContains + PERSENTAGE);
        }
        
        String q = queryWriter.toString();
        
        log.log(Level.INFO, "JPA QUERY [{0}]", q);
        
        TypedQuery<Membership> typedQuery = em.createQuery(q, Membership.class);
        
        for(Map.Entry<String, Object> param : params.entrySet()){
            typedQuery.setParameter(param.getKey(), param.getValue());
        }
        
        List<Membership> memberships = typedQuery.getResultList();
            
        return memberships;
    }
    
    private static final String PERSENTAGE = "%";
    private static final String[] WHEREORAND = {"WHERE","AND","AND"};
    
    private static final String TYPE = "type";
    private static final String NAME = "name";
    private static final String SURNAME = "surname";
    private static final String SPACE = " ";
}
