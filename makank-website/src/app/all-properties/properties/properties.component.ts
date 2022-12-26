import { HttpClient } from '@angular/common/http';
import { Component, Injectable, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { TokenStorageService } from 'src/app/_services/token-storage.service';
import { Globals } from 'src/globals';


import { Post } from '../../shared/post';
import { Property, Villa } from '../../shared/property';
import { ViewingPreference } from '../../shared/viewingPreference';

import { PropertiesService } from '../services/properties.service';
@Component({
  selector: 'app-properties',
  templateUrl: './properties.component.html',
  styleUrls: ['./properties.component.css' ],
  providers: [ Globals ],
})
export class PropertiesComponent implements OnInit {
  posts:Post[] = []
  serv: PropertiesService 
  preference:ViewingPreference=new ViewingPreference()
  userID:number=0
  condition:boolean=false
  goToEdit:boolean=false
  editedID:number=0
  constructor(private service:PropertiesService,private router:Router,private token: TokenStorageService) { 
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
    this.userID = this.token.getUser().userId;
    console.log(this.preference)
      this.serv.getPostsHomePage(this.preference).subscribe(results => {
          console.log("ana rg3t", results)
          this.posts=results
        // Globals.setPosts(results)
      } );
   
  }
  getSavedPost(){
    this.userID = this.token.getUser().userId;
    this.serv.getSavedPosts(this.userID,this.preference).subscribe(results => {
      console.log("saveeed", results)
      this.posts=results
    })
  }


  editMypost(postID:number){
      console.log(postID)
      this.editedID=postID
      this.goToEdit=true

      this.router.navigate([ '/','NewPost'],{queryParams:{data:postID}})
  }

  deleteMypost(postID:number){
    console.log(postID)
    this.serv.deletePost(postID).subscribe(results => {
      console.log("deleted", results)
      this.posts=results
    })
}



// // getDummyPost():Post{
//     let p = new Post()
//     let v = new Villa()
//     v.hasGarden=false
//     v.hasPool=true 
//     v.numberOfLevels=3
//     p.property=v
//     p.postID=5
//     p.publishDate=new Date()
//     p.property=new Property()
//     p.property.pictures=['https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRsleUSqV4jrTGyQ-LfR3V5bkobZGtG0hyXf5ObJEy7&s']
//     p.property.area=25
//     p.property.city='ka3 el hamoor'
//     p.property.info='pinapple 3 levels very good neighborhood\n neighbors are jazz playning squid and a pink starfish'
//     p.property.bathroomNumber=1
//     p.property.rent=false
//     p.property.roomNumber=4
//     p.property.sellerID=145
//     p.property.price=50000000000

//     let x = (p.property as Villa).hasGarden
//     console.log(x)
//     console.log(p.property instanceof Villa)
//     return p
//   //}

  goTonewPost():void{
    
    this.router.navigate([ '/','NewPost'])
  }
  getInfo(){
    var textSearch =  document.getElementById('searchText') as HTMLInputElement
    var type = document.getElementById('format1') as HTMLSelectElement
    var city = document.getElementById('format2') as HTMLSelectElement
    var buyRent  = document.getElementById('format3') as HTMLSelectElement
    var university = document.getElementById('format4') as HTMLSelectElement
    var minPrice = document.getElementById('minPrice') as HTMLInputElement
    var maxPrice = document.getElementById('maxPrice') as HTMLInputElement
    var minArea = document.getElementById('minArea') as HTMLInputElement
    var maxArea = document.getElementById('maxArea') as HTMLInputElement
    var withPictures =document.getElementById('c1') as HTMLInputElement
    var studentHousing =document.getElementById('c2') as HTMLInputElement
    var sortingCriteria = document.getElementById('sort') as HTMLSelectElement
    var order = document.getElementById('order') as HTMLSelectElement
    if(Number(minPrice.value)>Number(maxPrice.value)){
      console.log("priceee++++++")
      //console.log(minPrice,maxPrice)
      var temp = minPrice.value
      minPrice.value = maxPrice.value
      maxPrice.value = temp
    }
  
    if(Number(minArea.value)>Number(maxArea.value)){
      console.log("Areaaaa++++++")
      var temp = minArea.value
      minArea.value = maxArea.value
      maxArea.value = temp
    }
    if(textSearch.value=='' && (type.value=="Type"||type.value=="any") &&  (city.value=="City"||city.value=="any")
    &&  (buyRent.value=="Bye/Rent"||buyRent.value=="any")&&  Number(maxPrice.value)==0 &&Number(minPrice.value)==0&&Number(maxArea.value)==0
    &&Number(minArea.value)==0 && withPictures.checked==false && studentHousing.checked == false
    ){
       this.preference.filtered=false
    }else{
      this.preference.filtered=true
    }
    this.preference.filterPreference.infoSearchWord= textSearch.value
    this.preference.filterPreference.propertyType=type.value
    this.preference.filterPreference.citySearchWord=city.value
    this.preference.filterPreference.purchaseChoice=buyRent.value
    this.preference.filterPreference.maxPrice=Number(maxPrice.value)==0 ? -1 :Number(maxPrice.value)
    this.preference.filterPreference.minPrice=Number(minPrice.value)==0? -1:Number(minPrice.value)
    this.preference.filterPreference.minArea= Number(minArea.value)==0? -1:Number(minArea.value)
    this.preference.filterPreference.maxArea=Number(maxArea.value)==0? -1:Number(maxArea.value)
    this.preference.filterPreference.withPictures=withPictures.checked
    this.preference.filterPreference.studentHousing=studentHousing.checked
    this.preference.sorted=sortingCriteria.value=="Sort by"? false:true 
    this.preference.sortingPreference.sortingCriteria=sortingCriteria.value
    this.preference.sortingPreference.ascending=order.value=="ascending"? true: false
    //this.searchData.filterDetails.infoSearchWord=textSearch.value
    //this.searchData.filterDetails.propertyType=type.value
    //this.searchData.filterDetails.citySearchWord=city.value
   // this.searchData.filterDetails.purchaseType=buyRent.value
   // this.searchData.filterDetails.university= university.value
   // this.searchData.filterDetails.maxPrice =Number(maxPrice.value)
    //this.searchData.filterDetails.minPrice =Number(minPrice.value)
   // this.searchData.filterDetails.minArea = Number(minArea.value)
   // this.searchData.filterDetails.maxArea =Number(maxArea.value)
   // this.searchData.filterDetails.withPictures= withPictures.checked
    //this.searchData.filterDetails.studentHousing=studentHousing.checked
    //this.searchData.sorted = sortingCriteria.value=="Sort by"? false:true 
    //this.searchData.sortingDetails.sortingCriteria= sortingCriteria.value
    //this.searchData.sortingDetails.ascending= order.value=="ascending"? true: false
   // var body =JSON.stringify(this.searchData)
    //console.log( body)
   
  //  this.http.post(this.url,body).subscribe((data:any)=>{
  //   console.log(data)
  //  })
     
  

    console.log(this.preference)
      this.serv.getPostsHomePage(this.preference).subscribe(results => {
          console.log("filteeeeer ", results)
          this.posts=results
        // Globals.setPosts(results)
      } );
   
  
   }
   getSavedPosts(){
    

   }

}

