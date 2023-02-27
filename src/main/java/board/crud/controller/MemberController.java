package board.crud.controller;

import board.crud.member.MemberValidator;
import board.crud.member.MemberForm;
import board.crud.service.MemberService;
import board.crud.dto.MemberDTO;
import board.crud.service.UserSecurityService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/TPW")
public class MemberController {

    private final UserSecurityService loginService;
    private final MemberService memberService;
    private final MemberValidator memberValidator;

    @GetMapping("/register/new")
    public String register(Model model) {
        model.addAttribute("memberForm", new MemberForm());
        return "register";
    }

    // Validator로 중복을 검증한다. 중복이 없으면 DTO를 통해 join을 진행한다.
    @PostMapping(value = "/register/new")
    public String create(@Valid @ModelAttribute MemberForm memberForm, BindingResult bindingResult) {
        memberValidator.validate(memberForm, bindingResult);
        if (bindingResult.hasErrors()) {
            return "register";
        }

        MemberDTO memberDTO = MemberDTO.builder().
                name(memberForm.getName())
                .nick(memberForm.getNick())
                .password(memberForm.getPassword())
                .username(memberForm.getUsername())
                .build();

        try {
            memberService.join(memberDTO);
        } catch (Exception e){
            e.printStackTrace();
            bindingResult.reject("회원가입 실패", e.getMessage());
            return "register";
        }

        return "redirect:/TPW/login";
    }

    @GetMapping("/login")
    public String sign(){
        log.info("sign controller");
        return "sign";
    }

  /*  @PostMapping("/login")
    public String loginId(@ModelAttribute MemberDTO memberDTO){
        if(true){
            return "mainpage";
        }
        return "redirect:/";
    }*/
}
