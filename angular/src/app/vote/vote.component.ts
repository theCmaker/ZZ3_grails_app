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
    styleUrls: ['./vote.component.css']
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

        if (this.isQuestion) {
            this.entity = new Question(this.id, "question title", "question content", [], new Date(), [], [], undefined);
            this.questionService.getQuestionById(this.id).subscribe(res => { this.entity = res; });
            
        } else {
            this.entity = new Answer(this.id, "Fetching content...", false, new Date(), undefined, undefined, [], []);
            this.answerService.getAnswerById(this.id).subscribe(res => { this.entity = res; });

        }
    }

    getScore(): number {
        if (this.entity) {
            return this.entity.getScore();
        } else {
            return 0;
        }
    }

    upVote(): void {
        if (this.entity) {
            console.log("up");
        }
    }

    downVote(): void {
        if (this.entity) {
            console.log("down");
        }
    }

}