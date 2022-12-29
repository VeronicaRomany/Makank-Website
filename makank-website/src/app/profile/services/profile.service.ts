import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, throwError } from 'rxjs';
import { User } from 'src/app/shared/user';
import {Post} from "../../shared/post";
import {ViewingPreference} from "../../shared/viewingPreference";

@Injectable({
  providedIn: 'root'
})
export class ProfileService {

  constructor(private http:HttpClient) { }
  userURL:string = "http://localhost:8080/user"
  postsURL:string = "http://localhost:8080/posts"


  getUserInfo(userID: number | undefined):Observable<User>{
    console.log(userID)
    return this.http.get<User>(this.userURL+"/profile/"+userID)
  }
  // getUserPhone(userIdPhone: number | undefined):Observable<string>{
  //   console.log(userIdPhone)
  //   return this.http.get<string>(this.userURL+"/profile/phone/"+userIdPhone)
  // }
  getPostsOfTheUser(preferenceIn:ViewingPreference,userID: number):Observable<Post[]>{
    console.log(userID)
    console.log(preferenceIn)
    let queryParams = new HttpParams();
    queryParams = queryParams.append("preference",JSON.stringify(preferenceIn));
    return this.http.get<Post[]>(this.postsURL+"/userPost/"+userID.toString(),{params:queryParams})
  }
  getSavedPosts(iD:number,preferenceIn:ViewingPreference):Observable<Post[]> {
    let queryParams = new HttpParams();
    queryParams = queryParams.append("preference", JSON.stringify(preferenceIn));
    return this.http.get<Post[]>(this.postsURL + "/saved/" + iD.toString(), {
      params: queryParams
    })
  }
}
