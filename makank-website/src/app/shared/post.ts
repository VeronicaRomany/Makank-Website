import { Apartment, Property, Villa } from "./property"
export class Post{
    postID: number = 0
    property!: Property | Villa | Apartment 
    publishDate: Date = new Date
  }