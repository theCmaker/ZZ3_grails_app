import { Injectable } from '@angular/core';
import { Http, Response } from '@angular/http';

import { Answer } from './answer';

import 'rxjs/Rx';
import { Observable } from 'rxjs';

// @TODO this service is not tested

@Injectable()
export class AnswerService {

    _answerUrl = 'http://localhost:8080/answer';

    _data: Observable<Answer[]>;

    constructor(private http: Http) { }

    getList(): Observable<Answer[]> {
        console.log("in answer get list");
        
        if (!this._data) {
            this._data = this.http.get(this._answerUrl + '/list')
                .map(res => {
                    return res.json().map(elt => {
                        console.log(
                            elt.id, elt.content, elt.accepted, elt.date, elt.question, elt.user.id, elt.upVoters, elt.downVoters
                        );
                        
                        return new Answer(elt.id, elt.content, elt.accepted, elt.date, elt.question, elt.user.id, elt.upVoters, elt.downVoters);
                    });
                }).
                cache();
        }
        return this._data;
    }

    getAnswerById(id: number): Observable<Answer> {
        console.log("In answer get id");
        
        return this.getList().flatMap(x => x).find(a => {
            return a.id == id;
        });
    }
}