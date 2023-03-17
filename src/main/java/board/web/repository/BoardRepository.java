package board.web.repository;

import board.crud.domain.board.Board;
import board.crud.domain.movie.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;


public interface BoardRepository extends JpaRepository<Board, Long> {

    List<Board> findByMovie_Id(@Param(value = "movieID") Long movieID);
    Optional<Board> findById(Long id);
    List<Board> findAll();
    Board findByTitle(String title);

}
