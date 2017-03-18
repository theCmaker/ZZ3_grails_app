import { Injectable } from '@angular/core';
import { Http, Response } from '@angular/http';

import { User } from './user';

import 'rxjs/add/operator/cache';
import 'rxjs/add/operator/map';
import 'rxjs/Rx';
import { Observable } from 'rxjs';

@Injectable()
export class UserService {

    _userUrl: string = 'http://localhost:8080/user';

    _data: Observable<User[]>;

    constructor(private http: Http) { }


    getList(): Observable<User[]> {

        if (!this._data) {
            this._data = this.http.get(this._userUrl + '/list')
                .map(res => {
                    return res.json().map(elt => {
                        return new User(elt.id, elt.answers, elt.badges, elt.points, elt.questions, elt.username);
                    });
                })
                .cache();
        }

        return this._data;
    }

    getUserById(id: number): Observable<User> {
        console.log('user id')
        console.log(id)
        return this.getList().flatMap(x => x).find(u => {
            return u.id == id;
        });
    }
}