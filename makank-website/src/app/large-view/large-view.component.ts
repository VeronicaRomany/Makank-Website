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
  largePost:PostLargeView= new PostLargeView()
  constructor(@Inject(MAT_DIALOG_DATA) public data:{postId:number ,type:string},private service:LargeViewService) {
    this.serv =this.service
   }
 
  
   images:string[]=[]
   counter:number=0
  ngOnInit(): void {
   console.log ( this.data.postId );
   //this.getDummyData();
   this.getLargePost(this.data.postId);
   console.log("tb eeeeh");
   
   
   console.log(this.largePost);
   

  }
changePic(){
  if(this.images.length>1){
 this.counter=(this.counter+1)%this.images.length;
var  image =document.getElementById('image1') as HTMLImageElement;
image.setAttribute("src",this.images[this.counter]);

console.log("chaaaange");
  }

}
firstPic(){
  return this.counter==0
}
lastPic(){
  if(this.counter==this.images.length-1){
    return true
  }
  else{return false}
}
noPictures(){
  if(this.images[0]==null){
    console.log("mafiiish");
    
    return true;

  }
  console.log("feeeeeeh");
  
  return false
}
   getLargePost(postId:number){
   this.serv.getLargePost(postId).subscribe(results=>{
     console.log(results.area);
      this.largePost= results
      console.log("reeeq");
      this.images=this.largePost.pictures
      console.log(this.largePost.pictures[0]);
     console.log(this.largePost);
     
      
    })

   }
isApartment(){
  return this.largePost.type=="apartment";
}
     
  previousPic(){
    this.counter=(this.counter-1)%this.images.length;
    var  image =document.getElementById('image1') as HTMLImageElement;
    image.setAttribute("src",this.images[this.counter]);
  }
  noSellerPic(){
    return (this.largePost.seller_profile_pic==null)
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
