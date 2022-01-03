import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { HttpClient, HttpResponse } from '@angular/common/http';

@Component({
  selector: 'app-sign-in',
  templateUrl: './sign-in.component.html',
  styleUrls: ['./sign-in.component.css']
})
export class SignInComponent implements OnInit {
  URL: string = "http://localhost:8081/api";
  username: string = "";
  password: string = "";
  email: string = "";

  constructor(private http: HttpClient, private router: Router) { }

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

    var url =  this.URL+'/signIn/' + this.email + '/' + this.password;
    this.http.get(url,{
      responseType: 'text'
    }).subscribe(response=>{
      console.log(response);
      if(response=='success') {
        this.router.navigate(['/Folder']);
      } else if(response=='mismatch') {
        alert("Email and password are mismatched");
      } else if(response=='notFound') {
        alert("This email does not exist");
      }
    });
  }
}
