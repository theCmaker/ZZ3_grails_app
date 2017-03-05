import { Component, Input, OnInit } from '@angular/core';
import { ActivatedRoute, Params } from '@angular/router';
import { Location } from '@angular/common';

import 'rxjs/add/operator/switchMap';

import { QuestionService } from '../question/question.service'
import { Question } from '../question/question'

import { AnswerService } from '../answer/answer.service'
import { Answer } from '../answer/answer'

@Component({
    selector: 'vote',
    templateUrl: 'vote.component.html',
    styleUrls: ['./vote.component.css'],
})

export class VoteComponent implements OnInit {

    // the question ID    
    @Input() id: number;
    @Input() isQuestion: boolean = false;

    entity: Question|Answer;

    constructor(
        private questionService: QuestionService,
        private answerService : AnswerService,
        private route: ActivatedRoute,
        private location : Location
    ) { }

    ngOnInit() {

        //Fetching from answers or questions
        console.log("vote component for question " + this.isQuestion);

        if (this.isQuestion) {
            this.questionService.getQuestionById(this.id).subscribe(res => { this.entity = res; });
            
        } else {
            this.answerService.getAnswerById(this.id).subscribe(res => { this.entity = res; });

        }
    }

    getScore(): number {
        return this.entity.getScore();
    }

    upVote(): void {
        console.log("up");
    }

    downVote(): void {
        console.log("down");
    }

}