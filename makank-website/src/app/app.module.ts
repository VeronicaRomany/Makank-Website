import { EventEmitter, NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';

import { PropertiesComponent } from './all-properties/properties/properties.component';


import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponent } from './login/login.component';



import { RegisterComponent } from './registration/register/register.component';

import { ReactiveFormsModule } from '@angular/forms';

import { SharedModule } from './shared/shared.module';
import { ProfileComponent } from './profile/profile.component';


@NgModule({
  declarations: [
    AppComponent,

    LoginComponent,
    ProfileComponent,
    RegisterComponent,
    PropertiesComponent

  ],
  imports: [
    BrowserModule,
    AppRoutingModule,

    FormsModule,
    HttpClientModule,
    ReactiveFormsModule,
    SharedModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
