import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { SignInComponent } from './sign-in/sign-in.component';
import { SignUpComponent } from './sign-up/sign-up.component';
import { NavBarComponent } from './nav-bar/nav-bar.component';
import { ComposeComponent } from './compose/compose.component';
import { FolderComponent } from './folder/folder.component';
import { ContactComponent } from './contact/contact.component';
import { MailComponent } from './mail/mail.component';

const routes: Routes = [
  { path: '', component: SignInComponent },
  { path: 'SignUp', component: SignUpComponent },
  { path: 'Home', component: NavBarComponent,
    children: [
      { path: 'Folder', component: FolderComponent },
      { path: 'Compose', component: ComposeComponent },
      { path: 'Contact', component: ContactComponent },
      { path: 'Mail', component: MailComponent }
    ]
  },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})

export class AppRoutingModule { }

export const routingComponents = [SignInComponent, SignUpComponent];