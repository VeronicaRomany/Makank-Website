import { Component, OnInit } from '@angular/core';
import { Post } from 'src/app/shared/post';
import { Apartment, Property, Villa } from 'src/app/shared/property';
@Component({
  selector: 'app-properties',
  templateUrl: './properties.component.html',
  styleUrls: ['./properties.component.css' ]
})
export class PropertiesComponent implements OnInit {
  posts:Post[] =[]
  constructor() { }

  ngOnInit(): void {
    let p = this.getDummyPost()
    let p2= this.getDummyPost()
    this.posts.push(p)
    this.posts.push(p2)
    console.log(this.posts)
  }
  
  getDummyPost():Post{
    let p = new Post()
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
    return p
  }


}

