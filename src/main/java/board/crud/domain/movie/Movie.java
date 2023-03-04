package board.crud.domain.movie;


import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Movie {
    @Id
    private Long id;

    private String title;

    private String description;

    private String posterPath;

    private Double voteAverage;
}

