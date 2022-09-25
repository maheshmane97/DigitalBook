import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { TokenStorageService } from './token-storage.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent implements OnInit{
  static isInitialHome =true;
  title = 'DigitalBooks';
  referenceName = AppComponent;
  isLoggedIn = false;
  BookForm=false;
  AddBook=false;
  ReaderForm=false;
  userName?: string;
  private roles: string[]=[];

  constructor(private router: Router, private tokenStorageService: TokenStorageService){
    AppComponent.isInitialHome=true;
  }
  ngOnInit(): void {
    this.isLoggedIn=!!this.tokenStorageService.getToken();
    if(this.isLoggedIn){
      const user=this.tokenStorageService.getUser();
      this.roles=this.roles;

      this.BookForm=this.roles.includes('ROLE_READER');
      this.AddBook=this.roles.includes('ROLE_READER');
      this.ReaderForm=this.roles.includes('ROLE_READER');

      this.userName=user.userName;
    }
  }

  logout(): void {
    this.tokenStorageService.signOut();
    window.location.reload();
  }
}
