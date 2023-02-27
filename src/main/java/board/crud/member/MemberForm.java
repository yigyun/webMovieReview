package board.crud.member;


import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Getter
@Setter
public class MemberForm {
    @Size(min = 3, max = 20)
    @NotBlank(message = "이름은 필수항목입니다.")
    String name;

    @NotBlank(message = "닉네임은 필수항목입니다.")
    String nick;

    @NotEmpty(message = "비밀번호는 필수항목입니다.")
    String password;

    @NotEmpty(message = "아이디는 필수항목입니다.")
    String username;
}
