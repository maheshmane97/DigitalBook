import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import Author from './entity/Author';
import Book from './entity/Book';
const API_URL="http://localhost:8087/digitalbooks"
@Injectable({
  providedIn: 'root'
})
export class BookService {

  constructor(public client: HttpClient) { }
  saveBook(book: Book){
    const authorId=101;
    return this.client.post(API_URL+"/author/"+101+"/books", book)
  }

  getBooks(){
    return this.client.get(API_URL)
  }

  findBooks(title:string, price: number, publisher:string, author:string){
   return this.client.get(API_URL+"/books/search?title="+title+"&price="+price+"&publisher="+publisher+"&author="+author)
  }
}
