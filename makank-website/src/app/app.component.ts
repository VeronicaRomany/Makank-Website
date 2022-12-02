
import { TokenStorageService } from './_services/token-storage.service';
import { Component,OnInit } from '@angular/core';




@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})

export class AppComponent implements OnInit{
 
  title = 'makank-website';

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

  logout(): void {
    this.tokenStorageService.signOut();
    window.location.reload();
  }
}
