package com.github.phillipkruger.membership.service;

import com.github.phillipkruger.membership.Membership;
import java.util.List;
import java.util.logging.Level;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.constraints.NotNull;
import lombok.extern.java.Log;

@Log
@Stateless
public class MembershipService {
    
    @PersistenceContext(name="com.github.phillipkruger.membership")
    private EntityManager em;
    
    public Membership createMembership(@NotNull Membership membership){
        membership = em.merge(membership);
        log.log(Level.INFO, "Created membership [{0}]", membership);
        return membership;    
    }
    
    public List<Membership> getAllMembership() {
        return em.createNamedQuery(Membership.QUERY_FIND_ALL, Membership.class).getResultList();
    }

    public Membership getMembership(int id) {
        return em.find(Membership.class,id);
    }
}
