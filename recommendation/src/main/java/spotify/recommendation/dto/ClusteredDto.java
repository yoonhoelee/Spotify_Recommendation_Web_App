package spotify.recommendation.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ClusteredDto {

    private String song_name;
    private String artist;

}
