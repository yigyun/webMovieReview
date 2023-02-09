package board.crud.controller;


import board.crud.Service.MemberService;
import board.crud.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class RegisterController {
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
            member.setNickName(memberForm.getNickName());

            memberService.join(member);
            return "redirect:/";
        }
    }
}
