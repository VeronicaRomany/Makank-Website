import { Injectable } from '@angular/core';

@Injectable()
export class Globals{
    static posts:any[]
    static userID: number=0;
    static getUserID(): any {
      return this.userID
    }
    static setUserID(data: number) {
        this.userID = data;
    }
    static getPosts():any{
      return this.posts
    }
    static setPosts(allPosts: any[] ){
      this.posts=allPosts

    }
    
}