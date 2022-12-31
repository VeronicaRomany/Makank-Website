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
    this.sharedService.sendClickEvent();
    }
 navigateHome(){
  this.router.navigate(['/', 'Home'])
 }
 getSavedPost(){
  //this.property.getSavedPost()
 }

  temp(){
    let v:ViewingPreference = new ViewingPreference()
    let f:FilterPreference = new FilterPreference()
    let s:SortingPreference = new SortingPreference()

    f.withPictures=false
    f.infoSearchWord= "45"
    f.propertyType="Villa"
    f.purchaseChoice="buy"
    f.studentHousing=false
    
    s.ascending=true
    s.sortingCriteria="price"
    v.filterPreference=f
    v.sortingPreference=s
    v.sorted=false
    v.filtered=true
    console.log(JSON.stringify(v));
    
  }


  logout(): void {
    this.tokenStorageService.signOut();
    this.router.navigate(['/', 'Home'])
    window.location.reload();
    
  }

}
