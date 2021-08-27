package spotify.recommendation.controller;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class PasswordUpdateForm {

    @NotEmpty
    private String currentPw;

    @NotEmpty
    private String updatedPw;
}
