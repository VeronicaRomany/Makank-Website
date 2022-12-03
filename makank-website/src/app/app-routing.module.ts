import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { PropertiesComponent } from './all-properties/properties/properties.component';
import { LoginComponent } from './login/login.component';
import { ProfileComponent } from './profile/profile.component';


const routes: Routes = [];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
