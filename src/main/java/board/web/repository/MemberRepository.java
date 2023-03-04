package board.web.repository;

import board.crud.domain.member.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByUsername(String username);
    Optional<Member> findById(Long id);
    boolean existsByNick(String nick);
    boolean existsByUsername(String username);
}
