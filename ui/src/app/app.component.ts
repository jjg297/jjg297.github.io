import {Component, OnInit} from '@angular/core';
import {AppService} from "./app.service";
import {FormBuilder, FormGroup} from "@angular/forms";
import { DomSanitizer, SafeUrl } from '@angular/platform-browser';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent implements OnInit {

  playlistForm: FormGroup;
  query : string = "";
  youtubeResult: string = "";
  spotifyResult: string = "";
  title = "";
  isSpotifyLoadComplete: boolean = false;
  isYoutubeLoadComplete: boolean = false;
  spotify_url: SafeUrl;
  youtube_url: SafeUrl;

  constructor(
    private appService: AppService,
    private formBuilder: FormBuilder,
    public sanitizer: DomSanitizer
  ) {
    this.title = 'Spotitube'
    this.playlistForm = this.formBuilder.group({
      name: ''
    });
  }

  ngOnInit() {
  }

  getFirstSpotifyResult(query: string){
    console.log("Running SPOTIFY query for: " + query);
    this.appService.getFirstSpotifyResult(query).subscribe(res => {
      this.spotifyResult = res;
      console.log(res);
    }, errorResponse => {
      this.spotifyResult = errorResponse.error.text;
      this.spotify_url= this.updateSpotifySrc();
      console.log(this.spotifyResult);
      console.log(this.spotify_url);
      console.error(errorResponse);
      this.isSpotifyLoadComplete = true;
      this.isYoutubeLoadComplete = false;
    }, () => {
      this.isSpotifyLoadComplete = true;
      this.isYoutubeLoadComplete = false;
    });
  }

  getTopYoutubeResult(query: string){
    console.log("Running YOUTUBE query for: " + query);
    this.appService.getTopYoutubeResult(query).subscribe(res => {
      this.youtubeResult = res;
      console.log(res);
    }, errorResponse => {
      this.youtubeResult = errorResponse.error.text;
      this.youtube_url= this.updateYouTubeSrc();
//       console.error(this.youtubeResult);
//       console.error(this.youtubeResult.substring(32));
      this.isYoutubeLoadComplete = true;
      this.isSpotifyLoadComplete = false;
    }, () => {
      this.isYoutubeLoadComplete = true;
      this.isSpotifyLoadComplete = false;
    });

  }
  updateYouTubeSrc() {
        let str = this.sanitizer.bypassSecurityTrustResourceUrl(("https://www.youtube.com/embed/") + this.youtubeResult);
         console.log(str);
         return str;
//          return this.sanitizer.bypassSecurityTrustResourceUrl(this.youtubeResult);
       }
  updateSpotifySrc() {
          let str = this.sanitizer.bypassSecurityTrustResourceUrl(("https://open.spotify.com/embed/") + this.spotifyResult.substring(25));
           console.log(str);
           return str;
         }
}
