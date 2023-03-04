package board.web.security;

import board.crud.domain.member.Member;
import board.web.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class LoginService {

    private final MemberRepository memberRepository;

    /**
     * @return null 로그인 실패
     */
    public Member login(String username, String password) {
        return memberRepository.findByUsername(username)
                .filter(m -> m.getPassword().equals(password))
                .orElse(null);
    }
}
