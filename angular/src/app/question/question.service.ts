import {Injectable} from '@angular/core';
import {Http, Response} from '@angular/http';

import {Question} from './question';

import 'rxjs/add/operator/cache';
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/switchMap';
import 'rxjs/add/operator/mergeMap';
import 'rxjs/Rx';
import {Observable} from 'rxjs';

@Injectable()
export class QuestionService {

  _questionUrl: string = 'http://localhost:8080/question';
  
  _data: Observable<Question[]>;

  constructor(private http: Http) { }

  getList(): Observable<Question[]> {
    if (!this._data) {
      this._data = this.http.get(this._questionUrl+'/list')
        .map(res => {
          console.log('res');
          console.log(res);
          return res.json().map((elt) => {
            console.log('elt');
            console.log(elt);
            return new Question(elt.id, elt.title, elt.content, elt.answers, elt.date, elt.downVoters, elt.upVoters, elt.user.id);
          });
        })
        .cache();
    }
    return this._data
  }

  getQuestionById(id: number): Observable<Question> {
    /*
    this.getList().subscribe(res => {
      res.find((q, i, o) => {
        return q.id == id;
      });
    });*/

    console.log("getQuestionById");
    console.log(id);
    console.log(this._data);
    return this._data
      .flatMap(x => x)
      .filter(q => q.id == id - 1);
    
      // .flatMap(x => {
      //   console.log(x);
      //   return x;
      // })
  }
}
