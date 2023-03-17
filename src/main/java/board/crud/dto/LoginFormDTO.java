package board.crud.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class LoginFormDTO {
    @NotEmpty
    private String username;
    @NotEmpty
    private String password;
}
