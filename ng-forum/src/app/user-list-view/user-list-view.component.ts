import { Component, OnInit } from '@angular/core';
import {User} from "../../model/user";
import {DataService} from "../data-service.service";
import {Topic} from "../../model/topic";
import {Comment} from "../../model/comment";

@Component({
  selector: 'app-user-list-view',
  templateUrl: './user-list-view.component.html',
  styleUrls: ['./user-list-view.component.css']
})
export class UserListViewComponent implements OnInit {

  users: User[];
  topics: Topic[];
  comments: Comment[];

  selectedUser: User;
  commentUser: User;
  selectedTopic: Topic;

  createdUser: User = new User();
  createdTopic: Topic = new Topic();
  createdComment: Comment = new Comment();

  constructor(public dataService: DataService) {

    dataService.fetchUsers()
        .then(users => this.users = users)
        .then(users => console.log('Users: ', users));

    dataService.fetchTopics()
      .then(topics => this.topics = topics)
      .then(topics => console.log('Topics: ', topics));
  }


  ngOnInit() {
  }

  detailsUser(user: User) {
    this.selectedUser = user;

    //Created model associated to the form
    this.createdTopic = new Topic();
    this.createdTopic.user = user;
    this.createdTopic.name = user.name + "'s topic";

    this.createdUser.name = "New "+user.name;

    this.dataService.fetchUserWithTopics(user)
        .then(fullUser => this.selectedUser = fullUser)
        .then(console.log); //What???
  }

  getUser(user: User){
    console.log('Selected user : ' + user.name);
  }
  detailsTopic(topic: Topic)
  {
    this.selectedTopic = topic;

    //Created model associated to the form
    this.createdUser = new User();
    this.createdUser.name = topic.name;

    this.createdComment = new Comment();
    this.createdComment.content = "new Comment on " + topic.name;
  }



  createUser() {
    this.dataService.createUser(this.createdUser)
        .then(() => this.users.push(
            Object.assign({}, this.createdUser)
        ))
        .catch(e => alert(e));
  }

  createTopic() {
    this.dataService.createTopic(this.createdTopic)
        .then(() => this.selectedUser.topics.push(
            Object.assign({}, this.createdTopic)
        ))
        .catch(e => alert(e));
  }

  createComment()
  {
    this.dataService.createComment(this.createdComment).then(() =>
        this.selectedTopic.comments.push(
        Object.assign({}, this.createdComment)
      ))

    /*this.dataService.createComment(this.createdComment).then(() =>
        this.commentUser.comments.push(
        (Object.assign({}, this.createdComment))
      ))
*/
      .catch(e => alert(e));
  }



}
