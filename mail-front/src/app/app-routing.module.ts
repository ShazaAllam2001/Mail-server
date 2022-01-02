import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ComposeComponent } from './compose/compose.component';
import { FolderComponent } from './folder/folder.component';
import { SignInComponent } from './sign-in/sign-in.component';
import { SignUpComponent } from './sign-up/sign-up.component';

const routes: Routes = [
  { path: '', component: SignInComponent},
  { path: 'SignUp', component: SignUpComponent},
  { path: 'Folder', component: FolderComponent},
  { path: 'Compose', component: ComposeComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})

export class AppRoutingModule { }

export const routingComponents = [SignInComponent, SignUpComponent];