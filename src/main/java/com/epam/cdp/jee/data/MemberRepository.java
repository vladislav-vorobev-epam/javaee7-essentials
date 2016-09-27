package com.epam.cdp.jee.data;

import com.epam.cdp.jee.model.Member;
import com.epam.cdp.jee.model.Member_;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

/**
 * Created by Vladislav on 26.09.2016.
 */
@ApplicationScoped
public class MemberRepository {


    @Inject
    private EntityManager em;


    public List<Member> findAllOrderedByName() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Member> criteria = cb.createQuery(Member.class);
        Root<Member> member = criteria.from(Member.class);
        // Swap criteria statements if you would like to try out type-safe criteria queries, a new
        // feature in JPA 2.0
        criteria.select(member).orderBy(cb.asc(member.get(Member_.name)));
        //criteria.select(member).orderBy(cb.asc(member.get("name")));
        return em.createQuery(criteria).getResultList();
    }

    public void addMember(Member member) {
      em.persist(member);

    }
}
