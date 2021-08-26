package spotify.recommendation.controller;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Data
public class SongForm {

    @NotEmpty(message = "곡명은 필수 입니다")
    private String songName;

    @NotEmpty(message = "아티스트명은 필수 입니다")
    private String artist;
}
