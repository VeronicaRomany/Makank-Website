export class ViewData{
    prop_type:string=""
    purchase_type:string=""
    price:string=""
    city:string=""
    thumbnail_url:string=""
    info_Icons:string[]=[] //housing, not housing, etc
    userName:string=""
    date_published:string = ""

    //Builder-Like initialization of the view
    setType(t:string):ViewData{
        this.prop_type=t
        return this;
    }

    setPurchaseOption(o:string):ViewData{
        this.purchase_type=o
        return this;
    }

    setPrice(p:string):ViewData{
        this.price=p
        return this;
    }

    setThumbnail(u:string):ViewData{
        this.thumbnail_url=u
        return this;
    }

    setDatePublished(d:string):ViewData{
        this.date_published=d
        return this;
    }

    setIcons(icons:string[]):ViewData{
        this.info_Icons=icons
        return this
    }

    setPublisher(userName:string){
        this.userName=userName
        return this
    }

    setCity(c:string){
        this.city=c
        return this
    }
}