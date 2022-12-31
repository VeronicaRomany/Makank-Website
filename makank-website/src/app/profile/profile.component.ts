import { Component, OnInit } from '@angular/core';
import { ProfileService } from '../profile/services/profile.service';
import {Globals} from "../../globals";
import { User } from 'src/app/shared/user';
import { Router } from '@angular/router';
import {Post} from "../shared/post";
import {ViewingPreference} from "../shared/viewingPreference";
import {LargeViewComponent} from "../large-view/large-view.component";
import {MatDialog} from "@angular/material/dialog";
import {HttpClient} from "@angular/common/http";
import {TokenStorageService} from "../_services/token-storage.service";

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {
  posts: Post[] = []
  preference: ViewingPreference = new ViewingPreference()
  currentUser: any;
  currentUserInfo: User = new User();
  notLogIn = true
  userID: number = 0;
  saved:number[]=[]
  condition:boolean=false
  goToEdit:boolean=false
  editedID:number=0
  loggedIn:boolean=false
  currentPage:number=0
  postFlag:boolean=false
  numOfPosts:number=0
 
  maxPagesNum:number=0
  constructor(private token: TokenStorageService, private profile: ProfileService, private router: Router, public dialog:MatDialog, private http:HttpClient) {
  }

  ngOnInit(): void {
    this.currentPage=0
    this.postFlag=false
    this.currentUser = this.token.getUser();
    this.userID = this.token.getUser().userId;
    if (this.currentUser.username != undefined) {
      this.notLogIn = false
      console.log("ii")
    }
    this.profile.getUserInfo(this.userID).subscribe(result => {
      this.currentUserInfo = result
      console.log(result)
      this.profile.setUser(result);
    })

    this.profile.getPostsOfTheUser(this.preference, this.userID,0).subscribe(results => {
      console.log("ana rg3t", results)
      this.posts = results
    });
    this.profile.getPostsOfTheUserCounter(this.preference,this.userID,this.currentPage).subscribe(results => {
      console.log("ana rg3t", results)
      this.numOfPosts=results
      this.maxPagesNum=Math.ceil(this.numOfPosts/10);
    } );
    this.loggedIn = !!this.token.getToken();
    if(this.loggedIn) {
      this.getSavedPostsIds();
    }
  }

  getSavedPostsIds() {

    let userID = this.token.getUser().userId;
    this.profile.getIds(userID).subscribe(results => {
      console.log("idsssss ", results)
      this.saved = results
    })
  }
 editProfile(){
  this.router.navigate(['/', 'Edit'])
 }
  checkSaved(id: number) {


    for (let i = 0; i < this.saved.length; i++) {
      if (this.saved[i] == id) {
        return true;
      }
    }
    return false;
  }
 noPic(){
  
 }
  toggle(id: number) {
    if (this.loggedIn) {
      console.log(id);
      let userID = this.token.getUser().userId;
      var st: string = String(id);
      var btn = document.getElementById(st);
      if (btn?.style.color == "orange") {
        btn.style.color = "grey";
        var ob = {
          userID: userID,
          postID: id
        }
        var unsavedPostJsonString = JSON.stringify(ob)

        console.log("unsaving post " + unsavedPostJsonString);
        this.http.post("http://localhost:8080/posts/unsavePost", ob, {responseType: 'text'}).subscribe((data: any) => {
        })


      } else {
        btn!.style.color = "orange";

        var ob = {
          userID: userID,
          postID: id
        }
        var savedPostJsonString = JSON.stringify(ob)
        console.log("saving post " + savedPostJsonString);

        this.http.post("http://localhost:8080/posts/savePost", ob, {responseType: 'text'}).subscribe((data: any) => {
        })
      }
    } else {
      alert("Login or Register !");
    }
  }



  openLargeView(postID: number, propertyType: string) {

    this.dialog.open(LargeViewComponent, {data: {postId: postID, type: propertyType}});
  }


  editMypost(postID: number) {
    console.log(postID)
    this.editedID = postID
    this.goToEdit = true

    this.router.navigate(['/', 'NewPost'], {queryParams: {data: postID}})
  }
  sendPostsRequests(){
    this.userID = this.token.getUser().userId;
    console.log(this.preference)
    this.profile.getPostsOfTheUser(this.preference,this.userID,0).subscribe(results => {
      console.log("ana rg3t", results)
      this.posts=results
    } );
    this.profile.getPostsOfTheUserCounter(this.preference,this.userID,this.currentPage).subscribe(results => {
      console.log("aya m3lmmmmmmmmm ", results)
      this.numOfPosts=results
      this.maxPagesNum=Math.ceil(this.numOfPosts/10);
    } );

  }
  deleteMypost(postID: number) {
    console.log(postID)
    this.profile.deletePost(postID).subscribe(results => {
      this.sendPostsRequests()
    })
  }

  goTonewPost(): void {

    this.router.navigate(['/', 'NewPost'])
  }

  getInfo() {
    var textSearch = document.getElementById('searchText') as HTMLInputElement
    var type = document.getElementById('format1') as HTMLSelectElement
    var city = document.getElementById('format2') as HTMLSelectElement
    var buyRent = document.getElementById('format3') as HTMLSelectElement
    var university = document.getElementById('format4') as HTMLSelectElement
    var minPrice = document.getElementById('minPrice') as HTMLInputElement
    var maxPrice = document.getElementById('maxPrice') as HTMLInputElement
    var minArea = document.getElementById('minArea') as HTMLInputElement
    var maxArea = document.getElementById('maxArea') as HTMLInputElement
    var withPictures = document.getElementById('c1') as HTMLInputElement
    var studentHousing = document.getElementById('c2') as HTMLInputElement
    var sortingCriteria = document.getElementById('sort') as HTMLSelectElement
    var order = document.getElementById('order') as HTMLSelectElement
    if (Number(minPrice.value) > Number(maxPrice.value)) {
      console.log("priceee++++++")
      //console.log(minPrice,maxPrice)
      var temp = minPrice.value
      minPrice.value = maxPrice.value
      maxPrice.value = temp
    }

    if (Number(minArea.value) > Number(maxArea.value)) {
      console.log("Areaaaa++++++")
      var temp = minArea.value
      minArea.value = maxArea.value
      maxArea.value = temp
    }
    if (textSearch.value == '' && (type.value == "Type" || type.value == "any") && (city.value == "City" || city.value == "any")
      && (buyRent.value == "Bye/Rent" || buyRent.value == "any") && Number(maxPrice.value) == 0 && Number(minPrice.value) == 0 && Number(maxArea.value) == 0
      && Number(minArea.value) == 0 && withPictures.checked == false && studentHousing.checked == false
    ) {
      this.preference.filtered = false
    } else {
      this.preference.filtered = true
    }
    this.preference.filterPreference.infoSearchWord = textSearch.value
    this.preference.filterPreference.propertyType = type.value
    this.preference.filterPreference.citySearchWord = city.value
    this.preference.filterPreference.purchaseChoice = buyRent.value
    this.preference.filterPreference.maxPrice = Number(maxPrice.value) == 0 ? -1 : Number(maxPrice.value)
    this.preference.filterPreference.minPrice = Number(minPrice.value) == 0 ? -1 : Number(minPrice.value)
    this.preference.filterPreference.minArea = Number(minArea.value) == 0 ? -1 : Number(minArea.value)
    this.preference.filterPreference.maxArea = Number(maxArea.value) == 0 ? -1 : Number(maxArea.value)
    this.preference.filterPreference.withPictures = withPictures.checked
    this.preference.filterPreference.studentHousing = studentHousing.checked
    this.preference.sorted = sortingCriteria.value == "Sort by" ? false : true
    this.preference.sortingPreference.sortingCriteria = sortingCriteria.value
    this.preference.sortingPreference.ascending = order.value == "ascending" ? true : false


    console.log(this.preference)
    this.getSavedPostsIds();
    this.profile.getPostsOfTheUser(this.preference,this.userID,0).subscribe(results => {
      console.log("filteeeeer ", results)
      this.posts = results
      // Globals.setPosts(results)
    });


  }
  nextPage(){
    this.currentPage++
    if(!this.postFlag){
      this.profile.getPostsOfTheUser(this.preference,this.userID,this.currentPage).subscribe(results => {
        console.log("ana rg3t", results)
        this.posts=results
      } );
      this.profile.getPostsOfTheUserCounter(this.preference,this.userID,this.currentPage).subscribe(results => {
        console.log("ana rg3t", results)
        this.numOfPosts=results
        this.maxPagesNum=Math.ceil(this.numOfPosts/10);
      } );
    }
    else{
      this.profile.getSavedPosts(this.userID,this.preference,0).subscribe(results => {
        console.log("saveeed", results)
        this.posts=results
      })
    }
  }
  previousPage(){
    if(this.currentPage>0){
      this.currentPage--
      if(!this.postFlag){
        this.profile.getPostsOfTheUser(this.preference,this.userID,this.currentPage).subscribe(results => {
          console.log("ana rg3t", results)
          this.posts=results
        } );
        this.profile.getPostsOfTheUserCounter(this.preference,this.userID,this.currentPage).subscribe(results => {
          console.log("ana rg3t", results)
          this.numOfPosts=results
          this.maxPagesNum=Math.ceil(this.numOfPosts/10);
        } );
      }
      else{
        this.profile.getSavedPosts(this.userID,this.preference,0).subscribe(results => {
          console.log("saveeed", results)
          this.posts=results
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
      console.log(" e "+this.currentPage + " : "+Math.ceil(this.numOfPosts/10));
      
      return true
    }
    return false
  }
}
