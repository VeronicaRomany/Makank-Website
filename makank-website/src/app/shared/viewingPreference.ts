export class ViewingPreference{
    sorted:boolean= false
    filtered:boolean = false
    sortingPreference:SortingPreference=new SortingPreference
    filterPrereferene:FilterPreference=new FilterPreference
}

export class SortingPreference{
    ascending:boolean = false
    sortingCriteria: string = "price" //enum later
}

export class FilterPreference{
    purchaseChoice:string = "rent"
    propertyType:string = "villa"
    withPictures:boolean = true
    studentHousing:boolean = true
    //student housing search word
    addressSearchWord:string=""
    citySearchWord:string=""

}