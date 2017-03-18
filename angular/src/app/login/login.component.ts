import { Component, Input, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { ActivatedRoute, Params } from '@angular/router';
import { Location } from '@angular/common';

import { Http, Response } from '@angular/http';

import 'rxjs/Rx';
import { Observable } from 'rxjs';

@Component({
    selector: 'login',
    templateUrl: './login.component.html',
    styleUrls: ['./login.component.css'],
    entryComponents: []
})

export class LoginComponent {

    _loginUrl: string = 'http://localhost:8080/login';
    _response: Observable<any>;

    username: string;
    password: string;

    constructor(
        private http: Http
    ) {
        this.username = "";
        this.password = "";
    }

    submitLogin() {
        if ("" != this.username && "" != this.username) {

            // JSON to send and authenticate
            var jsonLogin = {
                "username": this.username,
                "password": this.password
            }

            console.log(jsonLogin);

            this.http.post(this._loginUrl, jsonLogin).subscribe(res => {
                console.log("response");
                console.log(res);
            });

        }
    }

}
