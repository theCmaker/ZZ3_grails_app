import { Routes, RouterModule } from '@angular/router';

import { IndexComponent } from './index/index.component';
import { QuestionComponent } from './question/question.component';
import { UserComponent } from './user/user.component';
import { LoginComponent } from './login/login.component';

// This variable is making the association between url and component to render
export const rootRouterConfig: Routes = [
    {
        path: '',
        redirectTo: 'index',
        pathMatch: 'full'
    },
    { // Show the index
        path: 'index',
        component: IndexComponent
    },
    { // Show a question
        path: 'question/:id',
        component: QuestionComponent
    },
    { // Show a user
        path: 'user/:id',
        component: UserComponent
    },
    { // Perform a user login
        path: 'login',
        component: LoginComponent
    }
];
