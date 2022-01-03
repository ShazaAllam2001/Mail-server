import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AppRoutingModule } from './app-routing.module';
import { SidebarModule } from 'ng-sidebar';
import { NgxPaginationModule } from 'ngx-pagination'; 
import { HttpClientModule } from '@angular/common/http';

import { AppComponent } from './app.component';
import { SignUpComponent } from './sign-up/sign-up.component';
import { SignInComponent } from './sign-in/sign-in.component';
import { ComposeComponent } from './compose/compose.component';
import { MailComponent } from './mail/mail.component';
import { FolderToolBarComponent } from './folder-tool-bar/folder-tool-bar.component';
import { FolderComponent } from './folder/folder.component';
import { NavBarComponent } from './nav-bar/nav-bar.component';
import { ContactComponent } from './contact/contact.component';
import { ContactToolBarComponent } from './contact-tool-bar/contact-tool-bar.component';

@NgModule({
  declarations: [
    AppComponent,
    SignUpComponent,
    SignInComponent,
    ComposeComponent,
    MailComponent,
    FolderToolBarComponent,
    FolderComponent,
    NavBarComponent,
    ContactComponent,
    ContactToolBarComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    SidebarModule,
    NgxPaginationModule,
    HttpClientModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
