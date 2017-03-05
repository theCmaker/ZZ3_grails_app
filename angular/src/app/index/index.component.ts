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
      this.usersList = res;
    });

    // Getting all the entities
    this.questionService.getList().subscribe(res => {
      this.questionsList = res;
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
