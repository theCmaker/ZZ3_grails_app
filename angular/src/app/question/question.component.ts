import { Component, Input, OnInit } from '@angular/core';
import { ActivatedRoute, Params } from '@angular/router';
import { Location } from '@angular/common';

import 'rxjs/add/operator/switchMap';

import {QuestionService} from './question.service';
import { Question } from './question';

@Component({
  selector: 'question',
  templateUrl: './question.component.html',
  styleUrls: ['./question.component.css'],
})
  
export class QuestionComponent implements OnInit {


  @Input() id: number;

  question: Question;

  constructor(
    private questionService: QuestionService,
    private route: ActivatedRoute,
    private location : Location
  ) {
    
  }


  ngOnInit(): void {
    this.route.params
      .switchMap((params: Params) => this.questionService.getQuestionById(+params['id'])).subscribe(res => { this.question = res; console.log("init component"); console.log(res) });
    }

}
