package board.crud.repository;

import board.crud.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class MemberRepository {

    private final EntityManager em;

    public void save(Member member) {
        em.persist(member);
    }

    //login시 사용할 것이다. id에 해당하는 하나의 Member를 가져온다.
    public Member findByMemberId(Long id) {
        return em.find(Member.class, id);
    }

    public List<Member> findByNick(String nick) {
        return em.createQuery("select m from Member m where m.nick = :nick", Member.class)
                .setParameter("nick", nick)
                .getResultList();
    }
}
