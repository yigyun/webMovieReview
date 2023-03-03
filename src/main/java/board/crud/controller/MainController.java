package board.crud.controller;

import board.crud.entity.Rating;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class MainController {

    @GetMapping("/mainpage")
    public String mainpage(Model model) {
        model.addAttribute("movieList", movieApiService.getInfo());
        return "mainpage";
    }
}
