import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AppRoutingModule } from './app-routing.module';
import { SidebarModule } from 'ng-sidebar';

import { AppComponent } from './app.component';
import { SignUpComponent } from './sign-up/sign-up.component';
import { SignInComponent } from './sign-in/sign-in.component';
import { ComposeComponent } from './compose/compose.component';
import { MailComponent } from './mail/mail.component';
import { InboxComponent } from './inbox/inbox.component';
import { ToolBarComponent } from './tool-bar/tool-bar.component';
import { FolderToolBarComponent } from './folder-tool-bar/folder-tool-bar.component';
import { FolderComponent } from './folder/folder.component';
import { NavBarComponent } from './nav-bar/nav-bar.component';

@NgModule({
  declarations: [
    AppComponent,
    SignUpComponent,
    SignInComponent,
    ComposeComponent,
    MailComponent,
    InboxComponent,
    ToolBarComponent,
    FolderToolBarComponent,
    FolderComponent,
    NavBarComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    SidebarModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
