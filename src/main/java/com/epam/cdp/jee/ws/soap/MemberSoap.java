package com.epam.cdp.jee.ws.soap;

import com.epam.cdp.jee.data.MemberRepository;
import com.epam.cdp.jee.model.Member;

import javax.inject.Inject;
import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.xml.ws.WebEndpoint;
import java.util.List;

/**
 * Created by Vladislav on 27.09.2016.
 */
@WebService
public class MemberSoap {
    @Inject
    private MemberRepository repository;

    @WebMethod
    @WebEndpoint(name = "MembersWebService")
    public List<Member> members() {
        return repository.findAllOrderedByName();
    }
}
