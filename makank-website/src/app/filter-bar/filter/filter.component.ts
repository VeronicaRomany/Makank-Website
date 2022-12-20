import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-filter',
  templateUrl: './filter.component.html',
  styleUrls: ['./filter.component.css']
})
export class FilterComponent implements OnInit {
   url ="http://localhost:8080/users/posts/homepage"
   searchData={
    sorted:false,
    sortingDetails:
    {
       ascending:true,
       sortingCriteria:''
    },
    filterDetails:
    {
    infoSearchWord:'',
    propertyType:'',
     citySearchWord:'',
     purchaseType:'',
     university:'',
     minPrice:0,
     maxPrice:100000000000000,
     minArea:0,
     maxArea:1000000000000000,
     withPictures:false,
     studentHousing:false
   }
   }
  constructor(private http:HttpClient) { }

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
  this.searchData.filterDetails.infoSearchWord=textSearch.value
  this.searchData.filterDetails.propertyType=type.value
  this.searchData.filterDetails.citySearchWord=city.value
  this.searchData.filterDetails.purchaseType=buyRent.value
  this.searchData.filterDetails.university= university.value
  this.searchData.filterDetails.maxPrice =Number(maxPrice.value)
  this.searchData.filterDetails.minPrice =Number(minPrice.value)
  this.searchData.filterDetails.minArea = Number(minArea.value)
  this.searchData.filterDetails.maxArea =Number(maxArea.value)
  this.searchData.filterDetails.withPictures= withPictures.checked
  this.searchData.filterDetails.studentHousing=studentHousing.checked
  this.searchData.sorted = sortingCriteria.value=="Sort by"? false:true 
  this.searchData.sortingDetails.sortingCriteria= sortingCriteria.value
  this.searchData.sortingDetails.ascending= order.value=="ascending"? true: false
  var body =JSON.stringify(this.searchData)
  console.log( body)
 
 this.http.post(this.url,body).subscribe((data:any)=>{
  console.log(data)
 })
 
 }
}
