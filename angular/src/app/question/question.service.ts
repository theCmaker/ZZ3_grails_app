import {Injectable} from '@angular/core';
import {Http, Response} from '@angular/http';

import 'rxjs/add/operator/cache';
import 'rxjs/add/operator/map';
import {Observable} from 'rxjs';

@Injectable()
export class QuestionService {

  _data: Observable<any>;

  constructor(private http: Http) { }

  getList(): Observable<any> {
    if (!this._data) {
      this._data = this.http.get('http://localhost:8080/question/list')
        .map((res: Response) => res.json())
        .cache();
    }
    return this._data
  }

}
