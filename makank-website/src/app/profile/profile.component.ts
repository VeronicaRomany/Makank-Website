import { Component, OnInit } from '@angular/core';
import { TokenStorageService } from '../login/services/token-storage.service';
import { ProfileService } from '../profile/services/profile.service';
import {Globals} from "../../globals";
import { User } from 'src/app/shared/user';
import { Router } from '@angular/router';
import {Post} from "../shared/post";
import {ViewingPreference} from "../shared/viewingPreference";

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {
  posts:Post[] = []
  preference:ViewingPreference=new ViewingPreference()
  currentUser: any;
  currentUserInfo: User = new User();
  notLogIn=true
  private userID: number = 0;
  private pho: string =""

  constructor(private token: TokenStorageService,private profile: ProfileService,private router:Router) { }

  ngOnInit(): void {
    this.currentUser = this.token.getUser();
    this.userID = this.token.getUser().userId;
    if(this.currentUser.username!=undefined){
      this.notLogIn=false
      console.log("ii")
    }
    this.profile.getUserInfo(this.userID).subscribe(result=>{this.currentUserInfo=result
    console.log(result)})

    this.profile.getPostsOfTheUser(this.preference,this.userID).subscribe(results => {
    console.log("ana rg3t", results)
    this.posts=results
    });
    // this.profile.getUserPhone(this.userID).subscribe(res=>{this.pho=res})
    // this.currentUserInfo.phone=this.pho
    // console.log(this.pho)
}
  getSavedPost(){
    let userID = this.token.getUser().userId;
    this.profile.getSavedPosts(userID,this.preference).subscribe(results => {
      console.log("saveeed", results)
      this.posts=results
    })
  }
  goTonewPost():void{

    this.router.navigate([ '/','NewPost'])
  }
  getInfo(){
    let userID = this.token.getUser().userId;
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
    this.profile.getPostsOfTheUser(this.preference,userID).subscribe(results => {
      console.log("filteeeeer ", results)
      this.posts=results
      // Globals.setPosts(results)
    } );


  }
}
