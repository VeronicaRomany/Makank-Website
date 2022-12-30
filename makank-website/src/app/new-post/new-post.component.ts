import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { AbstractControl, FormControl, FormGroup, Validators } from '@angular/forms';
import { FormBuilder } from '@angular/forms';
import { Post } from '../shared/post';
import { NewPostService } from './service/new-post.service';
import { Globals } from 'src/globals';
import { ActivatedRoute, Router } from '@angular/router';
import { TokenStorageService } from '../_services/token-storage.service';




@Component({
  selector: 'app-new-post',
  templateUrl: './new-post.component.html',
  styleUrls: ['./new-post.component.css'],
  providers: [ Globals ],
})
export class NewPostComponent implements OnInit {
  

  newPostForm: FormGroup = new FormGroup({
    type: new FormControl(''),
    RentOrBuy: new FormControl(''),
    city: new FormControl(''),
    level: new FormControl(''),
    price: new FormControl(''),
    area: new FormControl(''),
    roomsNum : new FormControl(''),
    WCNum : new FormControl(''),
    address: new FormControl(''),
    info: new FormControl(''),
    elevator: new FormControl(''),
    studentHousing: new FormControl(''),
    pool: new FormControl(''),
    garden: new FormControl(''),
    universities : new FormControl('')
  });

  submitted = false;
  villa = false;
  appartment = false;
  photosLinks :string[]=[]
  index =0;
  editing=false
  file: any;
  event:any
  newPost : Post= new Post()
  editPost : Post=new Post()

  

  constructor(private token: TokenStorageService, private fb: FormBuilder,
     private readonly newpostservice : NewPostService, private http:HttpClient,
     private route:ActivatedRoute,private router:Router) { }

  ngOnInit(): void {

    this. newPostForm = this.fb.group(
      {
        type: ['', Validators.required],
        RentOrBuy: ['', Validators.required],
        city: ['',  Validators.required],
        level: [ '', Validators.required],
        price : [ '', Validators.required],
        area : [ '', Validators.required],
        roomsNum : [ '', Validators.required],
        WCNum : [ '', Validators.required],
        address: [ '', Validators.required],
        info: [ '', ''],
        elevator: [ '', ''],
        studentHousing: [ '', ''],
        pool: [ '', ''],
        garden: [ '', ''],
      },
    );


    this.route.queryParams.subscribe((params:any) =>{
      console.log(params.data)
      if(params.data!=null){
            this.editing=true
            console.log("edit post with id = ",params.data);
            this.editPost.postID=params.data

            console.log( this.editPost.postID)
            
            // get post by id   >> request lma a3mlo ams7 al dummy data de
            
            this.http.get<Post>("http://localhost:8080/posts/info/"+params.data.toString()).subscribe((data:any) =>{
              this.editPost=data
              console.log(this.editPost)
              if(this.editPost.rent){
                this. newPostForm.controls["RentOrBuy"].setValue("rent")
              }else{
                this. newPostForm.controls["RentOrBuy"].setValue("buy")
              }
        
              if(this.editPost.hasPictures){
                for (let i = 0; i < this.editPost.pictures.length; i++) {
                  this.photosLinks[i]=this.editPost.pictures[i]
              }
            }
        
              this. newPostForm.controls["type"].setValue(this.editPost.type)
              this.onChooseType()
              this. newPostForm.controls["city"].setValue(this.editPost.city)
              this. newPostForm.controls["level"].setValue(this.editPost.level)
              this. newPostForm.controls["price"].setValue(this.editPost.price)
              this. newPostForm.controls["area"].setValue(this.editPost.area)
              this. newPostForm.controls["roomsNum"].setValue(this.editPost.roomNumber)
              this. newPostForm.controls["WCNum"].setValue(this.editPost.bathroomNumber)
              this. newPostForm.controls["address"].setValue(this.editPost.address)
              this. newPostForm.controls["info"].setValue(this.editPost.info)
            })


            // this. editPost.city = "alex"
            // this. editPost.price= 10000
            // this. editPost.area= 150
            // this. editPost.address="El3asfraaaa"
            // this .editPost.level=12
            // this. editPost.roomNumber=12
            // this. editPost.bathroomNumber=12
            // this. editPost.elevator=true
            // this. editPost.studentHousing=true
            // this. editPost.hasGarden=true
            // this. editPost.hasPool=true
            // this. editPost.info=""
            // this. editPost.rent=true
            // this. editPost.pictures=[]
      

            // nms7 l7d hena
            
            
      } })
  }


  get f(): { [key: string]: AbstractControl } {
    return this.newPostForm.controls;
  }



  onSubmit(): void {
    this.submitted = true;
    if (this.newPostForm.invalid) {
      return;
    }

  

    var headers=new HttpHeaders().append("Authorization","Bearer "+this.token.getUser().token)

    console.log("create new post")

   
    this.newPost.type = this.f['type'].value
    this.newPost.city = this.f['city'].value
    this.newPost.price= this.f['price'].value
    this.newPost.area= this.f['area'].value
    this.newPost.address=this.f['address'].value
    this.newPost.level=this.f['level'].value
    this.newPost.roomNumber=this.f['roomsNum'].value
    this.newPost.bathroomNumber=this.f['WCNum'].value
    this.newPost.info=this.f['info'].value
    this.newPost.sellerID=this.token.getUser().userId;

    if(this.villa){
      this.newPost.hasGarden=(document.getElementById("garden") as HTMLInputElement).checked
      this.newPost.hasPool=(document.getElementById("pool") as HTMLInputElement).checked
      this.newPost.elevator=false
      this.newPost.studentHousing=false
    }

    if(this.appartment){
      this.newPost.elevator=(document.getElementById("elev") as HTMLInputElement).checked
      this.newPost.studentHousing=(document.getElementById("student") as HTMLInputElement).checked
      this.newPost.hasGarden=false
      this.newPost.hasPool=false
    }


 

    if(this.f['RentOrBuy'].value=="rent"){
      this.newPost.rent=true
    }else{
      this.newPost.rent=false
    }
    let j=0;
    for (let i = 0; i < this.photosLinks.length; i++) {
      if(this.photosLinks[i]){
        this.newPost.pictures[j++]=this.photosLinks[i]
      }
    }


    console.log(this.newPost)

    var NewPostJsonString = JSON.stringify(this.newPost)
    console.log(NewPostJsonString)

    if(this.editing){
      
      this.http.post("http://localhost:8080/posts/edit",JSON.parse(NewPostJsonString),{headers: headers}).subscribe((data:any) =>{
        window.alert("Your post has be eddited")
        this.router.navigate(['/', 'Home'])
      })
    }else{

      this.http.post("http://localhost:8080/posts/new",JSON.parse(NewPostJsonString),{headers: headers}).subscribe((data:any) =>{
        this.router.navigate(['/', 'Home'])
      })
     
    }

   
    
  }


  onEdit():void{
    this.newPost.postID=this.editPost.propertyID
    console.log( this.newPost.postID)
    this.onSubmit()
  }

  onChooseType(): void{
        console.log(this.f['type'].value)
        if(this.f['type'].value=="villa"){
            this.villa = true
            this.appartment= false;
        }

        if(this.f['type'].value=="apartment"){
          this.appartment= true
          this.villa= false;
      }
  }



  onReset(): void {
    this.submitted = false;
    this.appartment = false;
    this.villa = false;
    this.photosLinks=[];
    this.newPostForm.reset();
  }

  
  RemoveMe(index:number){
    console.log(index)
     this.photosLinks.splice(index,1)
     console.log(this.photosLinks)
  }


  SelectFile(event:any){
    this.file = event.target.files[0];
    this.newpostservice.upload(this.file).subscribe((url: string) => this. photosLinks[this.index++]=url )
  }

}

