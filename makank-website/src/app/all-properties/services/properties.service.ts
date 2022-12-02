import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, throwError } from 'rxjs';
import { catchError, retry } from 'rxjs/operators';
import { Post } from 'src/app/post';
import { ViewingPreference } from 'src/app/shared/viewingPreference';

@Injectable({
  providedIn: 'root'
})
export class PropertiesService {
  
  constructor(private http:HttpClient) { }
  postsURL:string = "http://localhost:8080/posts"
  


  getPostsHomePage(preference:ViewingPreference):Observable<Post[]>{
    // let pars:HttpParams = new HttpParams()
    console.log(preference)
    // pars.append("preference",JSON.preference)
    return this.http.get<Post[]>(this.postsURL+"/homepage",{params:{
      preference:JSON.stringify(preference)
    }})
  }

  
}
