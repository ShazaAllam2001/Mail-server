import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { HttpClient, HttpResponse } from '@angular/common/http';

@Component({
  selector: 'app-sign-up',
  templateUrl: './sign-up.component.html',
  styleUrls: ['./sign-up.component.css']
})
export class SignUpComponent implements OnInit {
  URL: string = "http://localhost:8081/api";
  username: string = "";
  password: string = "";
  email: string = "";

  constructor(private http: HttpClient, private router: Router) { }

  ngOnInit(): void {
  }

  signUp() {
    var usernameElement = document.getElementById("usernamest") as HTMLInputElement;
    this.username = usernameElement.value;
    var emailElement = document.getElementById("emailst") as HTMLInputElement;
    this.email = emailElement.value;
    var passwordElement = document.getElementById("passwordst") as HTMLInputElement;
    this.password = passwordElement.value;

    var url =  this.URL+'/signUp/' + this.username + '/' + this.email + '/' + this.password;
    this.http.get(url).subscribe(response=>{
      console.log(response);
      if(response) {
        this.router.navigate(['/Folder']);
      } else {
        alert("This email already exists!");
      } 
    });
  }
}
