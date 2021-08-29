package spotify.recommendation.controller;

import com.wrapper.spotify.SpotifyApi;
import com.wrapper.spotify.SpotifyHttpManager;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import spotify.recommendation.entity.Keys;

import java.net.URI;

@RestController
@RequestMapping("/api")
public class AuthController {
    private static final URI redirectUri = SpotifyHttpManager.makeUri("http://localhost:8080/members/memberInfo");

    private static final SpotifyApi spotifyApi = new SpotifyApi.Builder()
            .setClientId(Keys.CLIENT_ID)
            .setClientSecret(Keys.CLIENT_SECRET)
            .setRedirectUri(redirectUri)
            .build();
}
