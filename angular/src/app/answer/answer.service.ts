import { Injectable } from '@angular/core';
import { Http, Response } from '@angular/http';

import { Answer } from './answer';

import 'rxjs/add/operator/cache';
import 'rxjs/add/operator/map';
import 'rxjs/Rx';
import { Observable } from 'rxjs';

// @TODO this service is not tested

@Injectable()
export class AnswerService {

    _answerUrl: string = 'http://localhost:8080/answer';

    _data: Observable<Answer[]>;

    constructor(private http: Http) { }

    getList(): Observable<Answer[]> {
        
        if (!this._data) {
            this._data = this.http.get(this._answerUrl + '/list')
            .map(res => {
                    return res.json().map(elt => {
                        return new Answer(elt.id, elt.content, elt.accepted, elt.date, elt.question, elt.user.id, elt.upVoters, elt.downVoters);
                    });
                })
            .cache();
        }
        return this._data;
    }

    private handleError(error: any): Promise<any> {
        console.error('An error occurred', error); // for demo purposes only
        return Promise.reject(error.message || error);
    }

    getAnswerById(id: number): Observable<Answer> {
        var data = this.getList().flatMap(x => x).find(a => {
            return a.id == id;
        });
        return data;
    }
}