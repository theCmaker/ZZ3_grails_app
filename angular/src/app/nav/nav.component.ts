import {Component} from '@angular/core';

import { LoginComponent } from '../login/login.component'

@Component({
  selector: 'app-navigation',
  templateUrl: './nav.component.html',
  styleUrls: ['./nav.component.css'],
  entryComponents: [LoginComponent]
})
export class NavComponent {

  

  constructor() { }

  
}
