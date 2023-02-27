package board.crud.controller;

import board.crud.service.BoardService;
import board.crud.dto.BoardDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@Slf4j
@RequestMapping("/TPW/board")
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    @GetMapping("/list")
    public String list(Model model){
        List<BoardDTO> boardDTOList = boardService.getList();
        model.addAttribute("postList",boardDTOList);
        return "board/list";
    }

    @GetMapping("/post")
    public String post(@ModelAttribute("boardDTO") BoardDTO boardDTO){
        return "board/post";
    }

    @PostMapping(value = "/post")
    public String write(@Validated BoardDTO boardDTO, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "board/post";
        }else{
            boardService.save(boardDTO);
            return "redirect:/board/list";
        }
    }

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
    public String edit(@Validated BoardDTO boardDTO){
        log.info("update put 하기.");
        boardService.update(boardDTO); //더티체킹
        return "redirect:/board/list";
    }

    @DeleteMapping("/post/{id}")
    public String delete(@Validated BoardDTO boardDTO){
        log.info("review delete start");
        boardService.delete(boardDTO.getId());
        return "redirect:/board/list";
    }
}
