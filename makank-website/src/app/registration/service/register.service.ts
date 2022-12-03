import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {map} from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class RegisterService {
  
  private readonly key = "1bc201f882e2b46ecd476b7014891039"
  constructor(private readonly http:HttpClient) { }

  upload(file :any):Observable<string> {
  
     
    const formData = new FormData(); 
    formData.append("image", file);

   console.log(formData) 
   return this.http.post('https://api.imgbb.com/1/upload',formData,{params:{key:this.key}}).pipe(map((response : any)=> response['data']['url']));
}


}
