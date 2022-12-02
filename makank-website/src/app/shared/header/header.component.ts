import { Component, OnInit } from '@angular/core';
import { FilterPreference, SortingPreference, ViewingPreference } from '../viewingPreference';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

  constructor() { }

  ngOnInit(): void {
  }

  temp(){
    let v:ViewingPreference = new ViewingPreference()
    let f:FilterPreference = new FilterPreference()
    let s:SortingPreference = new SortingPreference()

    f.withPictures=false
    f.addressSearchWord= "45"
    f.propertyType="Villa"
    f.purchaseChoice="buy"
    f.studentHousing=false
    
    s.ascending=true
    s.sortingCriteria="price"
    v.filterPrereferene=f
    v.sortingPreference=s
    v.sorted=false
    v.filtered=true
    console.log(JSON.stringify(v));
    
  }

}
