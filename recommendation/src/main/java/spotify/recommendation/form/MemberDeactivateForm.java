package spotify.recommendation.form;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class MemberDeactivateForm {
    @NotEmpty(message = "이메일은 필수 입니다")
    private String email;

    @NotEmpty(message = "비밀번호는 필수 입니다")
    private String password;

    @NotEmpty(message = "이름은 필수 입니다")
    private String name;
}
