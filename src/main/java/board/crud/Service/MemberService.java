package board.crud.Service;

import board.crud.domain.Member;
import board.crud.dto.MemberDTO;
import board.crud.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Slf4j
public class MemberService {
    private final MemberRepository memberRepository;

    @Transactional
    public Long join(MemberDTO memberDTO) {
        Member member = memberDTO.toEntity();
        validateDuplicateMember(member);
        memberRepository.save(member);
        return member.getMid();
    }

    private void validateDuplicateMember(Member member) {
        List<Member> findMembers = memberRepository.findByNick(member.getNick());
        if (!findMembers.isEmpty())
            throw new IllegalStateException("이미 존재하는 닉네임입니다.");
    }
}
