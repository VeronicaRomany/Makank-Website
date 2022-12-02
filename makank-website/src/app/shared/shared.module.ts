import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { BrowserModule } from '@angular/platform-browser'
import { HeaderComponent } from './header/header.component';


@NgModule({
  declarations: [
    HeaderComponent
  ],
  imports: [
    CommonModule,
    BrowserModule
  ],
  exports:[
    HeaderComponent
  ]
})
export class SharedModule { }
