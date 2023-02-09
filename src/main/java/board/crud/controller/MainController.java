package board.crud.controller;

import board.crud.domain.Movie;
import board.crud.domain.Rating;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class MainController {

    @GetMapping("/mainpage")
    public String mainpage(Model model){
        //List<Movie> movies = movieService.findMovies();
        model.addAttribute("rating", new Rating());
        return "mainpage";
    }

}
