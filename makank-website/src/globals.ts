import { Injectable } from '@angular/core';


export class Globals{
    static posts:[]
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
    
}