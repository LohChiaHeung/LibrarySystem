/*
Group Project - Library book system?
1. Fang Xiang Lun   D200221C
2. Lai Jin Fatt     D200245C
3. Lim Hooi Ern     D200217C
4. Teh Jun Yuan     D200248C
5. Loh Chia Heung   D200262C
*/

import java.io.*;
import java.nio.file.*;
import java.time.LocalDate;
import java.util.*;
import javafx.collections.*;
import javafx.scene.control.Alert;

public class LibraryBook implements Serializable {
    //----------Instance Variable----------
    private ArrayList<Book> bookList;
    int index=0;
    String library = "Library.ser";
    
    public LibraryBook(){
        bookList = new ArrayList<>();
        //Default data
        /*bookList.add(new Book("CLA-001","The Call of the Wild","Jack London","Classic","Macmillan",LocalDate.of(1903,6,22)));
        bookList.add(new Book("CLA-002","Romeo and Juliet","William Shakespeare","Classic","Simon & Schuster.",LocalDate.of(2011,8,2)));
        bookList.add(new Book("FAN-001","The Lord of the Rings","J. R. R. Tolkien","Fantasy","Allen & Unwin",LocalDate.of(1954,7,29)));
        bookList.add(new Book("COM-001","Spider-Man","Stan Lee & Steve Ditko","Comic","Marvel Comics",LocalDate.of(1962,8,1)));
        bookList.add(new Book("NOV-001","The Da Vinci Code","Dan Brown","Novel","Doubleday",LocalDate.of(2003,4,1)));
        bookList.add(new Book("HOR-001","The Tell-Tale Heart","Edgar Allan Poe","Hrror","The Pioneer",LocalDate.of(1843,1,1)));*/
    }
    
//------------------------------ADD Function------------------------------
    public void add(Book newBook){
        boolean found = false;
        for(int i =0; i<bookList.size() && found == false; i++){
            Book b = bookList.get(i);
            if(b.getBookTitle().equalsIgnoreCase(newBook.getBookTitle())){
                found=true;
            }
        }
        //After checking book existance
        if(found==true){
            Error("Book Exist","There exist a book with the same title in library.");
        }else{
            bookList.add(newBook);
            Info("Book added successfully.","The book "+newBook.getBookTitle()+" added into library.");
        }
    }
//------------------------------REMOVE Function------------------------------
    public void remove(String bookInfo,int option){
        if(bookExist(bookInfo,option)==true){
            String idTemp = bookList.get(index).getBookId();
            String titleTemp = bookList.get(index).getBookTitle();
            deleteBook(bookList.get(index).getBookId());
            bookList.remove(index);
            Info("Book removed succesfully.","The book with id: " + idTemp + " ,Title: '" + titleTemp + "' had been removed.");
        }else{
            Error("Search failed","Book not found!");
        }
    }
    
//------------------------------SEARCH Function------------------------------
    public void search(String bookId,int option){
        if(bookExist(bookId,option)==true){
            String idTemp = bookList.get(index).getBookId();
            String titleTemp = bookList.get(index).getBookTitle();
            Info("Book Found!","The book with id: " + idTemp + " ,Title: '" + titleTemp + "' found in library.");
        }else{
            Error("Book not found!", "The book cannot be found.");
        }
    }
    
//------------------------------UPDATE Function------------------------------ 
    public void update(String bookId, String bookTitle, String bookAuthor, String bookType, String publisher, LocalDate datePublished){
        if(bookExist(bookId,1)==true){
            bookList.set(index,new Book(bookId,bookTitle,bookAuthor,bookType,publisher,datePublished));
            Info("Update successfully","The book details has been updated.");
        }else{
            Error("Book not found.","Please add the book as a new book.");
        }
    }
    
    
    //--------------------TableView--------------------
    public ObservableList setTableContent(){
        ObservableList<Book> data = FXCollections.<Book>observableArrayList();
        data.addAll(bookList);
        return data;
    }
    
    //-------------------------------WriteLibrary-------------------------------
    public void writeLibrary(){
        try{
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(library));
            out.writeObject(bookList); //write arraylist into file
            out.close();
        }catch(IOException ioe){
            Error("IO Exception","File output error.");
        }
    }
    
    //-------------------------------ReadLibrary-------------------------------
    public void readLibrary(){
        try{
            File f = new File(library);
            //Check file exist or not
            if(f.exists()){
                ObjectInputStream in = new ObjectInputStream(new FileInputStream(library));
                bookList = (ArrayList<Book>) in.readObject();
                //close when done
                in.close();
                Collections.sort(bookList,Book.BookIdComparator);
            }else{
                Info("File not found","No book has been found.\nFirst Time user");
            }
        }catch(IOException ioe){
            Error("IO Exception","File input error.");
        } catch (ClassNotFoundException ex) {
            Error("Class Not Found Exception","Class not found.");
        }
    }
    
    //----------deleteBook----------
    public void deleteBook(String bookId){
        Path p = Paths.get(bookId+".txt");
        try{
            Files.delete(p);
        }catch(Exception e){
            Error("Error",bookId + ".txt File not deleted");
        }
    }
    
    //----------Check book Existance----------
    public boolean bookExist(String info,int option){
        boolean found = false;
        for(int i =0; i<bookList.size(); i++){
        Book b = bookList.get(i);
            if(option==1){
                if(b.getBookId().equalsIgnoreCase(info)){
                    found=true;
                    index = i;
                }
            }else{
                if(b.getBookTitle().equalsIgnoreCase(info)){
                    found=true;
                    index = i;
                }
            }
        }
        return found;
    }
    
    public void Error(String e,String em) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(e);
        alert.setHeaderText(null);
        alert.setContentText(em);
        alert.show();
    }    
    public void Info(String i,String im) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(i);
        alert.setHeaderText(null);
        alert.setContentText(im);
        alert.show();
    }
}
