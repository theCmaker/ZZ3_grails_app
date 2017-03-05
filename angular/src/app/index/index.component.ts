import { Component, OnInit } from '@angular/core';
import { Route, Router } from '@angular/router';

import { QuestionComponent } from '../question/question.component'
import { QuestionService } from '../question/question.service'

import { Question } from '../question/question'
import { User } from '../user/user'

import { VoteComponent } from '../vote/vote.component'

import { UserService } from '../user/user.service'

@Component({
  selector: 'app-index',
  templateUrl: './index.component.html',
  styleUrls: ['./index.component.css'],
  entryComponents: [QuestionComponent, VoteComponent]
})
export class IndexComponent implements OnInit {


  questionsList: Array<Question>;
  usersList: Array<User>;

  constructor(private questionService: QuestionService,
    private userService: UserService,
    private router: Router) {

    this.questionsList = new Array<Question>();
    this.usersList = new Array<User>();
    
  }

  ngOnInit(): void {
    this.userService.getList().subscribe(res => {

      res.forEach( (val, idx, arr) => {
        this.usersList.push(
          new User(
            val.id,
            val.answers,
            val.points,
            val.badges,
            val.questions,
            val.username
          )
        );
      });
      
      console.log('user list')
      console.log(this.usersList)

    });
    // Getting all the entities
    this.questionService.getList().subscribe(res => {
      // TODO : create Question instances, create user instances, store answers, change template and input the question in the vote component
      /*
      res.forEach((val, idx, arr) => {

        this.questionsList.push(
          new Question(
            val.id,
            val.title,
            val.content,
            val.answers,
            val.date,
            val.downVoters,
            val.upvoters,
            val.user.id
          )
        );
        */
      this.questionsList = res;
      });

      console.log('questionsList');
      console.log(this.questionsList);
    }

  getUserById(id: number): User {
    id = id - 1; // because
    console.log('user id');
    console.log(id);
    console.log(this.usersList[id]);

    

    return this.usersList.find((user, idx, obj) => {
      return user.id == id
    });
  }

  

  hasRoute(controllerName: string): boolean {
    return this.router.config.some((route: Route) => {
      if (route.path === controllerName) {
        return true;
      }
    });
  }
}
