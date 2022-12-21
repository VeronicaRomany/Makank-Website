import { JsonPipe } from '@angular/common';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, throwError } from 'rxjs';
import { catchError, retry } from 'rxjs/operators';


import { Post } from '../../shared/post';
import { ViewingPreference } from '../../shared/viewingPreference';

@Injectable({
  providedIn: 'root'
})
export class PropertiesService {
  
  constructor(private http:HttpClient) { }
  postsURL:string = "http://localhost:8080/posts"
  


  getPostsHomePage(preferenceIn:ViewingPreference):Observable<Post[]>{
    console.log(preferenceIn)
    let queryParams = new HttpParams();
    queryParams = queryParams.append("preference",JSON.stringify(preferenceIn));
    
    return this.http.get<Post[]>(this.postsURL+"/homepage",{params:queryParams})
  }
  getFilteredPosts(preferenceIn:ViewingPreference):Observable<Post[]>{
    console.log(preferenceIn)
    let queryParams = new HttpParams();
    queryParams = queryParams.append("preference",JSON.stringify(preferenceIn));
    
    return this.http.get<Post[]>(this.postsURL+"/homepage",{params:queryParams})
  }
  getSavedPosts(iD:Number,preferenceIn:ViewingPreference):Observable<Post[]>{
    let queryParams = new HttpParams();
    queryParams = queryParams.append("preference",JSON.stringify(preferenceIn));
    return this.http.get<Post[]>(this.postsURL+"/saved/"+iD.toString(),{params:queryParams

    })

  }
  
}
