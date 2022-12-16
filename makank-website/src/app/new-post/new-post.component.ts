import { Component, OnInit } from '@angular/core';
import { AbstractControl, FormControl, FormGroup, Validators } from '@angular/forms';
import { FormBuilder } from '@angular/forms';
import Validation from '../registration/register/register.component';

@Component({
  selector: 'app-new-post',
  templateUrl: './new-post.component.html',
  styleUrls: ['./new-post.component.css']
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
 
  alreadyCheckedElevator=false;
  alreadyCheckedPool=false;
  alreadCheckedGarden=false;
  alreadyCheckedStudent=false;

  

  constructor(private fb: FormBuilder) { }

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
    this.newPostForm.reset();
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

}
