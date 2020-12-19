package com.jgar730.Spotitube.service;

import com.wrapper.spotify.SpotifyApi;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.model_objects.credentials.ClientCredentials;
import com.wrapper.spotify.model_objects.specification.Paging;
import com.wrapper.spotify.model_objects.specification.Track;
import com.wrapper.spotify.requests.authorization.client_credentials.ClientCredentialsRequest;
import com.wrapper.spotify.requests.data.search.simplified.SearchTracksRequest;
import org.apache.hc.core5.http.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Component
public class SpotifyServiceImpl implements SpotifyService{

    // TODO Fix these values not coming in for some reason
//    @Value("${spring.security.oauth2.client.clientId}")
    private String clientId = "726523ff10744c7492baf193993d378a";

//    @Value("${spring.security.oauth2.client.clientSecret}")
    private String clientSecret = "889171cdbc764773828c5e970656ec51";

    private SpotifyApi spotifyApi = new SpotifyApi.Builder()
            .setClientId(clientId)
            .setClientSecret(clientSecret)
            .build();

    final ClientCredentialsRequest clientCredentialsRequest = spotifyApi.clientCredentials().build();

    final ClientCredentials clientCredentials = clientCredentialsRequest.execute();

    private final Logger logger = LoggerFactory.getLogger(SpotifyServiceImpl.class);

    public SpotifyServiceImpl() throws ParseException, SpotifyWebApiException, IOException {
        spotifyApi.setAccessToken(clientCredentials.getAccessToken());

        logger.info("Expires in: " + clientCredentials.getExpiresIn());
    }

    @Override
    public String getFirstSpotifyResultUrlByQuery(String query) throws ParseException, SpotifyWebApiException, IOException {

        final SearchTracksRequest searchTrackRequest = spotifyApi.searchTracks(query).limit(5).build();
        final Paging<Track> trackPaging = searchTrackRequest.execute();
        String returnUrl = trackPaging.getItems()[0].getUri();
        logger.info(returnUrl);
        return returnUrl.split("spotify:track:")[1];

    }

}
