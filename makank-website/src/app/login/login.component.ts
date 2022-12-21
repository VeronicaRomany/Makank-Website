import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import {AuthService} from "./services/auth.service.service";
import { TokenStorageService } from './services/token-storage.service';
import { Globals } from 'src/globals';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
  providers: [ Globals ],
})
export class LoginComponent implements OnInit {
  form: any = {
    username: null,
    password: null
  };
  isLoggedIn = false;
  isLoginFailed = false;
  errorMessage = '';
  username='';

  constructor(private authService: AuthService, private tokenStorage: TokenStorageService, private router: Router) { }

  ngOnInit(): void {
    if (this.tokenStorage.getToken()) {
      this.isLoggedIn = true;
      this.username = this.tokenStorage.getUser().username;
    }
  }

  onSubmit(): void {
    const { username, password } = this.form;

    this.authService.login(username, password).subscribe((data: number)=> {
        if(data > 0){
          this.tokenStorage.saveToken(username);
          this.tokenStorage.saveUser({"username":username,"password":password,"userId":data});
          console.log(this.tokenStorage.getUser())

          Globals.setUserID(data)
          console.log(Globals.getUserID())
          this.isLoginFailed = false;
          this.isLoggedIn = true;
          this.username = this.tokenStorage.getUser().username;
          this.router.navigate(['/', 'Home'])
        }
        else{
          if(data == -1) {
            this.errorMessage='Please enter valid username'
            this.isLoginFailed = true;
          }
          if(data == -2) {
            this.errorMessage='Please enter correct password'
            this.isLoginFailed = true;
          }

        }
      },
  );
  }

  reloadPage(): void {
    window.location.reload();
  }
}
