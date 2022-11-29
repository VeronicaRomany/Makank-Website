export class Property{
    sellerID: number = 0
    roomNumber:number = 0
    bathroomNumber:number = 0
    price:number = 0 
    city: string=""
    area:number=0
    rent:boolean=false
    info:String=""
    pictures:string[] = []

    
    view_Small(){

    }

}

export class Apartment extends Property{
    level: number=1
    elevator: boolean= false
    studentHousing: boolean= false 





}

export class Villa extends Property{
    hasGarden:boolean=false
    hasPool:boolean=false 
    numberOfLevels:number=1;






}