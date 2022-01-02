import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-sign-in',
  templateUrl: './sign-in.component.html',
  styleUrls: ['./sign-in.component.css']
})
export class SignInComponent implements OnInit {
  URL: string = "http://localhost:8081";
  username: string = "";
  password: string = "";
  email: string = "";

  constructor(private router: Router) { }

  ngOnInit(): void {
  }

  signUp(): void {
    const navigationDetails: string[] = ['/SignUp'];
    this.router.navigate(navigationDetails);
  }
  signIn() {
    var emailElement = document.getElementById("emailst") as HTMLInputElement;
    this.email = emailElement.value;
    var passwordElement = document.getElementById("passwordst") as HTMLInputElement;
    this.password = passwordElement.value;
    fetch(
      this.URL+"/signIn/" + this.email + "/" + this.password
    )
      .then((Response) => {
        return Response.json();
      })
      .then((data) => {
        if (data) {
          this.username = data.username;
          this.password = data.password;
          this.email = data.name;
          //this.router.push("/Inbox").catch(() => {});
          fetch(this.URL+"/getAccount")
            .then((Response) => {
              return Response.json();
            })
        } else {
          alert("error");
        }
      });
  }
}
