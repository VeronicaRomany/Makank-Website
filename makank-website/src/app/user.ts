import { Post } from "./shared/post"

export class User {

    name:string=""
    username:string=""
    password:string=""
    phone_numbers:string[]=[]


    email:string=""
    profile_pic_link:string=""
    address:string=""
    description:string=""

    saved_posts: Post[] = []

    constructor(){}


}
