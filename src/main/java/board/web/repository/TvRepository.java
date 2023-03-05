package board.web.repository;

import board.crud.domain.movie.Tv;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TvRepository extends JpaRepository<Tv, Long> {
    Tv findByName(String name);
    Optional<Tv> findById(Long id);
}
