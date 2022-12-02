import { Component, OnInit, Output } from '@angular/core';
import { AbstractControl, FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';



import {  ValidatorFn } from '@angular/forms';
import { User } from 'src/app/user';




export default class Validation {
  static match(controlName: string, checkControlName: string): ValidatorFn {
    return (controls: AbstractControl) => {
      const control = controls.get(controlName);
      const checkControl = controls.get(checkControlName);

      if (checkControl?.errors && !checkControl.errors['matching']) {
        return null;
      }

      if (control?.value !== checkControl?.value) {
        controls.get(checkControlName)?.setErrors({ matching: true });
        return { matching: true };
      } else {
        return null;
      }
    };
  }
}


@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {
  form: FormGroup = new FormGroup({
    fullname: new FormControl(''),
    username: new FormControl(''),
    email: new FormControl(''),
    password: new FormControl(''),
    confirmPassword: new FormControl(''),
    phoneNumber: new FormControl(''),
    description : new FormControl(''),
    address : new FormControl(''),
    profilePicture: new FormControl('')
  });
  submitted = false;

  urllink:string=""
  
  newAccount:User= new User()
 

  constructor(private formBuilder: FormBuilder) {}

  ngOnInit(): void {
    this.form = this.formBuilder.group(
      {
        fullname: ['', Validators.required],
        username: [
          '',
          [
            Validators.required
          ]
        ],
        email: ['', [ Validators.email]],
        password: [
          '',
          [
            Validators.required,
            Validators.minLength(6),
            Validators.maxLength(40)
          ]
        ],
        confirmPassword: ['', Validators.required],
        phoneNumber: ['',[Validators.required,Validators.minLength(9),Validators.maxLength(11)]],
        description : ['',''],
        address : ['',''],
        profilePicture :['','']

      },
      {
        validators: [Validation.match('password', 'confirmPassword')]
      }
    );
  }

  get f(): { [key: string]: AbstractControl } {
    return this.form.controls;
  }

  onSubmit(): void {
    this.submitted = true;

    if (this.form.invalid) {
      return;
    }

    var jsonString = JSON.stringify(this.form.value, null, 2)
    console.log(jsonString);
   
  
    this.newAccount.name = this.f['fullname'].value
    this.newAccount.password=this.f['password'].value
    this.newAccount.username = this.f['username'].value
    this.newAccount.phoneNumber = this.f['phoneNumber'].value
    this.newAccount.email = this.f['email'].value
    this.newAccount.description = this.f['description'].value
    this.newAccount.address = this.f['address'].value
    this.newAccount.profilePicture=this.f['profilePicture'].value

   
    var NewAccountJsonString = JSON.stringify(this.newAccount)
    console.log(NewAccountJsonString)

   
  }

  onReset(): void {
    this.submitted = false;
    this.form.reset();
  }

  selectFile(event:any){
    if(event.target.files){
      var reader = new FileReader()
      reader.readAsDataURL(event.target.files[0])
      reader.onload = (event:any)=>{

        this.urllink = event.target.result
        this.f['profilePicture'].setValue (this.urllink)
        
      }
    }
  }
}
