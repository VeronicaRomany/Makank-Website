import { Component, OnInit } from '@angular/core';
import { FilterPreference, SortingPreference, ViewingPreference } from '../viewingPreference';
import { TokenStorageService } from 'src/app/login/services/token-storage.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {
  username?: string;
  password?:string;
  isLoggedIn=false;
  constructor(private tokenStorageService: TokenStorageService) { }

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


  temp(){
    let v:ViewingPreference = new ViewingPreference()
    let f:FilterPreference = new FilterPreference()
    let s:SortingPreference = new SortingPreference()

    f.withPictures=false
    f.addressSearchWord= "45"
    f.propertyType="Villa"
    f.purchaseChoice="buy"
    f.studentHousing=false
    
    s.ascending=true
    s.sortingCriteria="price"
    v.filterPrereferene=f
    v.sortingPreference=s
    v.sorted=false
    v.filtered=true
    console.log(JSON.stringify(v));
    
  }


  logout(): void {
    this.tokenStorageService.signOut();
    window.location.reload();
  }

}
