import {Component, OnInit} from '@angular/core';
import {Route, Router} from '@angular/router';

import {QuestionComponent} from '../question/question.component'

@Component({
  selector: 'app-index',
  templateUrl: './index.component.html',
  styleUrls: ['./index.component.css'],
  entryComponents: [QuestionComponent]
})
export class IndexComponent {

  controllers: Array<any>;

  constructor(private router: Router) { }

  hasRoute(controllerName: string): boolean {
    return this.router.config.some((route: Route) => {
      if (route.path === controllerName) {
        return true;
      }
    });
  }
}
