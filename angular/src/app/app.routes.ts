import { Routes, RouterModule } from '@angular/router';

import { IndexComponent } from "./index/index.component";
import { QuestionComponent } from "./question/question.component";

// This variable is making the association between url and component to render
export const rootRouterConfig: Routes = [
    {path: '', redirectTo: 'index', pathMatch: 'full'},
    { path: 'index', component: IndexComponent },
    {path:'question/:id', component:QuestionComponent},
];
