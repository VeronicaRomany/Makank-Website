import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';

const AUTH_API = 'http://localhost:8081/makank/';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  constructor(private http: HttpClient) { }

  login(username: string, password: string) {
    return this.http.post<number>(AUTH_API + 'signin', {
      username,
      password
    },{
      headers: new HttpHeaders({
        'Content-Type': 'application/json'
      })
    });
  }
}
