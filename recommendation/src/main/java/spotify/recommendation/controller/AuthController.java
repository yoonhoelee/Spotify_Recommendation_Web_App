package spotify.recommendation.controller;

import com.wrapper.spotify.SpotifyApi;
import com.wrapper.spotify.SpotifyHttpManager;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.model_objects.credentials.AuthorizationCodeCredentials;
import com.wrapper.spotify.requests.authorization.authorization_code.AuthorizationCodeRequest;
import com.wrapper.spotify.requests.authorization.authorization_code.AuthorizationCodeUriRequest;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import spotify.recommendation.entity.Keys;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URI;

@RestController
@RequestMapping("/api")
public class AuthController {
    private static final URI redirectUri = SpotifyHttpManager.makeUri("http://localhost:8080/api/getUserCode");
    private String code="";

    private static final SpotifyApi spotifyApi = new SpotifyApi.Builder()
            .setClientId(Keys.CLIENT_ID)
            .setClientSecret(Keys.CLIENT_SECRET)
            .setRedirectUri(redirectUri)
            .build();

    @RequestMapping("/login")
    @ResponseBody
    public String spotifyLogin(){
        AuthorizationCodeUriRequest authorizationCodeUriRequest = spotifyApi.authorizationCodeUri()
                .scope("user-read-private, user-read-email, user-top-read")
                .show_dialog(true)
                .build();
        final URI uri = authorizationCodeUriRequest.execute();
        return uri.toString();
    }

    @GetMapping("/logintest")
    public ModelAndView test(){
        ModelAndView modelAndView = new ModelAndView();
        AuthorizationCodeUriRequest authorizationCodeUriRequest = spotifyApi.authorizationCodeUri()
                .scope("user-read-private, user-read-email, user-top-read")
                .show_dialog(true)
                .build();
        final URI uri = authorizationCodeUriRequest.execute();
        String uriString = uri.toString();
        modelAndView.addObject("uriString", uriString);
        modelAndView.setViewName("api/loginRedirect");
        return modelAndView;
    }

    @GetMapping("/getUserCode")
    public String getSpotifyUserCode(@RequestParam("code") String userCode, HttpServletResponse response) throws IOException{
        code = userCode;
        AuthorizationCodeRequest authorizationCodeRequest = spotifyApi.authorizationCode(code)
                .build();
        try {
            final AuthorizationCodeCredentials authorizationCodeCredentials = authorizationCodeRequest.execute();
            spotifyApi.setAccessToken(authorizationCodeCredentials.getAccessToken());
            spotifyApi.setAccessToken(authorizationCodeCredentials.getRefreshToken());
            System.out.println("Expires in:" + authorizationCodeCredentials.getExpiresIn());
        }catch (IOException | SpotifyWebApiException | org.apache.hc.core5.http.ParseException e){
            System.out.println("Error:" + e.getMessage());
        }
        response.sendRedirect("http://localhost:8080/topsongs");
        return spotifyApi.getAccessToken();
    }
    
}
