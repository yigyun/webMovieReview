package board.web.controller;

import board.crud.domain.member.Member;
import board.crud.domain.movie.Movie;
import board.crud.dto.Login;
import board.web.repository.MovieRepository;
import board.web.service.BoardService;
import board.crud.dto.BoardDTO;
import board.web.service.MovieApiService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@Slf4j
@RequestMapping("/TPW/board")
@RequiredArgsConstructor
public class BoardController {

    private final MovieApiService movieApiService;
    private final BoardService boardService;

    @GetMapping("/post/{id}")
    public String content(@PathVariable("id") Long id, Model model){
        BoardDTO boardDTO = boardService.getContent(id);
        model.addAttribute("post", boardDTO);
        return "board/content";
    }

    @GetMapping("/post/update/{id}")
    public String update(@PathVariable("id") Long id, Model model){
        log.info("글 수정 시작");
        BoardDTO boardDTO = boardService.getContent(id);
        model.addAttribute("post", boardDTO);
        return "board/update";
    }

    @PutMapping("/post/update/{id}")
    public String edit(@PathVariable("id") Long id, @Validated BoardDTO boardDTO, BindingResult bindingResult){
        if(bindingResult.hasErrors())
            return "/contents/mainpage";
        log.info("update put 하기.");
        Long postId = boardService.getMovieId(id);
        boardService.update(boardDTO); //더티체킹
        return "redirect:/TPW/board/list/movie/" + postId;
    }

    @DeleteMapping("/post/delete/{id}")
    public String delete(@PathVariable("id") Long id){
        log.info("review delete start");
        Long postId = boardService.getMovieId(id);
        boardService.delete(id);
        return "redirect:/TPW/board/list/movie/" + postId;
    }

    // 아래부터가 진짜
    @GetMapping("/list/movie/{id}")
    public String movieList(@PathVariable("id") Long id, Model model){
        List<BoardDTO> boardDTOList = boardService.getList("movie", id);
        model.addAttribute("postList", boardDTOList);
        Movie movie = movieApiService.getMovieById(id);
        model.addAttribute("name", movie.getTitle());
        return "board/list";
    }

    @GetMapping("/post/new/{title}")
    public String post(Model model) {
        model.addAttribute("boardDTO", new BoardDTO());
        return "board/post";
    }

    @PostMapping("/post/new/{title}")
    public String write(@Login Member member, @Validated BoardDTO boardDTO, BindingResult bindingResult
        ,@PathVariable("title") String title){
        if(bindingResult.hasErrors()){
            return "board/post";
        }
        boardDTO.setAuthor(member.getNick());
        boardDTO.setMovie(movieApiService.getMovieByTitle(title));
        log.info("movie info={}", boardDTO.getMovie().getId());
        boardService.save(boardDTO);
        Long id = movieApiService.getIdByTitle(title);
        return "redirect:/TPW/board/list/movie/" + id;
    }
}
