
import java.time.LocalDate;

/*
Group Project - Library book system?
1. Fang Xiang Lun   D200221C
2. Lai Jin Fatt     D200245C
3. Lim Hooi Ern     D200217C
4. Teh Jun Yuan     D200248C
5. Loh Chia Heung   D200262C
*/

public class TestLibraryBook {
    public static void main(String[] args){
    LibraryBook myList = new LibraryBook();
    
    //-------------------------DEFAULT DATA---------------------------
        myList.add(new Book("CLA-001","The Call of the Wild","Jack London","Classic","Macmillan",LocalDate.of(1903,6,22)));
        myList.add(new Book("CLA-002","Romeo and Juliet","William Shakespeare","Classic","Simon & Schuster.",LocalDate.of(2011,8,2)));
        myList.add(new Book("FAN-001","The Lord of the Rings","J. R. R. Tolkien","Fantasy","Allen & Unwin",LocalDate.of(1954,7,29)));
        myList.add(new Book("COM-001","Spider-Man","Stan Lee & Steve Ditko","Comic","Marvel Comics",LocalDate.of(1962,8,1)));
        myList.add(new Book("NOV-001","The Da Vinci Code","Dan Brown","Novel","Doubleday",LocalDate.of(2003,4,1)));
        myList.add(new Book("HOR-001","The Tell-Tale Heart","Edgar Allan Poe","Hrror","The Pioneer",LocalDate.of(1843,1,1)));
    //myList.printBook();
    
    //-------------------------Add-------------------------
    myList.add(new Book("BBB-333","Title5","Person5","fantasy","Pub A",LocalDate.of(1843,1,1)));
    System.out.println("");
    //myList.printBook();
    
    //-------------------------Remove-------------------------
    myList.remove("BBB-333",1);
    myList.remove("Spider-Man",2);
    myList.remove("EEE-000",1);
    System.out.println("");
    
    
    
    //-------------------------Search-------------------------
    myList.search("CLA-002",1);
    myList.search("The Da Vinci Code",2);
    myList.search("BBB-111",1);
    System.out.println("");
    
    //-------------------------Update-------------------------
    
    myList.update("HOR-001","Title6","Person6","classic","Pub A",LocalDate.of(1843,1,1));
    myList.update("BBB-555","Title6","Person6","classic","Pub A",LocalDate.of(1843,1,1));
    System.out.println("");
    //myList.printBook();
    
    
    }
}
