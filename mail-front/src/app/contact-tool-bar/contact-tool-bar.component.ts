import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-contact-tool-bar',
  templateUrl: './contact-tool-bar.component.html',
  styleUrls: ['./contact-tool-bar.component.css']
})
export class ContactToolBarComponent implements OnInit {
  showWindow = false;

  constructor() { }

  ngOnInit(): void {
  }

  addContact() {

  }
  deleteContact() {

  }
}
