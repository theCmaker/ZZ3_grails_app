import { Component, Input, OnInit } from '@angular/core';
import { ActivatedRoute, Params } from '@angular/router';
import { Location } from '@angular/common';

import { User } from './user';
import { UserService } from './user.service';


@Component({
    selector: 'user',
    templateUrl: 'user.component.html',
    styleUrls: ['./user.component.css'],
})
export class UserComponent implements OnInit {

    // Id of the user    
    @Input() id: number;

    user: User;

    constructor(
        private userService: UserService,
        private route: ActivatedRoute,
        private location : Location
    ) { }

    ngOnInit() {
        this.route.params
      .switchMap((params: Params) => this.userService.getUserById(+params['id'])).subscribe(res => { this.user = res; });
    }
}