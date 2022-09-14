import { Component, OnInit } from '@angular/core';
import { BookService } from '../book.service';
import Book from '../entity/Book';
@Component({
  selector: 'app-book-form',
  templateUrl: './book-form.component.html',
  styleUrls: ['./book-form.component.scss']
})
export class BookFormComponent implements OnInit {
  book: Book=new Book(101, 'abc.com', 'Comic', 'Iron Man', 'Marvel', '22-02-1995', 300.00, 'Marvel comic', 'true', 'XYZ' )
  title1:string='AAA';
  price1:number=1;
  publisher1:string='AAA';
  author1:string='AAA'

  constructor(public bookService: BookService) { }
  books:any=[];
  books1:any=[];
  ngOnInit(): void {
    this.getBooks();
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

  private getBooks(){
    const obeservable=this.bookService.getBooks();
    obeservable.subscribe(books=>{
      this.books=books;
    })
  }

   find(){
    console.log("Book Searched");
    const observable=this.bookService.findBooks(this.title1, this.price1,this.publisher1,this.author1)
    observable.subscribe(books1=>{
      this.books1=books1;
      console.log(books1);
      
    })
  }
}
