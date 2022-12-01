import { EventEmitter, NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { PropertiesComponent } from './all-properties/properties/properties.component';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';

import { RegisterComponent } from './registration/register/register.component';

import { ReactiveFormsModule } from '@angular/forms';

import { ProfileComponent } from './my-profile/profile/profile.component';
import { SharedModule } from './shared/shared.module';


@NgModule({
  declarations: [
    AppComponent,
    RegisterComponent,
    ProfileComponent,
    PropertiesComponent

  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    ReactiveFormsModule,

    SharedModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
