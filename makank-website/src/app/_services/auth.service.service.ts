import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { DataReturned } from '../dataReturned';

const AUTH_API:string = 'http://localhost:8080/users/';

@Injectable({
  providedIn: 'root'
})
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
