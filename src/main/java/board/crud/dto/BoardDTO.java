package board.crud.dto;


import board.crud.domain.board.Board;
import board.crud.domain.movie.Movie;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class BoardDTO {
    private Long id;
    private String author;
    private String title;
    private String content;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;

    private String movieTitle;
    private Movie movie;

    public Board toEntity(){
        return Board.builder()
                .author(author)
                .title(title)
                .content(content)
                .movie(movie)
                .build();
    }

    @Builder
    public BoardDTO(Long id,String movieTitle, String author, String title,
                    String content, LocalDateTime createdDate, LocalDateTime modifiedDate) {
        this.id = id;
        this.author = author;
        this.title = title;
        this.content = content;
        this.createdDate = createdDate;
        this.modifiedDate = modifiedDate;
        this.movieTitle = movieTitle;
    }

}
