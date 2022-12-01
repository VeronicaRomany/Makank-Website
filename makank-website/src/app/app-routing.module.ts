import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { PropertiesComponent } from './all-properties/properties/properties.component';
import { LoginComponent } from './login/login/login.component';
import { ProfileComponent } from './my-profile/profile/profile.component';
import { HeaderComponent } from './shared/header/header.component';

const routes: Routes = [
{path:"Home" , component:PropertiesComponent},
{path:"Profile" , component:ProfileComponent},
{path:"Login" , component:LoginComponent},
{path:"**" ,redirectTo:"Home",pathMatch:"full"}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
