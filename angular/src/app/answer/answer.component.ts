import { Component, Input, OnInit } from '@angular/core';
import { ActivatedRoute, Params } from '@angular/router';
import { Location } from '@angular/common';

import { AnswerService } from './answer.service'
import { Answer } from './answer'

import {VoteComponent} from '../vote/vote.component'

@Component({
    selector: 'answer',
    templateUrl: 'answer.component.html',
    entryComponents: [VoteComponent]
})

    
export class AnswerComponent implements OnInit {

    @Input() id: number;

    answer: Answer;

    constructor(
        private answerService: AnswerService,
        private route: ActivatedRoute,
        private location : Location
    ) { }

    ngOnInit(): void {
        this.answerService.getAnswerById(this.id).subscribe(res => { this.answer = res; console.log(res);
        });
    }

}