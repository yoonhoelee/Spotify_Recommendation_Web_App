package spotify.recommendation.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
public class PlaylistController {

    @RequestMapping("/playlist")
    public String home(){
        return "playlist/playlistHome";
    }

    @RequestMapping("/playlist/gym")
    public String gym(){
        return "playlist/workout";
    }
    @RequestMapping("/playlist/user")
    public String userRecommendation(){
        return "playlist/userRecommendation";
    }
    @RequestMapping("/playlist/chill")
    public String chill(){
        return "playlist/outdoorchill";
    }
    @RequestMapping("/playlist/code")
    public String code(){
        return "playlist/coding";
    }
}
