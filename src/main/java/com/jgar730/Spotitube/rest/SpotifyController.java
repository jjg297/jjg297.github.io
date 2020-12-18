package com.jgar730.Spotitube.rest;

import com.neovisionaries.i18n.CountryCode;
import com.wrapper.spotify.SpotifyApi;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.model_objects.specification.PlaylistSimplified;
import com.wrapper.spotify.requests.data.playlists.GetPlaylistRequest;
import org.apache.hc.core5.http.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import java.io.IOException;
import java.util.*;

@RestController
//@RequestMapping("/rest")
public class SpotifyController {

//    @Value("${spring.security.oauth2.client.registration.spotify.clientId}")
    @Value("${spring.security.oauth2.client.clientSecret}")
    private String clientId;

//    @Value("${spring.security.oauth2.client.registration.spotify.clientSecret}")
    @Value("${spring.security.oauth2.client.clientSecret}")
    private String clientSecret;

    @Value("${spring.security.oauth2.client.registration.spotify.accessToken")
    private String accessToken;

    Logger logger = LoggerFactory.getLogger(SpotifyController.class);

//    public static void clientCredentials_Sync() {
//        SpotifyApi spotifyApi = new SpotifyApi.Builder()
//                .setClientId(this.clientId)
//                .setClientSecret(this.clientSecret)
//                .build();
//        ClientCredentialsRequest clientCredentialsRequest = spotifyApi.clientCredentials()
//                .build();
//        try {
//            final ClientCredentials clientCredentials = clientCredentialsRequest.execute();
//
//            // Set access token for further "spotifyApi" object usage
//            spotifyApi.setAccessToken(clientCredentials.getAccessToken());
//
//            System.out.println("Expires in: " + clientCredentials.getExpiresIn());
//        } catch (IOException | SpotifyWebApiException | ParseException e) {
//            System.out.println("Error: " + e.getMessage());
//        }
//    }
//
//    public static void clientCredentials_Async() {
//        ClientCredentialsRequest clientCredentialsRequest = spotifyApi.clientCredentials()
//                .build();
//        try {
//            final CompletableFuture<ClientCredentials> clientCredentialsFuture = clientCredentialsRequest.executeAsync();
//
//            // Thread free to do other tasks...
//
//            // Example Only. Never block in production code.
//            final ClientCredentials clientCredentials = clientCredentialsFuture.join();
//
//            // Set access token for further "spotifyApi" object usage
//            spotifyApi.setAccessToken(clientCredentials.getAccessToken());
//
//            System.out.println("Expires in: " + clientCredentials.getExpiresIn());
//        } catch (CompletionException e) {
//            System.out.println("Error: " + e.getCause().getMessage());
//        } catch (CancellationException e) {
//            System.out.println("Async operation cancelled.");
//        }
//    }

    @RequestMapping("/login")
//    @CrossOrigin(origins = "http://localhost:4200/")
    public String spotifyLogin() throws ParseException, SpotifyWebApiException, IOException {
        SpotifyApi spotifyApi = new SpotifyApi.Builder()
                .setClientId(clientId)
                .setClientSecret(clientSecret)
                .build();

        PlaylistSimplified[] playlistList = spotifyApi.getListOfCurrentUsersPlaylists().build().execute().getItems();

// Create a request object with the optional parameter "market"
        final GetPlaylistRequest getSomethingRequest = spotifyApi.getPlaylist("qKRpDADUKrFeKhFHDMdfcu")
                .market(CountryCode.US)
                .build();

        logger.info(playlistList.toString());

        return "Check Logs!";
    }

    @RequestMapping("/resource")
    public Map<String,Object> home() {
        Map<String,Object> model = new HashMap<String,Object>();
        model.put("id", UUID.randomUUID().toString());
        model.put("content", "Hello World");
        return model;
    }

}
