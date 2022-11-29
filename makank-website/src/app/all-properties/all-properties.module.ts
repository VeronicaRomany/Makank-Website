import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { PropertiesComponent } from './properties/properties.component';
import { BrowserModule } from '@angular/platform-browser'


@NgModule({
  declarations: [
    PropertiesComponent
  ],
  imports: [
    CommonModule,
    AllPropertiesModule,
    BrowserModule
    
  ]
})
export class AllPropertiesModule { }
