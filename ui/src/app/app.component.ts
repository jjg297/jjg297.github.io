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
  song: string;
  authentication : any;
  title = "";

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

  onButtonClick(){
    this.appService.getSongFromPlaylist(this.playlistForm.get("name").value).subscribe(res => {
      this.song = res;
    });
  }

  authSpotify(){
    this.appService.authSpotify().subscribe(res => {
      this.authentication = res;
      console.log(res);
    });
  }

  getTopArtists(){
    this.appService.getTopArtists().subscribe(res => {
      console.log(res);
    });
  }
}
