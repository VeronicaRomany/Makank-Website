import { Component, OnInit,Inject } from '@angular/core';
import { MAT_DIALOG_DATA } from '@angular/material/dialog';
import { LargeViewService } from './large-view.service';
import { PostLargeView } from './postLargeView';
@Component({
  selector: 'app-large-view',
  templateUrl: './large-view.component.html',
  styleUrls: ['./large-view.component.css']
})
export class LargeViewComponent implements OnInit {
  serv:LargeViewService
  constructor(@Inject(MAT_DIALOG_DATA) public data:{postId:number ,type:string},private service:LargeViewService) {
    this.serv =this.service
   }
  largePost= new PostLargeView();
  
  ngOnInit(): void {
   console.log ( this.data.postId );
   this.getDummyData();
     // this.getLargePost(this.data.postId);
  }

   getLargePost(postId:number){
    this.serv.getLargePost(postId).subscribe(results=>{
      this.largePost=results
    })

   }
isApartment(){
  return this.largePost.type=="apartment";
}
  getDummyData(){
    this.largePost.area=500
    this.largePost.bathrooms=4
    this.largePost.city="Cairo"
    this.largePost.date="22/12/2022"
    this.largePost.elevator=true
    this.largePost.email="marko@gmail"
    this.largePost.garden=true
    this.largePost.info="Lorem ipsum, dolor sit amet consectetur adipisicing elit. Natus temporibus corporis repudiandae, consectetur nostrum nisi commodi placeat rerum molestias numquam nihil accusantium deleniti! Enim, nesciunt a quis amet hic officia. Lorem ipsum dolor sit amet consectetur adipisicing elit. Molestiae nemo accusantium tempora facere doloremque cum iusto."
  this.largePost.level=3
  this.largePost.phone_number="0123131431"
  this.largePost.price=250000
  this.largePost.property_address="45 shar3 9"
  this.largePost.rent=false
  this.largePost.rooms=5
  this.largePost.seller_name="abo iskandar"
  this.largePost.student_housing=true
  this.largePost.type=this.data.type
  }

}
