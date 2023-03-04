package board.web.dto;


import board.crud.domain.member.Member;
import lombok.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class MemberDTO {
    private String name;
    private String nick;
    private String username;

    private String password;

    public Member toEntity(){
        return Member.builder()
                .name(name)
                .nick(nick)
                .username(username)
                .password(password)
                .build();
    }

    @Builder
    public MemberDTO(String name, String nick, String username, String password)
    {
        this.name = name;
        this.username = username;
        this.password = password;
        this.nick = nick;
    }

}
