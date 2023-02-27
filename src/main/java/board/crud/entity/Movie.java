package board.crud.entity;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class Movie {
    private float stars;
    private String title;

    //@OneToMany(mappedBy = "question", cascade = CascadeType.REMOVE)
   // private List<Board> boardList;
}
