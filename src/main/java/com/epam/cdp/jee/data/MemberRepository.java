package com.epam.cdp.jee.data;

import com.epam.cdp.jee.model.Member;

import javax.enterprise.context.ApplicationScoped;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by Vladislav on 26.09.2016.
 */
@ApplicationScoped
public class MemberRepository {

    private List<Member> members = new ArrayList<>();


    public List<Member> findAllOrderedByName() {

        return members;
    }

    public void addMember(Member member) {
       if (!members.contains(member))
           members.add(member);

    }
}
