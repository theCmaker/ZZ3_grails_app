import { Component, Input, OnInit } from '@angular/core';
import { ActivatedRoute, Params } from '@angular/router';
import { Location } from '@angular/common';

import { AnswerService } from './answer.service'
import { Answer } from './answer'

import {VoteComponent} from '../vote/vote.component'

import { User } from '../user/user'
import { UserService } from '../user/user.service'

import { LoginService } from '../login/login.service';
import { Http, Response, Headers } from '@angular/http';
import 'rxjs/Rx';
import { Observable } from 'rxjs';

@Component({
    selector: 'answer',
    templateUrl: 'answer.component.html',
    entryComponents: [VoteComponent],
    providers: [AnswerService,UserService]
})

    
export class AnswerComponent implements OnInit {

    @Input() id: number;

    answer: Answer;
    username: string;
 
    constructor(
        public loginService: LoginService,
        private answerService: AnswerService,
        private userService: UserService,
        private route: ActivatedRoute,
        private location : Location
    ) {}

    ngOnInit(): void {
        this.username = "";
        this.answer = new Answer(undefined, "fetching content...", false, new Date(), undefined, undefined, [], []);
        this.answerService.getAnswerById(this.id).subscribe(res => {
            this.answer = res;
            this.userService.getUserById(res.user).subscribe(res => {
                this.username = res.username;
            })
        });
    }

    editAnswer(): void {}

    acceptAnswer(): void {}

    revokeAnswer(): void {}

    deleteAnswer(): void {}

}