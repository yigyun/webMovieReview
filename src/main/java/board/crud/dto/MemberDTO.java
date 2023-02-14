package board.crud.dto;


import board.crud.domain.Member;
import lombok.*;

@Getter
@NoArgsConstructor
@ToString
public class MemberDTO {

    private Long mid;
    private String name;
    private String nick;
    private String id;
    private String password;

    public Member toEntity(){
        return Member.builder()
                .mid(mid)
                .name(name)
                .nick(nick)
                .id(id)
                .password(password).build();
    }

    @Builder
    public MemberDTO(Long mid, String name, String nick, String id, String password)
    {
        this.mid = mid;
        this.name = name;
        this.id = id;
        this.password = password;
        this.nick = nick;
    }
}
