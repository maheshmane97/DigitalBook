import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

const AUTH_API = 'http://localhost:8087/userauth';
const httpOptions={
  headers: new HttpHeaders({'Content-Type': 'application/json'})
}
@Injectable({
  providedIn: 'root'
})
export class AuthServiceService {

  constructor(private http: HttpClient) { }

  signUp(name: string, userName:string, emailId:string, password:string): Observable<any>{
    return this.http.post(AUTH_API+'/signup',{
      name,
      userName,
      emailId,
      password
    }, httpOptions);
  }

  signIn(userName: string, password: string): Observable<any> {
    return this.http.post(AUTH_API + '/signin', {
      userName,
      password
    }, httpOptions);
  }
  

}
