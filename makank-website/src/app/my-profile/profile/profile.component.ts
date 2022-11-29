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
  
   num =[
    {
      id: 1,
      name: 'mark'
    },
    {
      id: 2,
      name: 'ma'
    },
    {
      id: 3,
      name: 'mi'
    }
   ]
}
