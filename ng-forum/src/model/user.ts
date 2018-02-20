import {Topic} from "./topic";
import {Comment} from "./comment";
export class User {
    id : number;
    name: string;

    topics: Topic[] = [];
    comments: Comment[];
}
