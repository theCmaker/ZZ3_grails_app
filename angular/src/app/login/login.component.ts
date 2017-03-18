import { Component, Input, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { ActivatedRoute, Params } from '@angular/router';
import { Location } from '@angular/common';


import 'rxjs/add/operator/switchMap';

@Component({
    selector: 'login',
    templateUrl: './login.component.html',
    styleUrls: ['./login.component.css'],
    entryComponents: []
})

export class LoginComponent {

    username: string;
    password: string;

    constructor() {
        this.username = "";
        this.password = "";
    }

    submitLogin() {
        if ("" != this.username && "" != this.username) {
            console.log("login " + this.username, " - " + this.password);
        }
    }

}
