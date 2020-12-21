import {Component, OnInit} from '@angular/core';
import {AppService} from "./app.service";
import {FormBuilder, FormGroup} from "@angular/forms";

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

  constructor(
    private appService: AppService,
    private formBuilder: FormBuilder,
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
      console.log(this.spotifyResult);
      console.error(errorResponse);
      this.isSpotifyLoadComplete = true;
    }, () => {
      this.isSpotifyLoadComplete = true;
    });
  }

  getTopYoutubeResult(query: string){
    console.log("Running YOUTUBE query for: " + query);
    this.appService.getTopYoutubeResult(query).subscribe(res => {
      this.youtubeResult = res;
      console.log(res);
    }, errorResponse => {
      this.youtubeResult = errorResponse.error.text;
      console.error(this.youtubeResult);
      this.isYoutubeLoadComplete = true;
    }, () => {
      this.isYoutubeLoadComplete = true;
    });
  }
}
