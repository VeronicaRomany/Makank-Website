import { Component, OnInit } from '@angular/core';
import { FilterPreference, SortingPreference, ViewingPreference } from '../viewingPreference';
import { TokenStorageService } from 'src/app/_services/token-storage.service';
import { Router } from '@angular/router';
import { PropertiesComponent } from 'src/app/all-properties/properties/properties.component';
import { SharedService } from 'src/app/shared.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {
  username?: string;
  password?:string;
  isLoggedIn=false;
  flag:boolean= false
  constructor(private tokenStorageService: TokenStorageService, private router:Router ,private sharedService:SharedService) { }

  ngOnInit(): void {
    
    this.isLoggedIn = !!this.tokenStorageService.getToken();
    console.log(this.tokenStorageService.getUser())
    if (this.isLoggedIn) {
      const user = this.tokenStorageService.getUser();
      this.username = user.username;
      this.password=user.password;
      console.log(this.username)
    }
  }
  goToNewPost(){
     if(this.isLoggedIn){
      this.router.navigate(['/', 'NewPost'])
     }else{
      alert('Login or Register')
     }
  }
  clickMe(){
    console.log("cliick /meeeeeeeeeeeeee");
    if(this.flag==false){
    this.router.navigate([ '/','Home'],{queryParams:{data:"saved"}})
     this.flag=true
    }else{
      window.location.reload()
    }
   // this.sharedService.sendClickEvent();
    
    }
 navigateHome(){
  this.router.navigate(['/', 'Home'])
 }
 getSavedPost(){
  //this.property.getSavedPost()
 }




  logout(): void {
    this.tokenStorageService.signOut();
    this.router.navigate(['/', 'Home'])
    window.location.reload();
    
  }

}
