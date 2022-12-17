import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { AbstractControl, FormControl, FormGroup, Validators } from '@angular/forms';
import { FormBuilder } from '@angular/forms';
import Validation from '../registration/register/register.component';
import { Post } from '../shared/post';
import { NewPostService } from './service/new-post.service';
import { Globals } from 'src/globals';

@Component({
  selector: 'app-new-post',
  templateUrl: './new-post.component.html',
  styleUrls: ['./new-post.component.css'],
  providers: [ Globals ],
})
export class NewPostComponent implements OnInit {
  [x: string]: any;

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
 
  alreadyCheckedElevator=false;
  alreadyCheckedPool=false;
  alreadCheckedGarden=false;
  alreadyCheckedStudent=false;
  photosLinks :string[]=[]
  index =0;
  file: any;

  newPost : Post= new Post()


  

  constructor(private fb: FormBuilder, private readonly newpostservice : NewPostService, private http:HttpClient,public global:Globals) { }

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
        universities :[ '', '']
      },
    );
  }


  get f(): { [key: string]: AbstractControl } {
    return this.newPostForm.controls;
  }



  onSubmit(): void {
    
    this.submitted = true;
    if (this.newPostForm.invalid) {
      return;
    }

    console.log("veroooooooooooo creates new Post")

    //fadel sellerID
    this.newPost.type = this.f['type'].value
    this.newPost.city = this.f['city'].value
    this.newPost.price= this.f['price'].value
    this.newPost.area= this.f['area'].value
    this.newPost.address=this.f['address'].value
    this.newPost.level=this.f['level'].value
    this.newPost.roomNumber=this.f['roomsNum'].value
    this.newPost.bathroomNumber=this.f['WCNum'].value
    this.newPost.elevator=this.alreadyCheckedElevator
    this.newPost.studentHousing=this.alreadyCheckedStudent
    this.newPost.hasGarden=this.alreadCheckedGarden
    this.newPost.hasPool=this.alreadyCheckedPool
    this.newPost.info=this.f['info'].value
    this.newPost.universities=this.f['universities'].value

    this.newPost.sellerID=this.global.getUserID()
    

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
    this.http.post("http://localhost:8080/posts/new",JSON.parse(NewPostJsonString),{responseType:'text'}).subscribe((data:any) =>{
      console.log(data);
      
    })


    

   
    
    
    
    
   
    
    
  }

  onChooseType(): void{
    console.log(this.f['type'].value)
    if(this.f['type'].value=="villa"){
        this.villa = true
        this.appartment= false
        this.alreadyCheckedElevator=false
        this.alreadyCheckedStudent=false
    }

    if(this.f['type'].value=="appartment"){
      this.appartment= true
      this.villa= false
      this.alreadCheckedGarden=false
      this.alreadyCheckedPool=false
  }
  
  }



  onReset(): void {
    this.submitted = false;
    this.appartment = false;
    this.villa = false;
    this.alreadCheckedGarden=false;
    this.alreadyCheckedElevator=false;
    this.alreadyCheckedPool=false;
    this.alreadyCheckedStudent=false;
    this.photosLinks=[];
    this.newPostForm.reset();
  }

  RemoveMe(index:number){
    console.log(index)
     this.photosLinks.splice(index,1)
    console.log(this.photosLinks)
  }

  handleChangeElevator(event:any) {
    var target = event.target;
    if(this.alreadyCheckedElevator){
      target.checked=false
      this.alreadyCheckedElevator=!this.alreadyCheckedElevator
      console.log(this.alreadyCheckedElevator)
      console.log(" not Checked")
    }else{
      target.checked=true;
      this.alreadyCheckedElevator=!this.alreadyCheckedElevator
      console.log(this.alreadyCheckedElevator)
      console.log("Checked")
    }
  }

  handleChangeStudent(event:any) {
    var target = event.target;
    if(this.alreadyCheckedStudent){
      target.checked=false
      this.alreadyCheckedStudent=!this.alreadyCheckedStudent
      console.log(this.alreadyCheckedStudent)
      console.log(" not Checked")
    }else{
      target.checked=true;
      this.alreadyCheckedStudent=!this.alreadyCheckedStudent
      console.log(this.alreadyCheckedStudent)
      console.log("Checked")
    }
  }


  handleChangePool(event:any) {
    var target = event.target;
    if(this.alreadyCheckedPool){
      target.checked=false
      this.alreadyCheckedPool=!this.alreadyCheckedPool
      console.log(this.alreadyCheckedPool)
      console.log(" not Checked")
    }else{
      target.checked=true;
      this.alreadyCheckedPool=!this.alreadyCheckedPool
      console.log(this.alreadyCheckedPool)
      console.log("Checked")
    }
  }

  handleChangeGarden(event:any) {
    var target = event.target;
    if(this.alreadCheckedGarden){
      target.checked=false
      this.alreadCheckedGarden=!this.alreadCheckedGarden
      console.log(this.alreadCheckedGarden)
      console.log(" not Checked")
    }else{
      target.checked=true;
      this.alreadCheckedGarden=!this.alreadCheckedGarden
      console.log(this.alreadCheckedGarden)
      console.log("Checked")
    }
  }


  SelectFile(event:any){
    this.file = event.target.files[0];
    this.newpostservice.upload(this.file).subscribe((url: string) => this. photosLinks[this.index++]=url )
    
    
  }

}
