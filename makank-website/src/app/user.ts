import { Post } from "./shared/post"
export class User {
    name:string=""
    username:string=""
    password:string=""
    phoneNumber:string[]=[]

    
    email:string=""
    profilePicture:string=""
    address:string=""
    description:string=""
    
    savedPosts: Post[] = []

    constructor(){}
   

}
