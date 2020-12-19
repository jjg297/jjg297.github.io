package com.jgar730.Spotitube.service;

import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.model.SearchListResponse;
import com.google.api.services.youtube.model.SearchResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Collections;

import static com.jgar730.Spotitube.util.Constants.*;

@Service
public class YoutubeServiceImpl implements YoutubeService{

    private final NetHttpTransport httpTransport = GoogleNetHttpTransport.newTrustedTransport();

    private final Logger logger = LoggerFactory.getLogger(YoutubeServiceImpl.class);

    private final YouTube youtubeInstance = new YouTube.Builder(httpTransport, JSON_FACTORY, null)
            .setApplicationName(APPLICATION_NAME).build();

    public YoutubeServiceImpl() throws GeneralSecurityException, IOException {
    }

    @Override
    public String getTopYoutubeResult(String query) throws IOException {
        // Define and execute the API request
        YouTube.Search.List searchRequest = youtubeInstance.search()
                .list(Collections.singletonList("snippet"));
        SearchListResponse response = searchRequest.setKey(API_KEY)
                .setMaxResults(1L).setQ(query)
                .execute();
        return response.getItems().get(0).getId().getVideoId();
    }

}
