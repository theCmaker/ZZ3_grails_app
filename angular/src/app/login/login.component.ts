import { Component, Input, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { ActivatedRoute, Params } from '@angular/router';
import { Location } from '@angular/common';

import { Http, Response } from '@angular/http';

import 'rxjs/Rx';
import { Observable } from 'rxjs';

import { UserService } from '../user/user.service';
import { User } from '../user/user';

@Component({
    selector: 'login',
    templateUrl: './login.component.html',
    styleUrls: ['./login.component.css'],
    entryComponents: []
})

export class LoginComponent {

    _loginUrl: string = 'http://localhost:8080/login';
    
    // Used for content management and acces
    _roles: Array<string> = undefined;
    _token: string = undefined;

    // Used for displaying and requests
    user: User;

    // Form fields
    username: string = "";
    password: string = "";

    constructor(
        private http: Http,
        private userService : UserService
    ) {
    }

    login() {
        console.log("login");
        
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

                // Parse the json response
                var parsed = res.json();

                this._token = parsed.access_token;
                this._roles = parsed.roles;

                // Recover the user instance from its name
                this.userService.getUserByName(parsed.username).subscribe(res => {
                    this.user = res;
                });

                
                console.log(this);
                
            });

        }
    }

    isLoggedIn(): boolean {
        
        return undefined != this._token;

    }

}
