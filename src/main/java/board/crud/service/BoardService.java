package board.crud.service;

import board.crud.entity.Board;
import board.crud.dto.BoardDTO;
import board.crud.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;

    @Transactional
    public void save(BoardDTO boardDTO){
        boardRepository.save(boardDTO.toEntity());
    }

    @Transactional
    public void update(BoardDTO boardDTO){
        Board findBoard = boardRepository.findById(boardDTO.getId());
        findBoard.updateBoard(boardDTO.getTitle(), boardDTO.getContent());
    }

    @Transactional
    public void delete(Long id){
        boardRepository.deleteById(id);
    }

    @Transactional
    public List<BoardDTO> getList(){
        List<Board> boardList = boardRepository.findAll();
        List<BoardDTO> boardDTOSList = new ArrayList<>();

        for(Board board : boardList){
            BoardDTO boardDto = BoardDTO.builder()
                    .id(board.getId())
                    .author(board.getAuthor())
                    .title(board.getTitle())
                    .content(board.getContent())
                    .createdDate(board.getCreatedTime())
                    .build();
            boardDTOSList.add(boardDto);
        }
        return boardDTOSList;
    }

    @Transactional
    public BoardDTO getContent(Long id){
        Board board = boardRepository.findById(id);

        return BoardDTO.builder()
                .id(board.getId())
                .author(board.getAuthor())
                .title(board.getTitle())
                .content(board.getContent())
                .createdDate(board.getCreatedTime())
                .build();
    }
}
