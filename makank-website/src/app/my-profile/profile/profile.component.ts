import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {

  constructor() { }

  ngOnInit(): void {
  }
  
   zoo =[
    new animal(),
    new animal(),
    new animal()
   ]
    
  
}


class animal{
  name:string=""

  getdog(){
    return new dog()
  }
}


class dog{
  name = "woof"
}