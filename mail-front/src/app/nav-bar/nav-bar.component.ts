import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-nav-bar',
  templateUrl: './nav-bar.component.html',
  styleUrls: ['./nav-bar.component.css']
})
export class NavBarComponent implements OnInit {
  opened = false;

  constructor(private router: Router) { }

  ngOnInit(): void {
    this.inbox('inbox');
 
  }

  toggleSideBar() {
    this.opened = !this.opened;
  }
  button_click(btn: HTMLLIElement) {
    var nav_btns = Array.from(document.getElementsByTagName("li"));
    nav_btns.forEach(element => {
      element.style.backgroundColor = "#f4f4f4";
    });
    btn.style.backgroundColor = "#dcdcdc";
  }
  compose(btn_id: string) {
    this.router.navigate(['/Home/Compose']);
    var btn = document.getElementById(btn_id) as HTMLLIElement;
    this.button_click(btn);
  }
  inbox(btn_id: string) {
    this.router.navigate(['/Home/Folder']);
    var btn = document.getElementById(btn_id) as HTMLLIElement;
    this.button_click(btn);
  }
  sent(btn_id: string) {
    this.router.navigate(['/Home/Folder']);
    var btn = document.getElementById(btn_id) as HTMLLIElement;
    this.button_click(btn);
  }
  draft(btn_id: string) {
    this.router.navigate(['/Home/Folder']);
    var btn = document.getElementById(btn_id) as HTMLLIElement;
    this.button_click(btn);
  }
  trash(btn_id: string) {
    this.router.navigate(['/Home/Folder']);
    var btn = document.getElementById(btn_id) as HTMLLIElement;
    this.button_click(btn);
  }
}
