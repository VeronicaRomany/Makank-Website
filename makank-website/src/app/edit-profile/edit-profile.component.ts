import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Component, OnInit, Output } from '@angular/core';
import { AbstractControl, FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';



import {  ValidatorFn } from '@angular/forms';
import { Router } from '@angular/router';

import { Observable } from 'rxjs';
import { User } from 'src/app/user';
import { AuthService } from 'src/app/_services/auth.service.service';
import { TokenStorageService } from 'src/app/_services/token-storage.service';
import { Globals } from 'src/globals';
import { RegisterService } from '../registration/service/register.service';
import { ProfileComponent } from '../profile/profile.component';
import { ProfileService } from '../profile/services/profile.service';


export default class Validation {
  static match(controlName: string, checkControlName: string): ValidatorFn {
    
    return (controls: AbstractControl) => {
      
      const checkControl = controls.get(checkControlName);

      if (checkControl?.errors && !checkControl.errors['matching']) {
        return null;
      }
      if (controlName !== checkControl?.value) {
        controls.get(checkControlName)?.setErrors({ matching: true });
        return { matching: true };
      } else {
        return null;
      }
    };
  }
}
@Component({
  selector: 'app-edit-profile',
  templateUrl: './edit-profile.component.html',
  styleUrls: ['./edit-profile.component.css']
})
export class EditProfileComponent implements OnInit {

  form: FormGroup = new FormGroup({
    fullname: new FormControl(''),
    username: new FormControl(''),
    email: new FormControl(''),
    old_password: new FormControl(''),
    new_password: new FormControl(''),
    phoneNumber: new FormControl(''),
    description : new FormControl(''),
    address : new FormControl(''),
    profilePicture: new FormControl('')
  });
  submitted = false;

  urllink:string="";

  
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
  user:User=new User()
  currentUserInfo=this.profile.getUser()

  
  
  
  

  constructor(private formBuilder: FormBuilder, private http:HttpClient,private readonly registerservice: RegisterService,
    private profile: ProfileService, private authService: AuthService, private tokenStorage: TokenStorageService,
    private router:Router) {}

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
        old_password: [
          '',
          [
            Validators.required
          ]
        ],
        new_password: ['',
        [Validators.minLength(6),
        Validators.maxLength(40)]
      ],
        phoneNumber: ['',[Validators.required,Validators.minLength(9),Validators.maxLength(11)]],
        description : ['',''],
        address : ['',''],
        profilePicture :['','']

      },
      
      {
        validators: [Validation.match(this.tokenStorage.getUser().password, 'old_password')]
      }
    );
    console.log(this.currentUserInfo);
    console.log(this.currentUserInfo.password);
    this.f['username'].setValue(this.currentUserInfo.username);

    this.f['fullname'].setValue(this.currentUserInfo.name);
    this.f['phoneNumber'].setValue(this.currentUserInfo.phone);
    this.f['description'].setValue(this.currentUserInfo.description);
    this.f['address'].setValue(this.currentUserInfo.address);
    this.f['email'].setValue(this.currentUserInfo.email);
    this.f['profilePicture'].setValue(this.currentUserInfo.profilePicLink);
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
   
    this.f['profilePicture'].setValue (this.urllink)
    
    console.log(this.f['fullname'].value,"fn")
    this.newAccount.name = this.f['fullname'].value
    console.log(this.f['new_password'].value,"new_password")
    if(this.f['new_password'].value!='')
       this.newAccount.password=this.f['new_password'].value
    else
       this.newAccount.password=this.f['old_password'].value
    this.newAccount.username = this.f['username'].value
    this.newAccount.phone_numbers[0] = this.f['phoneNumber'].value
    this.newAccount.email = this.f['email'].value
    this.newAccount.description = this.f['description'].value
    this.newAccount.address = this.f['address'].value
    this.newAccount.profile_pic_link =this.f['profilePicture'].value
    

   
    var NewAccountJsonString = JSON.stringify(this.newAccount)
    console.log(NewAccountJsonString)
    this.urllink=""

    var headers=new HttpHeaders().append("Authorization","Bearer "+this.tokenStorage.getUser().token)
    this.http.post<boolean>("http://localhost:8080/users/profile/edit/"+this.tokenStorage.getUser().userId,JSON.parse(NewAccountJsonString),{headers: headers}).subscribe((data:boolean) =>{
      if(data)
      alert("Your edit saved")
      console.log(data);
  },);
}

  onReset(): void {
    this.submitted = false;
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
