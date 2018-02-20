import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {User} from "../model/user";
import {Topic} from "../model/topic";
import {Comment} from "../model/comment";

@Injectable()
export class DataService {

  constructor(public http: HttpClient) {
  }

  fetchUsers(): Promise<User[]> {

    return this.http
      .get('http://localhost:8080/forum/api/users')
      .toPromise()
      .then(data => data as User[])
  }

  fetchTopics(): Promise<Topic[]> {
    return this.http
      .get('http://localhost:8080/forum/api/topics')
      .toPromise()
      .then(data => data as Topic[])
  }

  createUser(user: User) {
    let url = 'http://localhost:8080/forum/api/users/';

    let dto = { //Data Transfer Object. Pour Jax-B
      name: user.name
    };

    console.log('Sending user : user');
    return this.http
      .post(url, dto)
      .toPromise()
      .then(data => {
        console.log('Success :)', data);

      })
    //.catch(e => console.error('Fail :(', e))
  }

  createTopic(topic: Topic)
{
  let url = 'http://localhost:8080/forum/api/topics/';

  let dto = { //Data Transfer Object. Pour Jax-B
  name: topic.name,
  user: topic.user
};
  console.log('Sending topic:; topic');
  return this.http
.post(url, dto)
.toPromise()
.then(data => console.log('Success :)', data))
  //.catch(e => console.error('Fail :(', e))
}

  createComment(comment: Comment)
  {
    let url = 'http://localhost:8080/forum/api/comments/';

    let dto = { //Data Transfer Object. Pour Jax-B
      content: comment.content,
      user: comment.user,
      topic: comment.topic
    };

    console.log(dto);
    return ;

    console.log('Sending comment:; comment');
    return this.http
      .post(url, dto)
      .toPromise()
      .then(data => console.log('Success :)', data))
    //.catch(e => console.error('Fail :(', e))
  }

  fetchUserWithTopics(user: User): Promise<User> {
    let url = 'http://localhost:8080/forum/api/users/' + user.id;
    return this.http
      .get(url)
      .toPromise()
      .then(data => {
        console.log('user with topic: ', data);
        return data as User;
      })
  }

}
