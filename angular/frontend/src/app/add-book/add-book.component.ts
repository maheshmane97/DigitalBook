import { Component, OnInit } from '@angular/core';
import { BookService } from '../book.service';
import Book from '../entity/Book';

@Component({
  selector: 'app-add-book',
  templateUrl: './add-book.component.html',
  styleUrls: ['./add-book.component.scss']
})
export class AddBookComponent implements OnInit {
  book: Book=new Book(101, 'abc.com', 'Comic', 'Iron Man', 'Marvel', '22-02-1995', 300.00, 'Marvel comic', 'true', 'XYZ' )
  books:any=[];
  constructor(public bookService: BookService) { }

  ngOnInit(): void {
    this.getBooks();
  }
  private getBooks(){
    const obeservable=this.bookService.getBooks();
    obeservable.subscribe(books=>{
      this.books=books;
    })
  }

  save(){
    console.log("Book Created");
    //Ajax Call
    const observable =this.bookService.saveBook(this.book);
    observable.subscribe((response)=>{
      console.log(response);
      this.getBooks();
    },
    (error)=>{
      alert('Something Went Wrong')
    }
    )
  }
}
