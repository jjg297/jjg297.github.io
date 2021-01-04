import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Observable} from "rxjs";

@Injectable({providedIn: 'root'})
export class AppService {

  HTTP_CORS_OPTIONS = {
    headers : new HttpHeaders({
      'Access-Control-Allow-Credentials' : 'true',
      'Access-Control-Allow-Origin': '*',
      'Access-Control-Allow-Methods': 'GET, POST, PATCH, DELETE, PUT, OPTIONS',
      'Access-Control-Allow-Headers': 'Content-Type, Access-Control-Allow-Headers, Authorization, X-Requested-With'
    })
  }


  constructor(private http : HttpClient) { }

  getSongFromPlaylist(playlistId : string): Observable<string> {
    return this.http.get<string>("rest/songFromPlaylist/" + playlistId);
  }

  getFirstSpotifyResult(query: string): Observable<string> {
    return this.http.put<string>("/rest/spotify/queryByName", query, this.HTTP_CORS_OPTIONS);
  }

  getTopYoutubeResult(query: string): Observable<string> {
    return this.http.put<string>("/rest/youtube/queryByName", query, this.HTTP_CORS_OPTIONS);
  }

  getTopArtists(): Observable<any> {
    // return this.http.get<any>("/oauth2/authorization/github");

    return this.http.get<any>("http://localhost:8080/rest/user-top-artists", this.HTTP_CORS_OPTIONS);
  }

}
