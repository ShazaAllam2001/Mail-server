import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { MailInfoService } from '../mail-info/mail-info.service';

@Component({
  selector: 'app-nav-bar',
  templateUrl: './nav-bar.component.html',
  styleUrls: ['./nav-bar.component.css']
})
export class NavBarComponent implements OnInit {
  URL: string = "http://localhost:8081/api";
  opened = false;

  constructor(private http: HttpClient, private router: Router, private mailInfo: MailInfoService) { }

  ngOnInit(): void {
    this.changeFolder('inbox');
 
  }

  toggleSideBar() {
    this.opened = !this.opened;
  }
  signOut() {
    var url = this.URL + "/signOut";
    this.http.get(url).subscribe(response => {
      if(response) {
        this.router.navigate(['']);
      } else {
        alert("Can not Sign out. Try again!");
      }
    });

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
  contacts(btn_id: string) {
    this.router.navigate(['/Home/Contact']);
    var btn = document.getElementById(btn_id) as HTMLLIElement;
    this.button_click(btn);
  }
  changeFolder(btn_id: string) {
    this.router.navigate(['/Home/Folder']);
    var btn = document.getElementById(btn_id) as HTMLLIElement;
    this.button_click(btn);
    this.mailInfo.folder = btn_id;
  }
  
}
