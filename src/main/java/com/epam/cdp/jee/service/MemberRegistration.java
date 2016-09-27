package com.epam.cdp.jee.service;

        import com.epam.cdp.jee.data.MemberRepository;
        import com.epam.cdp.jee.model.Member;

        import javax.ejb.Stateless;
        import javax.enterprise.event.Event;
        import javax.inject.Inject;
        import java.util.logging.Logger;

// The @Stateless annotation eliminates the need for manual transaction demarcation
@Stateless
public class MemberRegistration {

    @Inject
    private Logger log;

    @Inject
    private MemberRepository repo;

    @Inject
    private Event<Member> memberEventSrc;

    public void register(Member member) throws Exception {
        log.info("Registering " + member.getName());
        repo.addMember(member);
        memberEventSrc.fire(member);

        JMSService jmsService = new JMSService();
        jmsService.sendMessage("Registering " + member.getName());
    }
}