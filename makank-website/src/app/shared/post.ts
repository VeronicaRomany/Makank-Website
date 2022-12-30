import { Apartment, Property, Villa } from "./property"
export class Post{
    postID: number = 0
    propertyID:number=0
    publishDate: Date = new Date()
    type:string=""
    sellerID: number = 0
    roomNumber:number = 0
    bathroomNumber:number = 0
    price:number = 0
    city: string=""
    address:string=""
    area:number=0
    rent:boolean=false
    info:string=""
    pictures:string[] = []
    image:string=""
    level: number=1
    elevator: boolean= false
    studentHousing: boolean= false
    hasGarden:boolean=false
    hasPool:boolean=false
    hasPictures:boolean=false
    image:string=""
    
  }
