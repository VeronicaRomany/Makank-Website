import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';

const AUTH_API = 'http://localhost:8080/users/';

@Injectable({
  providedIn: 'root'
})
export class DataReturned{
  id:number=0;
  token:string=''
  constructor(){}
}
export class AuthService {
  constructor(private http: HttpClient) { }

  login(username: string, password: string) {
    return this.http.post<DataReturned>(AUTH_API + 'signin', {
      username,
      password
    },{
      headers: new HttpHeaders({
        'Content-Type': 'application/json'
      })
    });
  }
}
