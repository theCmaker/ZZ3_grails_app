import { Injectable } from '@angular/core';
import { Http, Response } from '@angular/http';

import { Question } from './question';

import 'rxjs/add/operator/cache';
import 'rxjs/add/operator/map';
import 'rxjs/Rx';
import { Observable } from 'rxjs';

@Injectable()
export class QuestionService {

    _questionUrl: string = 'http://localhost:8080/question';

    _data: Observable<Question[]>;

    constructor(private http: Http) { }

    getList(): Observable<Question[]> {
        if (!this._data) {
            this._data = this.http.get(this._questionUrl + '/list')
                .map(res => {
                    return res.json().map(elt => {
                        return new Question(elt.id, elt.title, elt.content, elt.answers, elt.date, elt.downVoters, elt.upVoters, elt.user.id);
                    });
                })
                .cache();
        }
        return this._data
    }

    getQuestionById(id: number): Observable<Question> {
        return this.getList().flatMap(x => x).find(q => {
            return q.id == id
        });

    }
}
