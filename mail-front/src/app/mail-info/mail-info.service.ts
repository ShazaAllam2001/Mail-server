import { Injectable } from '@angular/core';
import { Mail } from './class/Mail';
import { HttpClient, HttpResponse } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class MailInfoService {
  URL: string = "http://localhost:8081/api";
  mail: Mail = new Mail();
  attachments: FormData = new FormData();

  constructor(private http: HttpClient) { }

  getMail() {
    return this.mail;
  }
  setTime() {
    this.mail.time = new Date().toISOString();
  }
  setSubject(subject: string) {
    this.mail.subject = subject;
  }
  setReceivers(receivers: string[]) {
    this.mail.recievers = receivers;
  }
  setPriority(priority: number) {
    this.mail.priority = priority;
  }
  setBody(body: string) {
    this.mail.body = body;
  }
  setAttachments(attachments: FormData) {
    this.attachments = attachments;
  }
  postMail(folder: string) {
    /* assign mail to the correct folder */
    this.mail.folder = folder;
    var creation_done: boolean = true;
    /* send attachments */
    var attach_url = this.URL + "/attachments";
    this.http.post(attach_url, this.attachments).subscribe(response => {
      creation_done = creation_done && Boolean(response);
    });
    /* send mail content */
    var compose_url = this.URL + "/compose";
    this.http.post(compose_url, JSON.stringify(this.mail)).subscribe(response => {
      creation_done = creation_done && Boolean(response);
    });
    /* check if creation done correctly */
    if(creation_done) {
      alert("The mail is created");
    } else {
      alert("The mail failed to be created");
    }
  }
  
}
