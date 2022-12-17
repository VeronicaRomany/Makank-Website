import { Injectable } from '@angular/core';

@Injectable()
export class Globals{
    userID: number=333;

    setUserID(id:number){
        this.userID=id
    }

    getUserID(){
        return this.userID;
    }
}