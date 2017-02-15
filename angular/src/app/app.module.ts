import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpModule } from '@angular/http';
import { LocationStrategy, HashLocationStrategy } from '@angular/common';
import { RouterModule } from '@angular/router';
import { NgbModule } from "@ng-bootstrap/ng-bootstrap";

// Components
import { rootRouterConfig } from './app.routes';
import { IndexComponent } from './index/index.component';
import { AppComponent } from './app.component';
import { NavComponent } from './nav/nav.component';

import { AnswerComponent } from './answer/answer.component'
import { BadgeComponent } from './badge/badge.component'
import { FeatureComponent } from './feature/feature.component'
import { QuestionComponent } from './question/question.component'
import { UserComponent } from './user/user.component'

// Services
import { AnswerService } from './answer/answer.service'
import { BadgeService } from './badge/badge.service'
import { FeatureService } from './feature/feature.service'
import { QuestionService } from './question/question.service'
import { UserService } from './user/user.service'

@NgModule({
  declarations: [
    AppComponent,
    NavComponent,
    IndexComponent,
    QuestionComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpModule,
    RouterModule.forRoot(rootRouterConfig),
    NgbModule.forRoot()
  ],
  providers: [{ provide: LocationStrategy, useClass: HashLocationStrategy },
    AnswerService,
    BadgeService,
    FeatureService,
    QuestionService,
    UserService],
  bootstrap: [AppComponent]
})
export class AppModule { }
