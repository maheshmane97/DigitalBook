import { Component, OnInit } from '@angular/core';
import { BookService } from '../book.service';

@Component({
  selector: 'app-reader-form',
  templateUrl: './reader-form.component.html',
  styleUrls: ['./reader-form.component.scss']
})
export class ReaderFormComponent implements OnInit {
  readerMailId:string=''
  bookId:number=0
  book1:any=[]
  message:any
  constructor(public bookService: BookService) { }

  ngOnInit(): void {
  }
  
  search(){
    console.log("Books Searched by Reader");
    const observable=this.bookService.searchBook(this.readerMailId, this.bookId)
    observable.subscribe(res=>{
      this.book1=res;
      console.log(res);
      this.book1=res;
    },
    (error)=>{
        console.log("error:", error);
      this.message=error;
      this.message="Something Went wrong.! Please check Details"
    }
    )
  }
}
