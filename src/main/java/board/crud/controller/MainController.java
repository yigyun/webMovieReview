package board.crud.controller;

import board.crud.Service.MovieApiService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
@Slf4j
@RequiredArgsConstructor
public class MainController {
    private final MovieApiService movieApiService;

    @GetMapping("/mainpage")
    public String mainpage(Model model) {
        model.addAttribute("movieList", movieApiService.getInfo());
        return "mainpage";
    }
}

