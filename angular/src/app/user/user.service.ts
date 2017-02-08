import {Injectable} from '@angular/core';
import {Http, Response} from '@angular/http';

import 'rxjs/add/operator/cache';
import 'rxjs/add/operator/map';
import {Observable} from 'rxjs';

@Injectable()
export class UserService {

    _user : Observable<any>

    constructor(private http: Http) { }

    getUserById(id: Number): Observable<any> {
        
        if (!this._user) {
            this._user = this.http.get('http://localhost:8080/user/show/' + id)
                .map((res: Response) => res.json())
                .cache();
        }

        return this._user;
    }
}