export class ViewingPreference{
    sorted:boolean= false
    filtered:boolean = false
    sortingPreference:SortingPreference=new SortingPreference
    filterPreference:FilterPreference=new FilterPreference
}

export class SortingPreference{
    ascending:boolean = false
    sortingCriteria: string = "any" //enum later
}

export class FilterPreference{
    purchaseChoice:string = "any"
    propertyType:string = "any"
    withPictures:boolean = false
    studentHousing:boolean = false
    minPrice:Number=-1
    maxPrice:Number=-1
    minArea:Number=-1;
    maxArea:Number=-1;
    //student housing search word
    infoSearchWord:string=""
    citySearchWord:string="any"

}

