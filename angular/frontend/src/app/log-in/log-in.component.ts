import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators, FormControl} from '@angular/forms';
import { AppComponent } from '../app.component';
import { AuthServiceService } from '../auth-service.service';
import { TokenStorageService } from '../token-storage.service';
@Component({
  selector: 'app-log-in',
  templateUrl: './log-in.component.html',
  styleUrls: ['./log-in.component.scss']
})
export class LogInComponent implements OnInit {
  isLoggedIn=false;
  isLoggedFailed=false;
  errorMessage='';
  roles: string[]=[];
  logInForm: any={
    userName:'',
    password:''
  }

  constructor(private authService: AuthServiceService, private tokenstorage: TokenStorageService) {
    AppComponent.isInitialHome=false;
   }

  ngOnInit(): void {

    if(this.tokenstorage.getToken()){
      this.isLoggedIn=true;
      this.roles=this.tokenstorage.getUser().roles;
    }
  }

  onSubmit():void{
    const{userName, password}=this.logInForm;
    this.authService.signIn(userName, password).subscribe(
      data=>{
        this.tokenstorage.saveToken(data.accessToken);
        this.tokenstorage.saveUser(data);
        this.isLoggedIn=true;
        this.isLoggedFailed=false;
        this.roles=this.tokenstorage.getUser().roles;
        this.reloadPage();
      },
      err=>{
        this.errorMessage=err.error.message;
        this.isLoggedFailed=true;
      }
    )
  }

  reloadPage():void{
    window.location.reload();
  }

}
