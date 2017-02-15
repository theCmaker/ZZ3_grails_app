import { Component, OnInit } from '@angular/core';
import { Route, Router } from '@angular/router';

import { QuestionComponent } from '../question/question.component'
import { QuestionService } from '../question/question.service'
import { Question } from '../question/question'

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

  constructor(private questionService: QuestionService,
    private userService: UserService,
    private router: Router) {

    this.questionsList = new Array<Question>();
    
  }

  ngOnInit(): void {
    this.questionService.getList().subscribe(res => {
      console.log('ininit');

      // TODO : create Question instances, create user instances, store answers, change template and input the question in the vote component

      res.forEach((val, idx, arr) => {

        console.log('Index init')        
        console.log(val);

        this.questionsList.push(
          new Question(
            val["id"],
            val["title"],
            val["content"],
            val["answers"],
            val["date"],
            val["downVoters"],
            val["upvoters"],
            val["user"]
          )
        );


        
      });

      console.log('questionsList');
      console.log(this.questionsList);
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
