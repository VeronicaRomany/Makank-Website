import { JsonPipe } from '@angular/common';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, throwError } from 'rxjs';
import { catchError, retry } from 'rxjs/operators';
import { TokenStorageService } from 'src/app/_services/token-storage.service';


import { Post } from '../../shared/post';
import { ViewingPreference } from '../../shared/viewingPreference';

@Injectable({
  providedIn: 'root'
})
export class PropertiesService {
  
  constructor(private http:HttpClient,private token: TokenStorageService ) { }
  postsURL:string = "http://localhost:8080/posts"



  getPostsHomePage(preferenceIn:ViewingPreference,pageNum:number):Observable<Post[]>{
    console.log(preferenceIn)
    let queryParams = new HttpParams();
    queryParams = queryParams.append("preference",JSON.stringify(preferenceIn));
    queryParams = queryParams.append("pageNum",JSON.stringify(pageNum));
    return this.http.get<Post[]>(this.postsURL+"/homepage",{params:queryParams})
  }
  getPostsHomePageCounter(preferenceIn:ViewingPreference,pageNum:number):Observable<number>{
    console.log(preferenceIn)
    let queryParams = new HttpParams();
    queryParams = queryParams.append("preference",JSON.stringify(preferenceIn));
    queryParams = queryParams.append("pageNum",JSON.stringify(pageNum));
    return this.http.get<number>(this.postsURL+"/homepage/count",{params:queryParams})
  }
  getFilteredPosts(preferenceIn:ViewingPreference):Observable<Post[]>{
    console.log(preferenceIn)
    let queryParams = new HttpParams();
    queryParams = queryParams.append("preference",JSON.stringify(preferenceIn));

    return this.http.get<Post[]>(this.postsURL+"/homepage",{params:queryParams})
  }
  getSavedPosts(iD:number,preferenceIn:ViewingPreference,pageNum:number):Observable<Post[]>{
    var headers=new HttpHeaders().append("Authorization","Bearer "+this.token.getUser().token)
    let queryParams = new HttpParams();
    queryParams = queryParams.append("preference",JSON.stringify(preferenceIn));
    queryParams = queryParams.append("pageNum",JSON.stringify(pageNum));
    return this.http.get<Post[]>(this.postsURL+"/saved/"+iD.toString(),{params:queryParams,headers:headers

    })
    

  }
  getSavedPostsCounter(iD:number,preferenceIn:ViewingPreference,pageNum:number):Observable<number>{
    var headers=new HttpHeaders().append("Authorization","Bearer "+this.token.getUser().token)
    let queryParams = new HttpParams();
    queryParams = queryParams.append("preference",JSON.stringify(preferenceIn));
    queryParams = queryParams.append("pageNum",JSON.stringify(pageNum));
    return this.http.get<number>(this.postsURL+"/saved/"+iD.toString()+"/count",{params:queryParams,headers:headers

    })
    

  }


  getIds(postId:number):Observable<number[]>{
    var headers=new HttpHeaders().append("Authorization","Bearer "+this.token.getUser().token)
    console.log("user id passed: "+ postId)
    return this.http.get<number[]>(this.postsURL+"/saved/ids/"+postId.toString(),{headers:headers})

  }

  deletePost(iD:number):Observable<Post[]>{
    let queryParams=new HttpParams();
    var headers=new HttpHeaders().append("Authorization","Bearer "+this.token.getUser().token)
    queryParams = queryParams.append("postID",iD);
    return this.http.delete<any>(this.postsURL+"/delete/"+iD.toString(),{headers:headers})
  }




}
