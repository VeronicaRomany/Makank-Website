import { Component, OnInit } from '@angular/core';
import { TokenStorageService } from '../login/services/token-storage.service';
import { ProfileService } from '../profile/services/profile.service';
import {Globals} from "../../globals";
import { User } from 'src/app/shared/user';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {
  currentUser: any;
  currentUserInfo: User = new User();
  notLogIn=true
  private userID: number | undefined;
  private pho: string =""

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
    // this.profile.getUserPhone(this.userID).subscribe(res=>{this.pho=res})
    // this.currentUserInfo.phone=this.pho
    // console.log(this.pho)
}


}
