package board.web.controller;

import board.web.dto.MemberFormDTO;
import board.web.dto.MemberDTO;
import board.web.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Slf4j
@RequiredArgsConstructor
@Controller
@RequestMapping("/TPW")
public class RegistryController {
    private final MemberService memberService;

    @GetMapping("/register/new")
    public String register(Model model) {
        model.addAttribute("memberForm", new MemberFormDTO());
        return "sign/register";
    }


    // Validator로 중복을 검증한다. 중복이 없으면 DTO를 통해 join을 진행한다.
    @PostMapping(value = "/register/new")
    public String create(@Valid @ModelAttribute(name = "memberForm") MemberFormDTO memberForm, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        log.info("회원가입 검증");
        if (bindingResult.hasErrors()) {
            return "sign/register";
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
            return "sign/register";
        }

        return "redirect:/TPW/login";
    }
}
