package board.crud.controller;

import board.crud.Service.LoginService;
import board.crud.Service.MemberService;
import board.crud.domain.Member;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@Slf4j
public class MemberController {
    @Autowired
    private final LoginService loginService;

    @Autowired
    private final MemberService memberService;

    @GetMapping("/register/new")
    public String register(Model model) {
        model.addAttribute("memberForm", new MemberForm());
        return "register";
    }

    @PostMapping(value = "/register/new")
    public String create(@Validated MemberForm memberForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "register";
        } else {
            Member member = new Member();
            member.setId(memberForm.getId());
            member.setPassword(memberForm.getPassword());
            member.setName(memberForm.getName());
            member.setNick(memberForm.getNick());

            memberService.join(member);
            return "redirect:/";
        }
    }

    @RequestMapping("/")
    public String sign(){
        log.info("sign controller");
        return "sign";
    }

    @PostMapping("/")
    public String loginId(@ModelAttribute Member member){
        if(loginService.login(member)){
            return "mainpage";
        }
        return "redirect:/";
    }
}
