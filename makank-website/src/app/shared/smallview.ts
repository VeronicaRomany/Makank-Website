export class SmallView{
    prop_type:string=""
    purchase_type:string=""
    price:string=""
    thumbnail_url:string=""
    info_Icons:string[]=[] //housing, not housing, etc
    userName:string=""
    date_published:string = ""

    //Builder-Like initialization of the view
    setType(t:string):SmallView{
        this.prop_type=t
        return this;
    }

    setPurchaseOption(o:string):SmallView{
        this.purchase_type=o
        return this;
    }

    setPrice(p:string):SmallView{
        this.price=p
        return this;
    }

    setThumbnail(u:string):SmallView{
        this.thumbnail_url=u
        return this;
    }

    setDatePublished(d:string):SmallView{
        this.date_published=d
        return this;
    }

    // setIcons(icons:string[]):SmallView{
        
    // }




}