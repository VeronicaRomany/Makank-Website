import {Post} from "./post";

export class User {

  name:string=""
  username:string=""
  password:string=""
  phoneNumber:string=""


  email:string=""
  profilePicLink:string=""
  address:string=""
  description:string=""

  saved_posts: Post[] = []

  constructor(){}


}
