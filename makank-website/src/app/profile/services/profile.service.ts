import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, throwError } from 'rxjs';
import { User } from 'src/app/shared/user';

@Injectable({
  providedIn: 'root'
})
export class ProfileService {

  constructor(private http:HttpClient) { }
  userURL:string = "http://localhost:8080/user"

  getUserInfo(userID: number | undefined):Observable<User>{
    console.log(userID)
    return this.http.get<User>(this.userURL+"/profile/"+userID)
  }
  getUserPhone(userIdPhone: number | undefined):Observable<any>{
    console.log(userIdPhone)
    return this.http.get<any>(this.userURL+"/profile/phone/"+userIdPhone)
  }


}
