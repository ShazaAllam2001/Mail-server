import { Component, OnInit } from '@angular/core';
import { MailInfoService } from '../mail-info/mail-info.service';

@Component({
  selector: 'app-compose',
  templateUrl: './compose.component.html',
  styleUrls: ['./compose.component.css']
})
export class ComposeComponent implements OnInit {
  files: File[] = [];
  checkedFiles: File[] = [];
  receivers: string[] = [];
  checkedReceivers: string[] = [];

  constructor(private mailInfo: MailInfoService) { }

  ngOnInit(): void {
  }

  addReceiver() {
    var receiversElem = document.getElementById('receiver') as HTMLInputElement;
    if(!this.receivers.includes(receiversElem.value)) {
      this.receivers.push(receiversElem.value);
    } else {
      alert('The email entered is added before');
    }
  }
  checkReceiver(event: Event, receiver: string) {
    var checkBox = event.target as HTMLInputElement;
    if(checkBox.checked) {
      this.checkedReceivers.push(receiver);
    } else {
      this.checkedReceivers = this.checkedReceivers.filter(element => element!=receiver);
    }
  }
  deleteReceivers() {
    this.receivers = this.receivers.filter(element => !this.checkedReceivers.includes(element));
  }
  selectFiles(event: Event) {
    const target = event.target as HTMLInputElement;
    const files = target.files;
    const fileItem = files?.item(0);
    if(fileItem) {
      this.files.push(fileItem);
    }
  }
  checkFile(event: Event, file: File) {
    var checkBox = event.target as HTMLInputElement;
    if(checkBox.checked) {
      this.checkedFiles.push(file);
    } else {
      this.checkedFiles = this.checkedFiles.filter(element => element!=file);
    }
  }
  deleteFiles() {
    this.files = this.files.filter(element => !this.checkedFiles.includes(element));
  }
  /* get email inputs */
  getSubject() {
    var subjectElem = document.getElementById('subject') as HTMLInputElement;
    this.mailInfo.setSubject(subjectElem.value);
  }
  getReceivers() {
    this.mailInfo.setReceivers(this.receivers);
  }
  getPriority() {
    var priorityElems = document.getElementsByName('priority');
    var priority: number = 1;
    priorityElems.forEach(element => {
      var e = element as HTMLInputElement;
      if(e.checked) {
        priority = parseInt(e.value);
        return;
      }
    });
    this.mailInfo.setPriority(priority);
  }
  getBody() {
    var bodyElem = document.getElementById('body') as HTMLTextAreaElement;
    this.mailInfo.setBody(bodyElem.value);
  }
  getAttachments() {
    var attachments = new FormData();
    this.files.forEach(element => {
      attachments.append("file",element,element.name);
    });
    this.mailInfo.setAttachments(attachments);
  }
  getMailContent() {
    this.mailInfo.setTime();
    this.getSubject();
    this.getReceivers();
    this.getPriority();
    this.getBody();
    this.getAttachments();
  }
  /* send or draft */
  send() {
    this.getMailContent();
    this.mailInfo.postMail('sent');
  }
  draft() {
    this.getMailContent();
    this.mailInfo.postMail('draft');
  }

}
