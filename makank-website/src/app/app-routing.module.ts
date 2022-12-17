import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { PropertiesComponent } from './all-properties/properties/properties.component';
import { LoginComponent } from './login/login.component';
import { NewPostComponent } from './new-post/new-post.component';
import { ProfileComponent } from './profile/profile.component';
import { RegisterComponent } from './registration/register/register.component';
const routes: Routes = [
  {path:"Home" , component:PropertiesComponent},
  {path:"Profile" , component:ProfileComponent},
  {path:"Login" , component:LoginComponent},
  {path:"Register", component:RegisterComponent  },
  {path:"NewPost",component:NewPostComponent},
  {path:"**" ,redirectTo:"Home",pathMatch:"full"}
  ];
@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
