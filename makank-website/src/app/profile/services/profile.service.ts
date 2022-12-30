import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, throwError } from 'rxjs';
import { User } from 'src/app/shared/user';

@Injectable({
  providedIn: 'root'
})
export class ProfileService {

  currentUserInfo:User = new User
  constructor(private http:HttpClient) { }
  userURL:string = "http://localhost:8080/users"

  
  getUserInfo(userID: number | undefined):Observable<User>{
    
    console.log(userID)
    return this.http.get<User>(this.userURL+"/profile/"+userID)
  }
  setUser(user:User){
    this.currentUserInfo=user
  }
  getUser(){
    console.log(this.currentUserInfo)
    return this.currentUserInfo
  }
  // getUserPhone(userIdPhone: number | undefined):Observable<string>{
  //   console.log(userIdPhone)
  //   return this.http.get<string>(this.userURL+"/profile/phone/"+userIdPhone)
  // }


}
