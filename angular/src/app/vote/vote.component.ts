import { Component, Input, OnInit } from '@angular/core';
import { ActivatedRoute, Params } from '@angular/router';
import { Location } from '@angular/common';

import 'rxjs/add/operator/switchMap';

import { QuestionService } from '../question/question.service'
import { Question } from '../question/question'

@Component({
    selector: 'vote',
    templateUrl: 'vote.component.html',
    styleUrls: ['./vote.component.css'],
})

export class VoteComponent implements OnInit {

    // the question ID    
    @Input() id: number;

    question: Question;

    constructor(
        private questionService: QuestionService,
        private route: ActivatedRoute,
        private location : Location
    ) { }

    ngOnInit() {
        this.route.params
            .switchMap((params: Params) => this.questionService.getQuestionById(this.id))
            .subscribe(res => { this.question = res; });
    }

    getScore(): number {
        return this.question.getScore();
    }

    upVote(): void {
        console.log("up");
    }

    downVote(): void {
        console.log("down");
    }

}