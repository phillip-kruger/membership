package com.github.phillipkruger.membership.service;

import com.github.phillipkruger.membership.MembershipFilter;
import com.github.phillipkruger.membership.Membership;
import com.github.phillipkruger.membership.Type;
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
    
    @PersistenceContext(name = "MembershipDS")
    private EntityManager em;
    
    public Membership createMembership(@NotNull Membership membership){
        membership = em.merge(membership);
        log.log(Level.INFO, "Created membership [{0}]", membership);
        return membership;    
    }
    
    public Membership deleteMembership(int id){
        Membership membership = getMembership(id);
        if(membership!=null){
            em.remove(membership);
        }
        return membership;
    }
    
    public List<Membership> getAllMemberships() {
        return getAllMemberships(Optional.empty(),Optional.empty(),Optional.empty());
    }
    
    public List<Membership> getAllMemberships(Optional<MembershipFilter> filter,
                                Optional<Integer> skip,
                                Optional<Integer> first) {
        if(!filter.isPresent()){
            return allMemberships(skip,first);
        }else {
            MembershipFilter membershipFilter = filter.get();
            return findMemberships(membershipFilter,skip,first);
        }
    }
    
    public Membership getMembership(int id) {
        return em.find(Membership.class,id);
    }
    
    private List<Membership> allMemberships(Optional<Integer> skip,Optional<Integer> first){
        TypedQuery<Membership> query = em.createNamedQuery(Membership.QUERY_FIND_ALL, Membership.class);
        setPaging(query,skip,first);
        return query.getResultList();
    }
    
    // TODO: Get this to work with CriteriaBuilder
    // @see http://www.baeldung.com/rest-search-language-spring-jpa-criteria
    private List<Membership> findMemberships(MembershipFilter filter,Optional<Integer> skip,Optional<Integer> first){
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
        
        TypedQuery<Membership> typedQuery = em.createQuery(q, Membership.class);
        
        for(Map.Entry<String, Object> param : params.entrySet()){
            typedQuery.setParameter(param.getKey(), param.getValue());
        }
        
        setPaging(typedQuery,skip,first);
        
        List<Membership> memberships = typedQuery.getResultList();
            
        return memberships;
    }
    
    private void setPaging(TypedQuery<Membership> typedQuery,Optional<Integer> optionalPageNumber,Optional<Integer> optionalPageSize){
        
        int pageNumber = 1;
        int pageSize = Integer.MAX_VALUE;
        
        
        if(optionalPageNumber.isPresent())pageNumber = optionalPageNumber.get();
        if(optionalPageSize.isPresent())pageSize = optionalPageSize.get();    
        
        typedQuery.setFirstResult((pageNumber-1) * pageSize); 
        typedQuery.setMaxResults(pageSize);
        
    }
    
    private static final String PERSENTAGE = "%";
    private static final String[] WHEREORAND = {"WHERE","AND","AND"};
    
    private static final String TYPE = "type";
    private static final String NAME = "name";
    private static final String SURNAME = "surname";
    private static final String SPACE = " ";
}
