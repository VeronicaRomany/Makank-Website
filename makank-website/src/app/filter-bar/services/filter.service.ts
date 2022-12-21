import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Post } from 'src/app/shared/post';
import { ViewingPreference } from 'src/app/shared/viewingPreference';

@Injectable({
  providedIn: 'root'
})
export class FilterService { 
  url ="http://localhost:8080/users/posts/homepage"

  constructor(private http:HttpClient) { }
  getFilteredPosts(preferenceIn:ViewingPreference):Observable<Post[]>{
    console.log(preferenceIn)
    let queryParams = new HttpParams();
    queryParams = queryParams.append("preference",JSON.stringify(preferenceIn));
    
    return this.http.get<Post[]>(this.url,{params:queryParams})
  }

}
