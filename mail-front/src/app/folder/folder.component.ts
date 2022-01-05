import { Component, OnInit } from '@angular/core';
import { Mail } from '../mail-info/class/Mail';
import { MailInfoService } from '../mail-info/mail-info.service';

@Component({
  selector: 'app-folder',
  templateUrl: './folder.component.html',
  styleUrls: ['./folder.component.css']
})
export class FolderComponent implements OnInit {
  totalLength: number = 0;
  page: number = 1;
  mails: Mail[] = [];
  checkedMails: Mail[] = [];

  constructor(private mailInfo: MailInfoService) { }

  ngOnInit(): void {
    this.mails = this.mailInfo.getMails();
    this.totalLength = this.mails.length;
  }

  checkMail(event: Event, contact: Mail) {
    var checkBox = event.target as HTMLInputElement;
    if(checkBox.checked) {
      this.checkedMails.push(contact);
    } else {
      this.checkedMails = this.checkedMails.filter(element => element!=contact);
    }
  }
  deleteMails() {
    this.mails = this.mails.filter(element => !this.checkedMails.includes(element));
    /* */
  }
}
