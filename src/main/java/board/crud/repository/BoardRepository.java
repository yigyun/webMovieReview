package board.crud.repository;

import board.crud.domain.Board;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
@RequiredArgsConstructor
public class BoardRepository {

    private final EntityManager em;

    Page<Board> findByTitle(String keyword, Pageable pageable){

    }
}
