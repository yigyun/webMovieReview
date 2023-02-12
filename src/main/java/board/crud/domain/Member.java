package board.crud.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long mid;

    private String name;

    @Column(unique = true)
    private String nick;

    private String id;

    private String password;
}
