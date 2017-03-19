import { Component, Input, OnInit } from '@angular/core';
import { ActivatedRoute, Params } from '@angular/router';
import { Location } from '@angular/common';

import 'rxjs/add/operator/switchMap';

import { Answer } from '../answer/answer';

import { QuestionService } from './question.service';
import { Question } from './question';

import { VoteComponent } from '../vote/vote.component'
import { AnswerComponent } from '../answer/answer.component';

import { LoginService } from '../login/login.service';

/* for testing */
import { Http, Response, Headers } from '@angular/http';
import 'rxjs/Rx';
import { Observable } from 'rxjs';

@Component({
    selector: 'question',
    templateUrl: './question.component.html',
    styleUrls: ['./question.component.css'],
    entryComponents: [VoteComponent, AnswerComponent]
})

export class QuestionComponent implements OnInit {


    @Input() id: number;

    question: Question;

    _answerContent: string = "";

    constructor(
        public loginService: LoginService,
        private questionService: QuestionService,
        private route: ActivatedRoute,
        private location: Location,
        private http: Http
    ) {

    }


    ngOnInit(): void {
        this.route.params
            .switchMap((params: Params) => this.questionService.getQuestionById(+params['id'])).subscribe(res => { this.question = res; });
    }

    createAnswer(): void {
        var createdAnswer = new Answer(undefined, this._answerContent, false, new Date(), this.question.id, this.loginService.user.id, [], []);

        console.log("posting");
        this.http.post("http://localhost:8080/answer/save/"+this.question.id+"?content="+this._answerContent
            , createdAnswer
            , {
                headers: new Headers({
                    'Authorization': 'Bearer ' + this.loginService._token
                }),
                
            })
            .subscribe(res => {
                console.log("response");
                console.log(res);
            });

    }

}
