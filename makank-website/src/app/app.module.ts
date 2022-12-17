import { EventEmitter, NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';



import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponent } from './login/login.component';



import { RegisterComponent } from './registration/register/register.component';

import { ReactiveFormsModule } from '@angular/forms';
import { ProfileComponent } from './profile/profile.component';



import { SharedModule } from './shared/shared.module';
import { HeaderComponent } from './shared/header/header.component';
import { PropertiesComponent } from './all-properties/properties/properties.component';
import { FilterComponent } from './filter-bar/filter/filter.component';
import { FilterBarModule } from './filter-bar/filter-bar.module';


@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    ProfileComponent,
    RegisterComponent,
    PropertiesComponent,
    FilterComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    SharedModule,
    FormsModule,  
    HttpClientModule,
    ReactiveFormsModule,
    
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
