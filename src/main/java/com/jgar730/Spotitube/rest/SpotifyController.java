package com.jgar730.Spotitube.rest;

import com.neovisionaries.i18n.CountryCode;
import com.wrapper.spotify.SpotifyApi;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.model_objects.credentials.ClientCredentials;
import com.wrapper.spotify.model_objects.specification.Album;
import com.wrapper.spotify.model_objects.specification.Paging;
import com.wrapper.spotify.model_objects.specification.PlaylistSimplified;
import com.wrapper.spotify.model_objects.specification.Track;
import com.wrapper.spotify.requests.authorization.client_credentials.ClientCredentialsRequest;
import com.wrapper.spotify.requests.data.albums.GetAlbumRequest;
import com.wrapper.spotify.requests.data.playlists.GetPlaylistRequest;
import com.wrapper.spotify.requests.data.search.simplified.SearchTracksRequest;
import com.wrapper.spotify.requests.data.tracks.GetTrackRequest;
import org.apache.hc.core5.http.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import java.io.IOException;
import java.time.LocalDate;
import java.util.*;

@RestController
@RequestMapping("/rest")
public class SpotifyController {

//    @Value("${spring.security.oauth2.client.registration.spotify.clientId}")
    @Value("${spring.security.oauth2.client.clientId}")
    private String clientId;

//    @Value("${spring.security.oauth2.client.registration.spotify.clientSecret}")
    @Value("${spring.security.oauth2.client.clientSecret}")
    private String clientSecret;

    @Value("${spring.security.oauth2.client.registration.spotify.accessToken")
    private String accessToken;

    Logger logger = LoggerFactory.getLogger(SpotifyController.class);

    @RequestMapping("/login")
//    @CrossOrigin(origins = "http://localhost:4200/")
    public String spotifyLogin() throws ParseException, SpotifyWebApiException, IOException {
        SpotifyApi spotifyApi = new SpotifyApi.Builder()
                .setClientId(clientId)
                .setClientSecret(clientSecret)
                .build();

        final ClientCredentialsRequest clientCredentialsRequest = spotifyApi.clientCredentials().build();

        try{
            final ClientCredentials clientCredentials = clientCredentialsRequest.execute();

            spotifyApi.setAccessToken(clientCredentials.getAccessToken());

            logger.info("Expires in: " + clientCredentials.getExpiresIn());
        } catch (IOException | SpotifyWebApiException | ParseException e) {
            logger.error("Error: " + e.getMessage());
        }

        final GetAlbumRequest getAlbumRequest = spotifyApi.getAlbum("5zT1JLIj9E57p3e1rFm9Uq").build();
        final Album album = getAlbumRequest.execute();

        final SearchTracksRequest searchTrackRequest = spotifyApi.searchTracks("Run Joji").build();
        final Paging<Track> trackPaging = searchTrackRequest.execute();

        logger.info(String.valueOf(trackPaging.getTotal()));

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
