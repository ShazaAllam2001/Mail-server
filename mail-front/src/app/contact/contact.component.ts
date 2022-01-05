import { Component, OnInit } from '@angular/core';
import { Contact } from '../mail-info/class/Contact';
import { MailInfoService } from '../mail-info/mail-info.service';

@Component({
  selector: 'app-contact',
  templateUrl: './contact.component.html',
  styleUrls: ['./contact.component.css']
})
export class ContactComponent implements OnInit {
  totalLength: number = 0;
  page: number = 1;
  contacts: Contact[] = [];
  checkedContacts: Contact[] = [];

  constructor(private mailInfo: MailInfoService) { }

  ngOnInit(): void {
    this.contacts = this.mailInfo.getContacts();
    this.totalLength = this.contacts.length;
  }

  checkContact(event: Event, contact: Contact) {
    var checkBox = event.target as HTMLInputElement;
    if(checkBox.checked) {
      this.checkedContacts.push(contact);
    } else {
      this.checkedContacts = this.checkedContacts.filter(element => element!=contact);
    }
  }
  deleteContacts() {
    this.contacts = this.contacts.filter(element => !this.checkedContacts.includes(element));
    /* */
  }
}
