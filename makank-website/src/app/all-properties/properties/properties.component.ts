import { HttpClient } from '@angular/common/http';
import { Component, Injectable, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { TokenStorageService } from 'src/app/_services/token-storage.service';
import { Globals } from 'src/globals';
import { MatDialog } from '@angular/material/dialog';

import { Post } from '../../shared/post';
import { Property, Villa } from '../../shared/property';
import { ViewingPreference } from '../../shared/viewingPreference';


import { LargeViewComponent } from 'src/app/large-view/large-view.component';
import { PropertiesService } from '../services/properties.service';
import { SharedService } from 'src/app/shared.service';
import { Subscription } from 'rxjs';
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
  saved:number[]=[]
  userID:number=0
  condition:boolean=false
  goToEdit:boolean=false
  editedID:number=0
  loggedIn:boolean=false
  currentPage:number=0
  numOfPosts:number=0
  postFlag:boolean=false
  maxPagesNum:number=0
  clickEventsubscription:Subscription;
  constructor(private service:PropertiesService,private router:Router,private token: TokenStorageService, public dialog:MatDialog, private http:HttpClient,private sharedService:SharedService,private route:ActivatedRoute) {
    this.serv= service
  this.clickEventsubscription=    this.sharedService.getClickEvent().subscribe(()=>{
    
  this.getSavedPost();
})
  }

  ngOnInit(): void {
    
    this.currentPage=0
    this.postFlag=false
    this.loggedIn = !!this.token.getToken();
    if(this.loggedIn){
      this.getSavedPostsIds();
    }
    this.route.queryParams.subscribe((params:any) =>{
      console.log(params.data+" <<<<<<<<<<<<<<<<<<<<<<<<<<")
      if(params.data!=null &&params.data=="saved"){
        
        this.getSavedPost()
      }else{
        this.sendPostsRequests();
      }
    }
    
    )
        
   
  
    

  }

  sendPostsRequests(){
    this.userID = this.token.getUser().userId;
    console.log(this.preference)
      this.serv.getPostsHomePage(this.preference,0).subscribe(results => {
          console.log("ana rg3t", results)
          this.posts=results
      } );
      this.serv.getPostsHomePageCounter(this.preference,this.currentPage).subscribe(results=>{
        this.numOfPosts=results
        this.maxPagesNum=Math.ceil(this.numOfPosts/10);
      })
  }
  getSavedPostsIds(){

    let userID = this.token.getUser().userId;
 this.serv.getIds(userID).subscribe(results =>{
  console.log("idsssss ", results)
   this.saved=results
 })
  }
  checkSaved(id:number){


     for(let i =0 ; i< this.saved.length;i++){
      if(this.saved[i]==id){
        return true;
      }
     }
     return false;
  }
  toggle(id:number){
   if(this.loggedIn){
    console.log(id);
    let userID = this.token.getUser().userId;
    var st : string = String(id);
      var btn= document.getElementById(st) as HTMLImageElement;
      console.log(btn+" yaaaaa3m aho");
      
      if (btn?.style.color=="orange"){
        btn.style.color = "grey";
        var ob={
          userID:userID ,
          postID:id
        }
        var unsavedPostJsonString = JSON.stringify(ob)

        console.log("unsaving post " + unsavedPostJsonString);
        this.http.post("http://localhost:8080/posts/unsavePost",ob,{responseType:'text'}).subscribe((data:any) =>{ })


      }
      else{
        btn!.style.color = "orange";

        var ob={
          userID:userID ,
          postID:id
        }
        var savedPostJsonString = JSON.stringify(ob)
        console.log("saving post " + savedPostJsonString);

        this.http.post("http://localhost:8080/posts/savePost",ob,{responseType:'text'}).subscribe((data:any) =>{ })
      }
   }else{
    alert("Login or Register !");
   }
  }
  getSavedPost(){
    if(this.loggedIn){
    this.getSavedPostsIds();
    this.postFlag=true
    this.currentPage=0
    let userID = this.token.getUser().userId;
    this.serv.getSavedPosts(userID,this.preference,0).subscribe(results => {


      console.log("saveeed", results)
      this.posts=results
    })
    this.serv.getSavedPostsCounter(this.userID,this.preference,0).subscribe(results => {
      console.log("saveeed", results)
     
      this.numOfPosts=results
      this.maxPagesNum=Math.ceil(this.numOfPosts/10);
    })
     }else{
      alert("Login or Register !");
     }
  }

openLargeView(postID:number ,propertyType:string){

 this.dialog.open(LargeViewComponent,{data:{postId:postID ,type:propertyType}});
}



  editMypost(postID:number){
      console.log(postID)
      this.editedID=postID
      this.goToEdit=true

      this.router.navigate([ '/','NewPost'],{queryParams:{data:postID}})
  }

  deleteMypost(postID:number){
    console.log(postID)
    this.serv.deletePost(postID).subscribe(results=> {
          this.sendPostsRequests()
    })
}


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




    console.log(this.preference.filterPreference.studentHousing+"shoof b2a ")
    if(this.loggedIn){
      this.getSavedPostsIds();
    }
    
      this.serv.getPostsHomePage(this.preference,0).subscribe(results => {
          console.log("filteeeeer ", results)
          this.posts=results
        // Globals.setPosts(results)
      } );
      this.serv.getPostsHomePageCounter(this.preference,this.currentPage).subscribe(results=>{
        this.numOfPosts=results
        this.maxPagesNum=Math.ceil(this.numOfPosts/10);
      })

   }
   nextPage(){
    this.currentPage++
    if(!this.postFlag){
      this.serv.getPostsHomePage(this.preference,this.currentPage).subscribe(results => {
        console.log("ana rg3t", results)
        this.posts=results
      } );
          this.serv.getPostsHomePageCounter(this.preference,this.currentPage).subscribe(results=>{
            this.numOfPosts=results
            this.maxPagesNum=Math.ceil(this.numOfPosts/10);
          })
    }
    else{
      this.serv.getSavedPosts(this.userID,this.preference,0).subscribe(results => {
        console.log("saveeed", results)
        this.posts=results
      });
      this.serv.getSavedPostsCounter(this.userID,this.preference,0).subscribe(results => {
        console.log("saveeed counter", results)
        this.numOfPosts=results
        this.maxPagesNum=Math.ceil(this.numOfPosts/10);
      });
    }
   }
  previousPage(){
    if(this.currentPage>0){
      this.currentPage--
      if(!this.postFlag){
        this.serv.getPostsHomePage(this.preference,this.currentPage).subscribe(results => {
          console.log("ana rg3t", results)
          this.posts=results
        } );
        this.serv.getPostsHomePageCounter(this.preference,this.currentPage).subscribe(results=>{
          this.numOfPosts=results
          this.maxPagesNum=Math.ceil(this.numOfPosts/10);
          console.log("post NUM ", results)
        })
      }
      else{
        this.serv.getSavedPosts(this.userID,this.preference,0).subscribe(results => {
          console.log("saveeed", results)
          this.posts=results
        });
        this.serv.getSavedPostsCounter(this.userID,this.preference,0).subscribe(results => {
          console.log("saveeed", results)
         
          this.numOfPosts=results
          this.maxPagesNum=Math.ceil(this.numOfPosts/10);
        })
      }
    }
  }
  checkPrevious() {
    if(this.currentPage>0){
      return true
    }
    return false
  }
  checkNext(){
    if(this.currentPage<Math.ceil(this.numOfPosts/10)-1){
     
      return true
    }
    return false
  }
}

