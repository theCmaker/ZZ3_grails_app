import {Component, OnInit} from '@angular/core';
import {Route, Router} from '@angular/router';

import {QuestionComponent} from '../question/question.component'
import { QuestionService } from '../question/question.service'

import {UserService} from '../user/user.service'

@Component({
  selector: 'app-index',
  templateUrl: './index.component.html',
  styleUrls: ['./index.component.css'],
  entryComponents: [QuestionComponent]
})
export class IndexComponent implements OnInit {


  questionsList: Array<any>;

  constructor(private questionService: QuestionService,
    private userService: UserService,  
    private router: Router) { }

  ngOnInit(): void {
    this.questionService.getList().subscribe(res => {
      this.questionsList = res;

      this.questionsList.forEach((val, idx, arr) => {

        this.userService.getUserById(val.user.id).subscribe(res => {
          console.log(res);
          val.user = res;
        });
      });


      console.log(res);
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
