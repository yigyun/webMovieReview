package board.crud.entity;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@EntityListeners(AuditingEntityListener.class) // JPA에게 쓰이는거 감시함을 알린다.
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "Board")
public class Board {

    @Id             // 기본키로 설정
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100, nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    @Column(length = 10, nullable = false)
    private String author;

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime createdTime;

    @LastModifiedDate
    private LocalDateTime modifiedTime;

    //@ManyToOne // 영화 하나에 여러개의 글이 달릴 수 있다. 나중에 추가할 사항이다.
    //private Movie movie;

    @Builder
    public Board(Long id, String author, String title, String content) {
        this.id = id;
        this.author = author;
        this.title = title;
        this.content = content;
    }

    public void updateBoard(String title, String content){
        this.title = title;
        this.content = content;
    }
}
