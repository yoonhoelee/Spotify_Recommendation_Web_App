package spotify.recommendation.controller;

import com.wrapper.spotify.SpotifyApi;
import com.wrapper.spotify.SpotifyHttpManager;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.model_objects.credentials.AuthorizationCodeCredentials;
import com.wrapper.spotify.model_objects.specification.Artist;
import com.wrapper.spotify.model_objects.specification.Paging;
import com.wrapper.spotify.model_objects.specification.Track;
import com.wrapper.spotify.requests.authorization.authorization_code.AuthorizationCodeRequest;
import com.wrapper.spotify.requests.authorization.authorization_code.AuthorizationCodeUriRequest;
import com.wrapper.spotify.requests.data.personalization.simplified.GetUsersTopArtistsRequest;
import com.wrapper.spotify.requests.data.personalization.simplified.GetUsersTopTracksRequest;
import org.springframework.http.MediaType;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
import spotify.recommendation.entity.Keys;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URI;
@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api")
public class SpotifyApiController {
    private static final URI redirectUri = SpotifyHttpManager.makeUri("http://localhost:8080/api/getUserCode");
    private String code="";

    private static final SpotifyApi spotifyApi = new SpotifyApi.Builder()
            .setClientId(Keys.CLIENT_ID)
            .setClientSecret(Keys.CLIENT_SECRET)
            .setRedirectUri(redirectUri)
            .build();

    @RequestMapping("/login")
    @ResponseBody
    public RedirectView spotifyLogin(){
        AuthorizationCodeUriRequest authorizationCodeUriRequest = spotifyApi.authorizationCodeUri()
                .scope("user-read-private, user-read-email, user-top-read")
                .show_dialog(true)
                .build();
        final URI uri = authorizationCodeUriRequest.execute();
        String s = uri.toString();
        return new RedirectView(s);
    }


    @GetMapping("/getUserCode")
    public String getSpotifyUserCode(@RequestParam("code") String userCode, HttpServletResponse response) throws IOException{
        code = userCode;
        AuthorizationCodeRequest authorizationCodeRequest = spotifyApi.authorizationCode(code)
                .build();
        try {
            final AuthorizationCodeCredentials authorizationCodeCredentials = authorizationCodeRequest.execute();
            spotifyApi.setAccessToken(authorizationCodeCredentials.getAccessToken());
            System.out.println("Expires in:" + authorizationCodeCredentials.getExpiresIn());
        }catch (IOException | SpotifyWebApiException | org.apache.hc.core5.http.ParseException e){
            System.out.println("Error:" + e.getMessage());
        }
        response.sendRedirect("http://localhost:8080/api/topartist");
        return spotifyApi.getAccessToken();
    }

    @GetMapping("/topartist")
    public Artist[] topArtist(){
        final GetUsersTopArtistsRequest getUsersTopArtistsRequest = spotifyApi.getUsersTopArtists()
                .limit(10)
                .offset(0)
                .time_range("medium_term")
                .build();
        try{
            final Paging<Artist> artistPaging = getUsersTopArtistsRequest.execute();
            System.out.println("Total: " + artistPaging.getTotal());
            return artistPaging.getItems();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return new Artist[0];

    }

}
