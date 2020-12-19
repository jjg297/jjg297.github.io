import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {HTTP_CORS_OPTIONS} from "../environments/environment";

@Injectable({providedIn: 'root'})
export class AppService {

  constructor(private http : HttpClient) { }

  getSongFromPlaylist(playlistId : string): Observable<string> {
    return this.http.get<string>("rest/songFromPlaylist/" + playlistId);
  }

  getFirstSpotifyResult(query: string): Observable<string> {
    return this.http.put<string>("/rest/spotify/queryByName", query, HTTP_CORS_OPTIONS);
  }

  getTopYoutubeResult(query: string): Observable<string> {
    return this.http.put<string>("/rest/youtube/queryByName", query, HTTP_CORS_OPTIONS);
  }

  getTopArtists(): Observable<any> {
    // return this.http.get<any>("/oauth2/authorization/github");

    return this.http.get<any>("http://localhost:8080/rest/user-top-artists", HTTP_CORS_OPTIONS);
  }

}
