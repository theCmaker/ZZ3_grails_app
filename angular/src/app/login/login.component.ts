import { Component, Input, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { ActivatedRoute, Params } from '@angular/router';
import { Location } from '@angular/common';

import { Http, Response } from '@angular/http';

import 'rxjs/Rx';
import { Observable } from 'rxjs';

import { UserService } from '../user/user.service';
import { User } from '../user/user';


import { LoginService } from './login.service';

@Component({
    selector: 'login',
    templateUrl: './login.component.html',
    styleUrls: ['./login.component.css'],
    entryComponents: []
})

export class LoginComponent {

    // Form fields
    username: string = "";
    password: string = "";

    constructor(
        private http: Http,
        public loginService: LoginService
    ) {
    }

    login() {

        console.log("login");

        if ("" != this.username && "" != this.password) {
            this.loginService.login(this.username, this.password);
        }

    }

}
