package board.crud.repository;

import board.crud.domain.Board;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class BoardRepository {

    private final EntityManager em;


    public List<Board> findAll() {
        return em.createQuery("select m from Board m", Board.class)
                .getResultList();
    }

    public void save(Board board) {
        em.persist(board);
    }


    public Board findById(Long id){
        return em.find(Board.class, id);
    }

    public void deleteById(Long id) {
        em.remove(em.find(Board.class, id));
    }
}
