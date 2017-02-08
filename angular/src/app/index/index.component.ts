import {Component, OnInit} from '@angular/core';
import {Route, Router} from '@angular/router';

import {QuestionComponent} from '../question/question.component'
import {QuestionService} from '../question/question.service'

@Component({
  selector: 'app-index',
  templateUrl: './index.component.html',
  styleUrls: ['./index.component.css'],
  entryComponents: [QuestionComponent]
})
export class IndexComponent implements OnInit {

  controllers: Array<any>;

  _listData: any;

  constructor(private questionService : QuestionService, private router: Router) { }

  ngOnInit(): void {
    this.questionService.getList().subscribe(res => {
      this._listData = res;
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
