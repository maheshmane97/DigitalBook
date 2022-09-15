import { Component, OnInit } from '@angular/core';
import { BookService } from '../book.service';
import Book from '../entity/Book';
@Component({
  selector: 'app-book-form',
  templateUrl: './book-form.component.html',
  styleUrls: ['./book-form.component.scss']
})
export class BookFormComponent implements OnInit {
  title:string='';
  price:number=0;
  publisher:string='';
  author:string=''

  constructor(public bookService: BookService) { }
  books:any=[];
  ngOnInit(): void {
    
  }

 

  

   find(){
    console.log("Book Searched");
    const observable=this.bookService.findBooks(this.title, this.price,this.publisher,this.author)
    observable.subscribe(books=>{
      this.books=books;
      console.log(books);
    }, 
    (error)=>{
      alert("Something Went Wrong.! Please check enterd details")
    }
    )
  }
}
