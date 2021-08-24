package spotify.recommendation.dto;

import lombok.Getter;
import lombok.Setter;
import spotify.recommendation.entity.Sex;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotEmpty;

@Getter @Setter
public class MemberForm {
    @NotEmpty(message = "이메일은 필수 입니다")
    private String email;

    @NotEmpty(message = "비밀번호는 필수 입니다")
    private String password;

    @NotEmpty(message = "이름은 필수 입니다")
    private String name;

    @NotEmpty(message = "나이는 필수 입니다")
    private int age;

    @NotEmpty(message = "성별은 필수 입니다")
    @Enumerated(EnumType.STRING)
    private Sex sex;
}
