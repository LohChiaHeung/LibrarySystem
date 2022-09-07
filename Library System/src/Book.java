/*
Group Project - Library book system?
1. Fang Xiang Lun   D200221C
2. Lai Jin Fatt     D200245C
3. Lim Hooi Ern     D200217C
4. Teh Jun Yuan     D200248C
5. Loh Chia Heung   D200262C
*/

//int datePublished =>> LocalDate datePublished

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Comparator;

public class Book implements Serializable{
    //----------Instance variable----------
    private LocalDate datePublished;
    private String bookId,bookTitle, bookAuthor, bookType, publisher;
    
    //----------Constructor----------
    public Book() {
    }
    //----------Contructor with parameter----------
    public Book(String bookId, String bookTitle, String bookAuthor, String bookType, String publisher, LocalDate datePublished) {
        this.bookId = bookId;
        this.bookTitle = bookTitle;
        this.bookAuthor = bookAuthor;
        this.bookType = bookType;
        this.publisher = publisher;
        this.datePublished = datePublished;
    }
    
    //----------Get Method----------
    public String getBookId(){return bookId;}
    public String getBookTitle(){return bookTitle;}
    public String getBookAuthor(){return bookAuthor;}
    public String getBookType(){return bookType;}
    public String getPublisher(){return publisher;}
    public LocalDate getDatePublished(){return datePublished;}
    
    //----------Set Method----------
    public void setBookId(String theBookId){bookId = theBookId;}
    public void setBookTitle(String theBookTitle){bookTitle = theBookTitle;}
    public void setBookAuthor(String theBookAuthor){bookAuthor = theBookAuthor;}
    public void setBookType(String theBookType){bookType = theBookType;}
    public void setPublisher(String thePublisher){publisher = thePublisher;}
    public void setDatePublished(LocalDate theDatePublished){datePublished = theDatePublished;}
    
    //----------toString Method----------
    public String toString(){
        return "********************************************"+
                "\nBook ID: " + bookId +
                "\nBook Title: "+ bookTitle + 
                "\nBook Author: " + bookAuthor +
                "\nBook Type: " + bookType +
                "\nPublihser: " + publisher +
                "\nDate Published: " + datePublished + "\n";
    }
    
    //----------Comparable----------
    public static Comparator<Book> BookIdComparator 
                          = new Comparator<Book>() {

	    public int compare(Book book1, Book book2) {
	      String bookName1 = book1.getBookId().toUpperCase();
	      String bookName2 = book2.getBookId().toUpperCase();
	      
	      //ascending order
	      return bookName1.compareTo(bookName2);
	    }
	};
}
