package com.jgar730.Spotitube.service;

import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import org.apache.hc.core5.http.ParseException;

import java.io.IOException;

public interface SpotifyService {

    String getFirstSpotifyResultUrlByQuery(String query) throws ParseException, SpotifyWebApiException, IOException;

}
