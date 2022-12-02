import { Injectable } from '@angular/core';
import { Observable, throwError } from 'rxjs';
import { catchError, retry } from 'rxjs/operators';
import { Post } from 'src/app/shared/post';
import { ViewingPreference } from 'src/app/shared/viewingPreference';

@Injectable({
  providedIn: 'root'
})
export class PropertiesService {

  constructor() { }

  getPostsHomePage(preference:ViewingPreference):Observable<Post[]>{
    console.log(preference)
    return this.http.get<Post[]>(this.postsURL+"/homepage",{params:{
      preference:JSON.stringify(preference)
    }})
  }

  
}
