import { Injectable } from '@angular/core';


export class Globals{
    static getUserID(): any {
      return this.userID
    }
    static setUserID(data: number) {
        this.userID = data;
    }
    static userID: number=0;
}