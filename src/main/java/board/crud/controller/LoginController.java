package board.crud.controller;


import board.crud.Service.LoginService;
import board.crud.domain.Member;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
@RequiredArgsConstructor
public class LoginController {

    @Autowired
    private final LoginService loginService;

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
