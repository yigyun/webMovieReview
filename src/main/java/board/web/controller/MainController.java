package board.web.controller;

import board.crud.domain.member.Member;
import board.web.dto.Login;
import board.web.repository.MemberRepository;
import board.web.service.MovieApiService;
import board.web.service.TvApiService;
import board.web.session.SessionManager;
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
    private final TvApiService tvApiService;

    @GetMapping("/mainpage")
    public String mainpage(@Login Member loginMember, Model model) {
        if(loginMember == null){
            return "sign";
        }
        model.addAttribute("movieList", movieApiService.getInfo());
        model.addAttribute("tvList", tvApiService.getInfo());
        model.addAttribute("member", loginMember);

        return "/contents/mainpage";
    }

    @GetMapping("/details/movie/{title}")
    public String movieDetails(@Login Member loginMember, @PathVariable String title, Model model) {
        if(loginMember == null){
            return "sign";
        }
        model.addAttribute("movie", movieApiService.getDetail(title));
        model.addAttribute("member", loginMember);
        return "/contents/movie_details";
    }

    @GetMapping("/details/tv/{name}")
    public String tvDetails(@Login Member loginMember, @PathVariable String name, Model model) {
        if(loginMember == null){
            return "sign";
        }
        model.addAttribute("tv", tvApiService.getDetail(name));
        model.addAttribute("member", loginMember);
        return "/contents/tv_details";
    }
}
