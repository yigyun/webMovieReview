package board.crud.Service;

import board.crud.domain.Member;
import board.crud.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class LoginService {

    @Autowired
    private final MemberRepository memberRepository;

    public boolean login(Member member) {
        //입력한 아이디와 비밀번호가 일치하는지 확인한다.
        Member findMember = memberRepository.findByMemberId(member.getMid());
        if(findMember != null){
            if(!findMember.getPassword().equals(member.getPassword())){
                return false;
            }
            return true;
        }
        return false;
    }
}
