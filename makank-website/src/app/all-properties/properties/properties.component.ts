import { HttpClient } from '@angular/common/http';
import { Component, Injectable, OnInit } from '@angular/core';
import { Post } from 'src/app/shared/post';
import { Apartment, Property, Villa } from 'src/app/shared/property';
import { ViewingPreference } from 'src/app/shared/viewingPreference';
import { PropertiesService } from '../services/properties.service';
@Component({
  selector: 'app-properties',
  templateUrl: './properties.component.html',
  styleUrls: ['./properties.component.css' ]
})
export class PropertiesComponent implements OnInit {
  posts:Post[] =[]
  serv: PropertiesService 
  preference:ViewingPreference=new ViewingPreference()
  constructor(private service:PropertiesService) { 
    this.serv= service
  }

  ngOnInit(): void {
    // let p = this.getDummyPost()
    // let p2= this.getDummyPost()
    // this.posts.push(p)
    // this.posts.push(p2)
    // console.log(this.posts)
    this.sendPostsRequests()
    
  }

  sendPostsRequests(){
      this.serv.getPostsHomePage(this.preference).subscribe(results => {
          
          this.posts=results
      } );
   
  }

 getDummyPost():Post{
    let p = new Post()
    let v = new Villa()
    v.hasGarden=false
    v.hasPool=true 
    v.numberOfLevels=3
    p.property=v
    p.postID=5
    p.publishDate=new Date()
    p.property=new Property()
    p.property.pictures=['https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRsleUSqV4jrTGyQ-LfR3V5bkobZGtG0hyXf5ObJEy7&s']
    p.property.area=25
    p.property.city='ka3 el hamoor'
    p.property.info='pinapple 3 levels very good neighborhood\n neighbors are jazz playning squid and a pink starfish'
    p.property.bathroomNumber=1
    p.property.rent=false
    p.property.roomNumber=4
    p.property.sellerID=145
    p.property.price=50000000000

    let x = (p.property as Villa).hasGarden
    console.log(x)
    console.log(p.property instanceof Villa)
    return p
  }


}

