import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/internal/Observable';
import { PostLargeView } from './postLargeView';

@Injectable({
  providedIn: 'root'
})
export class LargeViewService {
  postsURL:string = "http://localhost:8080/posts"
  constructor(private http:HttpClient) { }


  getLargePost(postId:number):Observable<PostLargeView>{
    return this.http.get<PostLargeView>(this.postsURL+"/details/"+postId.toString())
            
  
  }
}
