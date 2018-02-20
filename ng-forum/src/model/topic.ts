import {User} from "./user";
export class Topic {
    user : User;
    name:string;

   comments : Comment[] = [];
}
