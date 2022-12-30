import { HttpClient } from '@angular/common/http';
import { Component, OnInit, Output } from '@angular/core';
import { AbstractControl, FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';



import {  ValidatorFn } from '@angular/forms';
import { Router } from '@angular/router';

import { Observable } from 'rxjs';
import { DataReturned } from 'src/app/login/services/auth.service.service';
import { User } from 'src/app/user';
import { AuthService } from 'src/app/_services/auth.service.service';
import { TokenStorageService } from 'src/app/_services/token-storage.service';
import { Globals } from 'src/globals';

import { RegisterService } from '../service/register.service';




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

  urllink:string="";
  phoneLen : boolean = false;
  phone : String=""

  
  file:File={
    lastModified: 0,
    name: '',
    webkitRelativePath: '',
    size: 0,
    type: '',
    arrayBuffer: function (): Promise<ArrayBuffer> {
      throw new Error('Function not implemented.');
    },
    slice: function (start?: number | undefined, end?: number | undefined, contentType?: string | undefined): Blob {
      throw new Error('Function not implemented.');
    },
    stream: function (): ReadableStream<Uint8Array> {
      throw new Error('Function not implemented.');
    },
    text: function (): Promise<string> {
      throw new Error('Function not implemented.');
    }
  }

  newAccount:User= new User()
  
  
  

  constructor(private formBuilder: FormBuilder, private http:HttpClient,private readonly registerservice: RegisterService,
    private authService: AuthService, private tokenStorage: TokenStorageService,private router:Router) {}

  ngOnInit(): void {
    this.form = this.formBuilder.group(
      {
        fullname: ['', Validators.required],
        username: [
          '',
          [
            Validators.required,Validators.pattern("^[A-Za-z][A-Za-z0-9]*$")
          ]
        ],
        email: ['', [ Validators.email,Validators.pattern("^[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,4}$")]],
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
    this.phone  = this.f['phoneNumber'].value
    console.log(this.phone)
    if(this.phone.length == 10){
      console.log("e2fsh")
      this.phoneLen=true;
      return;
    }

    if (this.form.invalid) {
      return;
    }

    var jsonString = JSON.stringify(this.form.value, null, 2)
    console.log(jsonString);
   
    this.f['profilePicture'].setValue (this.urllink)
  
    this.newAccount.name = this.f['fullname'].value
    this.newAccount.password=this.f['password'].value
    this.newAccount.username = this.f['username'].value
    this.newAccount.phone_numbers[0] = this.f['phoneNumber'].value
    this.newAccount.email = this.f['email'].value
    this.newAccount.description = this.f['description'].value
    this.newAccount.address = this.f['address'].value
    this.newAccount.profile_pic_link =this.f['profilePicture'].value

   
    var NewAccountJsonString = JSON.stringify(this.newAccount)
    console.log(NewAccountJsonString)
    this.urllink=""

    this.http.post<DataReturned>("http://localhost:8080/users/new",JSON.parse(NewAccountJsonString)).subscribe((dataReturned) =>{
      let data=dataReturned.id  
      let token=dataReturned.token
      console.log(dataReturned,data,token)
      if(data>0){

        console.log("ana 3mlt register")
      console.log(data);
      this.authService.login(this.newAccount.username, this.newAccount.password).subscribe((user)=> {
        console.log("ana 3mlt login")
        console.log(user)
        let data=dataReturned.id  
        let token=dataReturned.token
        if(data > 0){
          console.log("successfully login")
          console.log(data)
          this.tokenStorage.saveToken(this.newAccount.username);
          this.tokenStorage.saveUser({"username":this.newAccount.username,"password":this.newAccount.password,"userId":data,"token":token});
          console.log(this.tokenStorage.getUser())
          Globals.setUserID(data)
          this.router.navigate(['/', 'Home'])
        }
      
    },);
      }else if(data ==-1){
        console.log("taken user name")
        window.alert("This username is already taken")
      

  }},);
}

  onReset(): void {
    this.submitted = false;
    this.urllink="";
    this.phoneLen=false;
    this.form.reset();
  }



  


  SelectFile(event:any){
    this.file = event.target.files[0];


    this.registerservice.upload(this.file).subscribe(url => this.urllink=url)
    

    console.log(this.urllink)
    
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
