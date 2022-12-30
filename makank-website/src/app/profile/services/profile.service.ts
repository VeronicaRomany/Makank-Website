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

  currentUserInfo:User = new User
  constructor(private http:HttpClient) { }
  userURL:string = "http://localhost:8080/users"
  postsURL:string = "http://localhost:8080/posts"



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

  getPostsOfTheUser(preferenceIn:ViewingPreference,targetUserID: number,pageNum:number):Observable<Post[]>{
    console.log(targetUserID)
    console.log(preferenceIn)
    let queryParams = new HttpParams();
    queryParams = queryParams.append("preference",JSON.stringify(preferenceIn));
    queryParams = queryParams.append("pageNum",JSON.stringify(pageNum));
    return this.http.get<Post[]>(this.postsURL+"/profile/"+targetUserID.toString(),{params:queryParams})
  }
  getSavedPosts(iD:number,preferenceIn:ViewingPreference,pageNum:number):Observable<Post[]>{
    let queryParams = new HttpParams();
    queryParams = queryParams.append("preference",JSON.stringify(preferenceIn));
    queryParams = queryParams.append("pageNum",JSON.stringify(pageNum));
    return this.http.get<Post[]>(this.postsURL+"/saved/"+iD.toString(),{params:queryParams
    })
  }
  getIds(postId:number):Observable<number[]>{
    console.log("user id passed: "+ postId)
    return this.http.get<number[]>(this.postsURL+"/saved/ids/"+postId.toString())

  }

  deletePost(iD:number):Observable<Post[]>{
    let queryParams=new HttpParams();
    queryParams = queryParams.append("postID",iD);
    return this.http.delete<any>(this.postsURL+"/delete/"+iD.toString())
  }
}
