package board.web.service;

import board.crud.domain.board.Board;
import board.crud.domain.movie.Movie;
import board.crud.dto.BoardDTO;
import board.web.repository.BoardRepository;
import board.web.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class BoardService {
    private final BoardRepository boardRepository;
    private final MovieRepository movieRepository;

    @Transactional
    public void save(BoardDTO boardDTO){
        // 로그를 위해 분리
        Board board = boardDTO.toEntity();
        boardRepository.save(board);
    }

    @Transactional
    public void update(BoardDTO boardDTO){
        Board findBoard = boardRepository.findById(boardDTO.getId()).get();
        findBoard.updateBoard(boardDTO.getTitle(), boardDTO.getContent());
    }

    @Transactional
    public void delete(Long id){
        boardRepository.deleteById(id);
    }

 /*   @Transactional(readOnly = true)
    public List<BoardDTO> getList(){
        List<Board> boardList = boardRepository.findAll();
        List<BoardDTO> boardDTOSList = new ArrayList<>();

        for(Board board : boardList){
            BoardDTO boardDto = BoardDTO.builder()
                    .author(board.getAuthor())
                    .title(board.getTitle())
                    .content(board.getContent())
                    .createdDate(board.getCreatedTime())
                    .build();
            boardDTOSList.add(boardDto);
        }
        return boardDTOSList;
    }*/


    // 영화의 제목에 맞는 리뷰 리스트를 들고온다.
    @Transactional(readOnly = true)
    public List<BoardDTO> getList(String type, Long id){
        List<Board> boardList;
        //Movie movie = movieRepository.findById(id).get();
        //log.info("getList movieTitle={}", movie.getTitle());
        log.info("service getList id={}", id);
        boardList = boardRepository.findByMovie_Id(id);

        List<BoardDTO> boardDTOSList = new ArrayList<>();
        // review title, title, member name add
        for(Board board : boardList){
            BoardDTO boardDTO = BoardDTO.builder()
                    .id(board.getId())
                    .title(board.getTitle())
                    .author(board.getAuthor())
                    .content(board.getContent())
                    .createdDate(board.getCreatedTime())
                    .modifiedDate(board.getModifiedTime())
                    .build();
            boardDTOSList.add(boardDTO);
        }
        return boardDTOSList;
    }

    @Transactional(readOnly = true)
    public BoardDTO getContent(Long id){
        Board board = boardRepository.findById(id).get();

        return BoardDTO.builder()
                .id(board.getId())
                .author(board.getAuthor())
                .title(board.getTitle())
                .content(board.getContent())
                .createdDate(board.getCreatedTime())
                .build();
    }

    @Transactional(readOnly = true)
    public String getMovieTitle(Long id){
        return boardRepository.findById(id).get().getMovie().getTitle();
    }

    @Transactional(readOnly = true)
    public Long getMovieId(Long id){
        return boardRepository.findById(id).get().getMovie().getId();
    }
}


