package board.crud.repository;

import board.crud.member.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByUsername(String username);
    Optional<Member> findByMid(Long mid);
    boolean existsByNick(String nick);
    boolean existsByUsername(String username);
}
