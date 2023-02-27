package board.crud.member;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED) // 아무런 의미없는 값을 가지는 것을 방지한다.
@EntityListeners(AuditingEntityListener.class) //이후 개인정보 수정 시 날짜 기록을 위해 남겨둔다.
@Table(name = "member")
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long mid;

    private String name;

    @Column(unique = true)
    private String nick;

    @Column(unique = true)
    private String username;

    private String password;

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime createdTime;

    @Builder
    public Member(String name, String nick, String username, String password){
        this.username = username;
        this.password = password;
        this.name = name;
        this.nick = nick;
    }
}
