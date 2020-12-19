package com.jgar730.Spotitube.service;

import com.google.api.services.youtube.model.SearchResult;

import java.io.IOException;

public interface YoutubeService {

    String getTopYoutubeResult(String query) throws IOException;
}
