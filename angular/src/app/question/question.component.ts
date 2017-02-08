import {Component} from '@angular/core';
import {QuestionService} from './question.service';
import {OnInit} from '@angular/core';

@Component({
  selector: 'question',
  templateUrl: './question.component.html',
  styleUrls: ['./question.component.css']
})
export class QuestionComponent implements OnInit {

  applicationData: any;

  constructor(private questionService : QuestionService) { }

  ngOnInit(): void {
    this.questionService.getList().subscribe(res => {

      this.applicationData = res
      console.log(this.applicationData);
    });
  }

}
