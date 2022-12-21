import { Component, OnInit } from '@angular/core';
import { TokenStorageService } from '../login/services/token-storage.service';
import { ProfileService } from '../profile/services/profile.service';
import {Globals} from "../../globals";

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {
  currentUser: any;
  currentUserInfo: any;
  notLogIn=true
  private userID: number | undefined;

  constructor(private token: TokenStorageService,private profile: ProfileService) { }

  ngOnInit(): void {
    this.currentUser = this.token.getUser();
    this.userID = this.token.getUser().userId;
    if(this.currentUser.username!=undefined){
      this.notLogIn=false
      console.log("ii")
    }
    this.profile.getUserInfo(this.userID).subscribe(result=>{this.currentUserInfo=result
    console.log(result)})
    console.log(this.currentUserInfo.username)
  }

}
