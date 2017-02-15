import { Component, OnInit } from '@angular/core';

import { Question } from '../question/question'

@Component({
    selector: 'vote',
    templateUrl: 'vote.component.html'
})

export class VoteComponent implements OnInit {

    constructor(
        private question: Question
    ) { }

    ngOnInit() {
    }

}