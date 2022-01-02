import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-sign-up',
  templateUrl: './sign-up.component.html',
  styleUrls: ['./sign-up.component.css']
})
export class SignUpComponent implements OnInit {
  URL: string = "http://localhost:8081";
  username: string = "";
  password: string = "";
  email: string = "";

  constructor(private router: Router) { }

  ngOnInit(): void {
  }

  signUp() {
    var usernameElement = document.getElementById("usernamest") as HTMLInputElement;
    this.username = usernameElement.value;
    var emailElement = document.getElementById("emailst") as HTMLInputElement;
    this.email = emailElement.value;
    var passwordElement = document.getElementById("passwordst") as HTMLInputElement;
    this.password = passwordElement.value;
    fetch(
      this.URL+'/signUp/' +
        this.username +
        "/" +
        this.email +
        "/" +
        this.password
    )
      .then((Response) => {
        return Response.json();
      })
      .then((data) => {
        if (data) {
          console.log(data);
          fetch(this.URL +"/getAccount")
            .then((Response) => {
              return Response.json();
            })
            .then((data) => {
              console.log(data);
              this.router.navigate(["/"]).catch(() => {});
            });
        } else {
          alert("error");
        }
      });
  }
}
