package board.web.controller;
import board.web.security.SessionConst;
import board.crud.domain.member.Member;
import board.crud.dto.LoginFormDTO;
import board.web.security.LoginService;
import board.web.service.MemberService;
import board.web.security.session.SessionManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@RequiredArgsConstructor
@Slf4j
@RequestMapping("/TPW")
@Controller
public class MemberController {
    private final MemberService memberService;
    private final LoginService loginService;
    private final SessionManager sessionManager;

    @GetMapping("/login")
    public String sign(@ModelAttribute("loginForm")LoginFormDTO form){
        log.info("sign controller");
        return "sign/sign";
    }

    @PostMapping("/login")
    public String signIn(@Valid @ModelAttribute LoginFormDTO form, BindingResult bindingResult,
                         @RequestParam(defaultValue = "/TPW/mainpage") String redirectURL,
                         HttpServletRequest request){

        log.info("sign in");
        if(bindingResult.hasErrors())
            return "sign/sign";

        Member loginMember = loginService.login(form.getUsername(), form.getPassword());

        if(loginMember == null){
            log.info("아이디 비밀번호 오류");
            bindingResult.reject("loginFail", "아이디 또는 비밀번호가 맞지 않습니다.");
            return "sign/sign";
        }
        // 세션이 없으면 만들고 있으면 있는거 반환.
        HttpSession session = request.getSession();
        // 세션에 정보 보관.
        session.setAttribute(SessionConst.LOGIN_MEMBER, loginMember);

        return "redirect:" + redirectURL;
    }

    @PostMapping("/logout")
    public String logout(HttpServletRequest request){
        HttpSession session = request.getSession(false);
        if(session != null){
            session.invalidate();
        }
        return "redirect:/TPW/login";
    }

    private void expireCookie(HttpServletResponse response, String cookieName) {
        Cookie cookie = new Cookie(cookieName, null);
        cookie.setMaxAge(0);
        response.addCookie(cookie);
    }
}
