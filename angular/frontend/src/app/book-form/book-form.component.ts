import { Component, OnInit } from '@angular/core';
import { BookService } from '../book.service';
import Book from '../entity/Book';
@Component({
  selector: 'app-book-form',
  templateUrl: './book-form.component.html',
  styleUrls: ['./book-form.component.scss']
})
export class BookFormComponent implements OnInit {
  book: Book=new Book(101, 'abc.com', 'Comic', 'Hulk', 'Marvel', '22-02-1995', 300.00, 'Marvel comic', 'true', 'XYZ' )
  constructor(public bookService: BookService) { }
  books:any=[];
  ngOnInit(): void {
    this.getBooks();
  }

  save(){
    console.log("Book Created");
    //Ajax Call
    const observable =this.bookService.saveBook(this.book);
    observable.subscribe((response)=>{
      console.log(response);
    },
    (error)=>{
      alert('Something Went Wrong')
    }
    )
  }

  private getBooks(){
    const obeservable=this.bookService.getBooks();
    obeservable.subscribe(books=>{
      this.books=books;
    })
  }
}
