package com.jgar730.Spotitube.rest;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.model.ChannelListResponse;
import com.google.api.services.youtube.model.SearchListResponse;
import com.google.api.services.youtube.model.SearchResult;
import com.jgar730.Spotitube.SpotitubeApplication;
import com.jgar730.Spotitube.service.SpotifyService;
import com.jgar730.Spotitube.service.YoutubeService;
import com.jgar730.Spotitube.util.Constants;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.GeneralSecurityException;
import java.time.LocalDate;
import java.util.*;

import static com.jgar730.Spotitube.util.Constants.*;

@RestController
@CrossOrigin
@RequestMapping("/rest")
public class MainController {

    private final Logger logger = LoggerFactory.getLogger(MainController.class);

    @Autowired
    private YoutubeService youtubeService;

    @Autowired
    private SpotifyService spotifyService;

    // TODO Fix these returns with a JsonSerializer/Deserializer as angular frontend expects a json.parse, for some reason?
    @RequestMapping("/spotify/queryByName")
    public String getFirstSpotifyResult(@RequestBody String query) throws ParseException, SpotifyWebApiException, IOException {
        return this.spotifyService.getFirstSpotifyResultUrlByQuery(query);
    }

    @RequestMapping("/youtube/queryByName")
    public String getTopYoutubeResult(@RequestBody String query) throws IOException {
        return "https://www.youtube.com/watch?v="+youtubeService.getTopYoutubeResult(query);
    }

}
