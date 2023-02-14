package board.crud.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long mid;

    private String name;

    @Column(unique = true)
    private String nick;

    private String id;

    private String password;

    @Builder
    public Member(Long mid, String name, String nick, String id, String password){
        this.id = id;
        this.mid = mid;
        this.password = password;
        this.name = name;
        this.nick = nick;
    }
}
