package board.crud.member;

import board.crud.dto.MemberDTO;
import board.crud.member.Member;
import board.crud.member.MemberForm;
import board.crud.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class MemberValidator implements Validator {

    @Autowired
    private MemberRepository memberRepository;

    @Override
    public boolean supports(Class<?> clazz) {
        return MemberForm.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        MemberForm memberForm = (MemberForm) target;
        if(memberRepository.existsByNick(memberForm.getNick()))
            errors.rejectValue("nick", "key", "이미 닉네임이 존재합니다.");
        else if(memberRepository.existsByUsername(memberForm.getUsername()))
            errors.rejectValue("username", "key", "이미 아이디가 존재합니다.");
    }
}
