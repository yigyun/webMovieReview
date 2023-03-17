package board.web.controller;

import board.crud.domain.member.Member;
import board.crud.dto.Login;
import board.web.repository.MemberRepository;
import board.web.service.MovieApiService;

import board.web.security.session.SessionManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/TPW")
public class MainController {
    private final MovieApiService movieApiService;
    private final MemberRepository memberRepository;
    private final SessionManager sessionManager;

    @GetMapping("/mainpage")
    public String mainpage(@Login Member loginMember, Model model) {
        if(loginMember == null){
            return "sign";
        }
        model.addAttribute("movieList", movieApiService.getInfo());
        model.addAttribute("member", loginMember);
        return "/contents/mainpage";
    }

    @GetMapping("/movie/list")
    public String movieList(Model model){
        model.addAttribute("movieList", movieApiService.getInfo());
        return "/contents/movie";
    }

    @GetMapping("/details/movie/{id}")
    public String movieDetails(@Login Member loginMember, @PathVariable("id") Long id, Model model) {
        if(loginMember == null){
            return "sign";
        }
        model.addAttribute("movie", movieApiService.getMovieById(id));
        model.addAttribute("member", loginMember);
        return "/contents/movie_details";
    }
}
