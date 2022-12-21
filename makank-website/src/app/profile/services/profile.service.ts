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

  getUserInfo(id: number | undefined):Observable<User>{
    console.log(id)

    return this.http.get<User>(this.userURL+"/profile/"+id)
  }


}
