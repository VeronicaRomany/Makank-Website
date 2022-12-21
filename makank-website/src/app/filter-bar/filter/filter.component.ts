import { HttpClient } from '@angular/common/http';
import { Component, OnInit, ViewChild } from '@angular/core';
import { PropertiesComponent } from 'src/app/all-properties/properties/properties.component';
import { ViewingPreference } from 'src/app/shared/viewingPreference';
import { Globals } from 'src/globals';
import { FilterService } from '../services/filter.service';

@Component({
  selector: 'app-filter',
  templateUrl: './filter.component.html',
  styleUrls: ['./filter.component.css'],
  providers: [ Globals ],
})

export class FilterComponent implements OnInit {
 
  serv:FilterService
  preference:ViewingPreference=new ViewingPreference()
   url ="http://localhost:8080/users/posts/homepage"
   
  //  searchData={
  //   filterd:false,
  //   sorted:false,
  //   sortingDetails:
  //   {
  //      ascending:true,
  //      sortingCriteria:''
  //   },
  //   filterDetails:
  //   {
  //   infoSearchWord:'',
  //   propertyType:'',
  //    citySearchWord:'',
  //    purchaseType:'',
  //    university:'',
  //    minPrice:0,
  //    maxPrice:100000000000000,
  //    minArea:0,
  //    maxArea:1000000000000000,
  //    withPictures:false,
  //    studentHousing:false
  //  }
  //  }
  constructor(private service:FilterService,private http:HttpClient) { 
    this.serv= service
  }

  ngOnInit(): void {
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
  &&  (buyRent.value=="Bye/Rent"||type.value=="any")&&  Number(maxPrice.value)==-1 &&Number(minPrice.value)==-1&&Number(maxArea.value)==-1
  &&Number(minArea.value)==-1 && withPictures.checked==false && studentHousing.checked == false
  ){
     this.preference.filtered=false
  }else{
    this.preference.filtered=true
  }
  this.preference.filterPreference.infoSearchWord= textSearch.value
  this.preference.filterPreference.propertyType=type.value
  this.preference.filterPreference.citySearchWord=city.value
  this.preference.filterPreference.purchaseChoice=buyRent.value
  this.preference.filterPreference.maxPrice=Number(maxPrice.value)
  this.preference.filterPreference.minPrice=Number(minPrice.value)
  this.preference.filterPreference.minArea= Number(minArea.value)
  this.preference.filterPreference.maxArea=Number(maxArea.value)
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
   

this.serv.getFilteredPosts(this.preference).subscribe(results => {
  console.log("filtered hna ", results)
  
  //this.posts=results
} );

 }
}
