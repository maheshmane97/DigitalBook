import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators} from '@angular/forms';
import { AppComponent } from '../app.component';
import { AuthServiceService } from '../auth-service.service';
import { NgModule }      from '@angular/core';

@Component({
  selector: 'app-sign-up',
  templateUrl: './sign-up.component.html',
  styleUrls: ['./sign-up.component.scss']
})
export class SignUpComponent implements OnInit {
  signUpform: any = {
    name: '',
    userName: '',
    emailId: '',
    password: ''
  };
  isSuccessful = false;
  isSignUpFailed = false;
  errorMessage = '';
  constructor(private authService: AuthServiceService) { 
    AppComponent.isInitialHome=false;
  }

  ngOnInit(): void {
  }

  onSubmit():void{
    const{name, userName, emailId, password}=this.signUpform;

    this.authService.signUp(name, userName, emailId, password).subscribe(
      response=>{
        console.log(response);
        this.isSuccessful=true;
        this.isSignUpFailed=false;
      },
      error=>{
        this.errorMessage=error.error.message;
        this.isSignUpFailed=true;
      }
    );
  }

}
