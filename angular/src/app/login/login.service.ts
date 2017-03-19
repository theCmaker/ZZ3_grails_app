import { Injectable } from '@angular/core';
import { Http, Response } from '@angular/http';

import 'rxjs/Rx';
import { Observable } from 'rxjs';

import { UserService } from '../user/user.service';
import { User } from '../user/user';

@Injectable()
export class LoginService {

    _loginUrl: string = 'http://localhost:8080/login';

    // Used for content management and acces
    _roles: Array<string> = undefined;
    _token: string = undefined;

    _loggedIn: boolean = false;

    // Used for displaying and requests
    user: User;

    constructor(
        private http: Http,
        private userService: UserService
    ) { }

    login(username: string, password: string) {

        // JSON to send and authenticate
        var jsonLogin = {
            "username": username,
            "password": password
        };

        console.log(jsonLogin);

        this.http.post(this._loginUrl, jsonLogin).subscribe(res => {

            if (200 == res.status) {
                
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

                this._loggedIn = true;

            } else {

                this._loggedIn = true;
                // Display an error or idk...
            }
        });

    }

    isLoggedIn(): boolean {
        return this._loggedIn;
    }

}