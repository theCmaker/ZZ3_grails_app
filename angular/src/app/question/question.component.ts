import {Component} from '@angular/core';
import {QuestionService} from './question.service';


@Component({
  selector: 'question',
  templateUrl: './question.component.html',
  styleUrls: ['./question.component.css']
})
export class QuestionComponent {

  applicationData: any;

  title: string;

  constructor(private questionService: QuestionService) {
    this.title = "Toto";
  }

  

}
