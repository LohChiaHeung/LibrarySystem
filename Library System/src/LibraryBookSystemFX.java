/*
Group Project - Library book system?
1. Fang Xiang Lun   D200221C
2. Lai Jin Fatt     D200245C
3. Lim Hooi Ern     D200217C
4. Teh Jun Yuan     D200248C
5. Loh Chia Heung   D200262C
*/

//Add loginForm for better appearance. "Login" and "Create new user" function


import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javafx.application.Application;
import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.*;
import javafx.scene.layout.*;
import javafx.scene.text.*;
import javafx.stage.Stage;

public class LibraryBookSystemFX extends Application {
    //----------UI CONTROLS----------
    //Login
    Text tW = new Text("SUC Library System");
    String checkUser,checkPw;
    TextField tfName = new TextField(),tfcName = new TextField();
    PasswordField pfpw = new PasswordField(),pfCpw = new PasswordField();
    Button btnLogin = new Button("Login"),
            btnCreate = new Button("New user?");
    Hyperlink hForgot = new Hyperlink("Forgot password?");
    TextInputDialog tidCreate = new TextInputDialog();
    
    //Main
    TextField tfID = new TextField(),
              tfrsID = new TextField(),
              tfTitle = new TextField(),
              tfrsTitle = new TextField(),
              tfAuthor = new TextField(),
              tfPub = new TextField();
    Button btnAdd = new Button("Add"),
           btnRemove = new Button("Remove"),
           btnSearch = new Button("Search"),
           btnUpdate = new Button("Update");
    ComboBox cbType = new ComboBox();
    RadioButton rbID = new RadioButton("ID"),
                rbTitle = new RadioButton("Book Title");
    Text tAU = new Text("Add & Update Function"),
         tRS = new Text("Remove & Search Function");
    DatePicker dpdate = new DatePicker();
    //-----Instance variable-----
    LibraryBook bookList = new LibraryBook();
    TableView<Book> table = new TableView<>(),
                    table1 = new TableView<>();
    MemberLogin memList = new MemberLogin();
    
    @Override
    public void start(Stage primaryStage) {
        bookList.readLibrary();
        memList.readMember();
        /*-------------------------------------------------------------------------------------
        --------------------------------------LOGIN FORM---------------------------------------
        ---------------------------------------------------------------------------------------*/
        
        //--------------------HBox(Welcome)--------------------
        HBox hbtW = new HBox();
        hbtW.setAlignment(Pos.CENTER);
        hbtW.setPadding(new Insets(30,0,10,0));
        hbtW.getChildren().addAll(tW);
        hbtW.setAlignment(Pos.CENTER);
        //Text setting(welcome)
        tW.setFont(Font.font ("Verdana", FontWeight.BOLD, 17));
        tW.setTextAlignment(TextAlignment.CENTER);
        hForgot.setTextAlignment(TextAlignment.LEFT);
        
        //--------------------GridPane(Login)--------------------
        GridPane gpLogin = new GridPane();
        //GridPane setting
        gpLogin.setHgap(15);
        gpLogin.setVgap(15);
        gpLogin.setPadding(new Insets(15,15,15,15));
        gpLogin.setAlignment(Pos.CENTER);
        //1st row
        gpLogin.add(new Label("User Name"), 0, 0);
        gpLogin.add(new Label(":"), 1, 0);
        gpLogin.add(tfName, 2, 0);
        //2nd row
        gpLogin.add(new Label("Password"), 0, 1);
        gpLogin.add(new Label(":"), 1, 1);
        gpLogin.add(pfpw, 2, 1);
        //TextField setting
        tfName.setPrefColumnCount(7);
        pfpw.setPrefColumnCount(7);
        
        //--------------------HBox(btnLogin)--------------------
        HBox hbLogin = new HBox(20);
        hbLogin.setAlignment(Pos.BOTTOM_LEFT);
        hbLogin.setPadding(new Insets(0,0,0,95));
        hbLogin.getChildren().addAll(hForgot);
        HBox hbLogin1 = new HBox(20);
        hbLogin1.setAlignment(Pos.BOTTOM_RIGHT);
        hbLogin1.setPadding(new Insets(15,80,0,0));
        hbLogin1.getChildren().addAll(btnLogin,btnCreate);
        
        //--------------------VBox(Login Main)--------------------
        VBox vbLogin = new VBox(0);
        vbLogin.getChildren().addAll(hbtW, gpLogin, hbLogin, hbLogin1);
        Scene scene = new Scene(vbLogin,400,300);
        scene.getStylesheets().add(getClass().getResource("login.css").toExternalForm());
        vbLogin.setId("image");
        
        //--------------------TextInputDialog(Create New User)--------------------
        tidCreate.setHeaderText("Please enter USER NAME and PASSWORD\nto create a new account.");
        GridPane gpCreate = new GridPane();
        //GridPane setting
        gpCreate.setHgap(5);
        gpCreate.setVgap(5);
        gpCreate.setPadding(new Insets(15,15,15,15));
        gpCreate.setAlignment(Pos.CENTER);
        //1st row
        gpCreate.add(new Label("User Name"), 0, 0);
        gpCreate.add(new Label(":"),1, 0);
        gpCreate.add(tfcName, 2, 0);
        //2nd row
        gpCreate.add(new Label("Password"), 0, 1);
        gpCreate.add(new Label(":"),1, 1);
        gpCreate.add(pfCpw, 2, 1);
        tidCreate.getDialogPane().setContent(gpCreate);
        
        
        //-------------------------------------------------------------------
        //------------------------------SCENE 1------------------------------
        //-------------------------------------------------------------------
        
        //-----------CREATE MENU-----------
        //-----------MENU_BAR----------
        MenuBar menubar = new MenuBar();
        //-----------MENU----------
        Menu operationMenu = new Menu("Operation"),
             helpMenu = new Menu("Help");
        //-----------MENU_ITEMS-----------
        MenuItem addUpdate = new MenuItem("Add & Update"),
                 removeSearch = new MenuItem("Remove & Search"),
                 printItem = new MenuItem("Print"),
                 exitItem = new MenuItem("Exit"),
                 logoutItem = new MenuItem("Log Out");
        //----------ADD MENU_ITEMS TO MENU----------
        operationMenu.getItems().addAll(addUpdate,removeSearch,printItem);
        helpMenu.getItems().addAll(exitItem,logoutItem);
        //-----------ADD MENU TO MENU_BAR-----------
        menubar.getMenus().addAll(operationMenu,helpMenu);
        //----------END MENU-----------
        
        
        //Text setting(State Function)
        tAU.setFont(Font.font ("Verdana", FontWeight.BOLD, 17));
        tAU.setTextAlignment(TextAlignment.CENTER);
        HBox hbt1 = new HBox();
        hbt1.getChildren().add(tAU);
        hbt1.setAlignment(Pos.CENTER);
        hbt1.setPadding(new Insets(10,0,0,0));
        
        //----------GRID_PANE----------
        GridPane gp = new GridPane();
        gp.setHgap(15);
        gp.setVgap(15);
        gp.setPadding(new Insets(15,15,15,15));
        gp.setAlignment(Pos.CENTER);
        //-----R1-----
        gp.add(new Label("Book Type:"),0,0);
        gp.add(new Label(":"),1,0);
        gp.add(cbType,2,0);
        cbType.setPromptText("book type");
        cbType.getItems().addAll("Classic","Fantasy","Comic","Novel","Horror");
        
        //-----R2-----
        gp.add(new Label("Book ID"),0,1);
        gp.add(new Label(":"),1,1);
        gp.add(tfID,2,1);
        tfID.setPromptText("Choose a book type");
        //-----R3-----
        gp.add(new Label("Book Title:"),0,2);
        gp.add(new Label(":"),1,2);
        gp.add(tfTitle,2,2);
        tfTitle.setPromptText("Harry Potter and the Philosopher's Stone");
        //-----R4-----
        gp.add(new Label("Author:"),0,3);
        gp.add(new Label(":"),1,3);
        gp.add(tfAuthor,2,3);
        tfAuthor.setPromptText("J.K. Rowling");
        //-----R5-----
        gp.add(new Label("Publisher:"),0,4);
        gp.add(new Label(":"),1,4);
        gp.add(tfPub,2,4);
        tfPub.setPromptText("Bloomsbury");
        //-----R6-----
        gp.add(new Label("Date Published:"),0,5);
        gp.add(new Label(":"),1,5);
        gp.add(dpdate,2,5);
        dpdate.setPromptText(LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yy")));
        dpdate.setId("type");
        //gp.getStyleClass().add("label");
        //-----------END GRID_PANE------------
        
        
        //-----------HBOX-----------
        //-----ADD BUTTON------
        HBox hb = new HBox(10);
        hb.getChildren().addAll(btnAdd,btnUpdate);
        hb.setPadding(new Insets(0,0,10,0));
        hb.setAlignment(Pos.CENTER);
        //----------END HBOX-----------
        
        
        //------------COLUMN FOR TABLE_VIEW------------
        //------ID COL------
        TableColumn<Book, String> idCol = new TableColumn("Book ID");
        idCol.setCellValueFactory(new PropertyValueFactory<>("bookId"));
        idCol.prefWidthProperty().bind(table.widthProperty().multiply(0.13));
        //idCol.setId("id");
        //------TITLE COL-----
        TableColumn<Book, String> titleCol = new TableColumn("Book Title");
        titleCol.setCellValueFactory(new PropertyValueFactory<>("bookTitle"));
        titleCol.prefWidthProperty().bind(table.widthProperty().multiply(0.23));
        //titleCol.setId("title");
        //------AUTHOR COL-----
        TableColumn<Book, String> authorCol = new TableColumn("Book Author");
        authorCol.setCellValueFactory(new PropertyValueFactory<>("bookAuthor"));
        authorCol.prefWidthProperty().bind(table.widthProperty().multiply(0.17));
        //authorCol.setId("author");
        //-----BOOK_TYPE COL-----
        TableColumn<Book, String> typeCol = new TableColumn("Type");
        typeCol.setCellValueFactory(new PropertyValueFactory<>("bookType"));
        typeCol.prefWidthProperty().bind(table.widthProperty().multiply(0.12));
        //typeCol.setId("type");
        //-----PUBLISHER COL-----
        TableColumn<Book, String> pubCol = new TableColumn("Publisher");
        pubCol.setCellValueFactory(new PropertyValueFactory<>("publisher"));
        pubCol.prefWidthProperty().bind(table.widthProperty().multiply(0.15));
        //pubCol.setId("pub");
        //-----DATE COL-----
        TableColumn<Book, LocalDate> dateCol = new TableColumn("Date Published");
        dateCol.setCellValueFactory(new PropertyValueFactory<>("datePublished"));
        dateCol.prefWidthProperty().bind(table.widthProperty().multiply(0.20));
        //dateCol.setId("date");
        idCol.setResizable(false);
        typeCol.setResizable(false);
        dateCol.setResizable(false);
        idCol.setStyle("-fx-alignment: CENTER;");
        typeCol.setStyle("-fx-alignment: CENTER;");
        dateCol.setStyle("-fx-alignment: CENTER;");
        table.getColumns().addAll(idCol,titleCol,authorCol,typeCol,pubCol,dateCol);
        
        //------------COLUMN FOR TABLE_VIEW------------
        //------ID COL------
        TableColumn<Book, String> idCol1 = new TableColumn("Book ID");
        idCol1.setCellValueFactory(new PropertyValueFactory<>("bookId"));
        idCol1.prefWidthProperty().bind(table.widthProperty().multiply(0.13));
        //idCol1.setId("id");
        //------TITLE COL-----
        TableColumn<Book, String> titleCol1 = new TableColumn("Book Title");
        titleCol1.setCellValueFactory(new PropertyValueFactory<>("bookTitle"));
        titleCol1.prefWidthProperty().bind(table.widthProperty().multiply(0.23));
        //titleCol1.setId("title");
        //------AUTHOR COL-----
        TableColumn<Book, String> authorCol1 = new TableColumn("Book Author");
        authorCol1.setCellValueFactory(new PropertyValueFactory<>("bookAuthor"));
        authorCol1.prefWidthProperty().bind(table.widthProperty().multiply(0.17));
        //authorCol1.setId("author");
        //-----BOOK_TYPE COL-----
        TableColumn<Book, String> typeCol1 = new TableColumn("Type");
        typeCol1.setCellValueFactory(new PropertyValueFactory<>("bookType"));
        typeCol1.prefWidthProperty().bind(table.widthProperty().multiply(0.12));
        //typeCol1.setId("type");
        //-----PUBLISHER COL-----
        TableColumn<Book, String> pubCol1 = new TableColumn("Publisher");
        pubCol1.setCellValueFactory(new PropertyValueFactory<>("publisher"));
        pubCol1.prefWidthProperty().bind(table.widthProperty().multiply(0.15));
        //pubCol1.setId("pub");
        //-----DATE COL-----
        TableColumn<Book, LocalDate> dateCol1 = new TableColumn("Date Published");
        dateCol1.setCellValueFactory(new PropertyValueFactory<>("datePublished"));
        dateCol1.prefWidthProperty().bind(table.widthProperty().multiply(0.20));
        //dateCol1.setId("date");
        idCol1.setResizable(false);
        typeCol1.setResizable(false);
        dateCol1.setResizable(false);
        idCol1.setStyle("-fx-alignment: CENTER;");
        typeCol1.setStyle("-fx-alignment: CENTER;");
        dateCol1.setStyle("-fx-alignment: CENTER;");
        table1.getColumns().addAll(idCol1,titleCol1,authorCol1,typeCol1,pubCol1,dateCol1);
        //-------------END COLUMN FOR TABLE_VIEW------------------
        
        
        //-----------VBOX(main SCENE 1)-----------
        VBox vb = new VBox(5);
        vb.getChildren().addAll(menubar,hbt1,gp,hb,table);
        vb.setAlignment(Pos.CENTER);
        vb.getStyleClass().add("image");
        //------------END VBOX----------
        Scene scene1 = new Scene(vb, 640, 650);
        scene1.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
        
        
        
        //-------------------------------------------------------------------
        //------------------------------SCENE 2------------------------------
        //-------------------------------------------------------------------
        
        //----------HBox(text)----------
        HBox hbT2 = new HBox();
        hbT2.getChildren().add(tRS);
        hbT2.setAlignment(Pos.CENTER);
        hbT2.setPadding(new Insets(10,0,0,0));
        tRS.setFont(Font.font ("Verdana", FontWeight.BOLD, 17));
        tRS.setTextAlignment(TextAlignment.CENTER);
        
        //----------HBox(radio button)----------
        HBox hbRB = new HBox(10);
        hbRB.getChildren().addAll(rbID,rbTitle);
        hbRB.setAlignment(Pos.CENTER);
        hbRB.setPadding(new Insets(15,0,0,0));
        //RadioButton setting
        final ToggleGroup rbGroup = new ToggleGroup();
        rbID.setToggleGroup(rbGroup);
        rbTitle.setToggleGroup(rbGroup);
        rbID.setSelected(true);
        
        //----------GridPane(tfrsID & tfrsTitle)----------
        GridPane gpID = new GridPane();
        gpID.setHgap(15);
        gpID.setVgap(15);
        gpID.setPadding(new Insets(15,15,15,15));
        gpID.setAlignment(Pos.CENTER);
        gpID.add(new Label("Book ID"),0,0);
        gpID.add(new Label(":"),1,0);
        gpID.add(tfrsID,2,0);
        
        GridPane gpTitle = new GridPane();
        gpTitle.setHgap(15);
        gpTitle.setVgap(15);
        gpTitle.setPadding(new Insets(15,15,15,15));
        gpTitle.setAlignment(Pos.CENTER);
        gpTitle.add(new Label("Book Title"),0,0);
        gpTitle.add(new Label(":"),1,0);
        gpTitle.add(tfrsTitle,2,0);
        //gpTitle.getStyleClass().add("label");
        
        //----------HBox(btnRemove,btnSearch)----------
        HBox hbRS = new HBox(10);
        hbRS.getChildren().addAll(btnRemove,btnSearch);
        hbRS.setAlignment(Pos.CENTER);
        hbRS.setPadding(new Insets(0,0,10,0));
        
        //----------VBox(Main SCENE2)----------
        VBox vb1 = new VBox();
        vb1.setAlignment(Pos.CENTER);
        vb1.getChildren().addAll(hbT2,hbRB,gpID,hbRS,table1);
        vb1.getStyleClass().add("image");
        Scene scene2 = new Scene(vb1, 640, 550);
        scene2.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
        //---------END SCENE 2----------
        
        //-----------Primary Stage----------
        primaryStage.setTitle("Library Book System");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();  
        print();
        
        //-------------------------------------------------------------------
        //---------------------PROCESS EVENTS--------------------------------
        //-------------------------------------------------------------------
        //-----------FOR MENU-----------
        addUpdate.setOnAction(e -> {
            vb.getChildren().clear();
            vb1.getChildren().clear();
            vb.getChildren().addAll(menubar,hbt1,gp,hb,table);
            primaryStage.setScene(scene1);
        });
        removeSearch.setOnAction(e -> {
            vb.getChildren().clear();
            vb1.getChildren().clear();
            vb1.getChildren().addAll(menubar,hbT2,hbRB,gpID,hbRS,table1);
            primaryStage.setScene(scene2);
            tfrsID.requestFocus();
        });
        printItem.setOnAction(e-> print());
        exitItem.setOnAction(e->System.exit(0));
        logoutItem.setOnAction(e->{
            primaryStage.setScene(scene);
            Info("Log out","Log out successfully!");
            tfName.requestFocus();
        });
        //-----------FOR BUTTON----------
        btnLogin.setOnAction(e-> {
            checkUser = tfName.getText();
            checkPw = pfpw.getText();
            if(login(checkUser,checkPw)==true){
                primaryStage.setScene(scene1);
                
            };
        });
        hForgot.setOnAction(e-> {
            Info("Forgot Password","Username & Password can be found \nin the UserId,PW.txt.");
            tfName.requestFocus();
        });
        btnCreate.setOnAction(e-> create());
        btnAdd.setOnAction(e -> add());
        btnRemove.setOnAction(e -> remove());
        btnUpdate.setOnAction(e-> update());
        btnSearch.setOnAction(e-> search());
        rbID.setOnAction(e->{
            vb1.getChildren().remove(gpTitle);
            vb1.getChildren().add(3, gpID);
            clear(6);
        });
        rbTitle.setOnAction(e->{
            vb1.getChildren().remove(gpID);
            vb1.getChildren().add(3, gpTitle);
            clear(7);
        });
        //-----------END PROCESS EVENTS------------
        
        
        //-----------KEYPRES EXCEPTION--------------
        tfID.addEventFilter(KeyEvent.KEY_PRESSED, event -> {
            if(event.getCode() == KeyCode.ENTER ||event.getCode() == KeyCode.BACK_SPACE ||event.getCode() == KeyCode.LEFT ||event.getCode() == KeyCode.RIGHT ||event.getCode() == KeyCode.TAB ||event.getCode() == KeyCode.SHIFT ||event.getCode() == KeyCode.CAPS ||event.getCode() == KeyCode.PERIOD||event.getCode().isLetterKey()||event.getCode().isDigitKey() ||event.getCode().isKeypadKey()||event.getCode() == (KeyCode.NUM_LOCK)||event.getCode() == (KeyCode.SLASH) ||event.getCode() == KeyCode.TAB)
                {} 
            else {
                Error("BOOK ID can only be alphabet and number.");
                clear(1);
            }
        });
        tfAuthor.addEventFilter(KeyEvent.KEY_PRESSED, event -> {
            if(event.getCode() == KeyCode.ENTER ||event.getCode() == KeyCode.BACK_SPACE ||event.getCode() == KeyCode.LEFT ||event.getCode() == KeyCode.RIGHT ||event.getCode() == KeyCode.TAB ||event.getCode() == KeyCode.SHIFT ||event.getCode() == KeyCode.CAPS ||event.getCode() == KeyCode.PERIOD||event.getCode().isLetterKey() ||event.getCode().isKeypadKey()||event.getCode() == (KeyCode.NUM_LOCK)||event.getCode() == (KeyCode.SLASH) ||event.getCode() == KeyCode.TAB || event.getCode() == KeyCode.SPACE)
                {} 
            else {
                Error("BOOK AUTHOR can only be alphabet.");
                clear(1);
            }
        });
        
        dpdate.setOnKeyPressed( event-> {
            if(event.getCode().isDigitKey() ||event.getCode().isKeypadKey()||event.getCode() == (KeyCode.LEFT)  ||event.getCode() == (KeyCode.RIGHT) ||event.getCode() == (KeyCode.ENTER) ||event.getCode() == (KeyCode.BACK_SPACE) ||event.getCode() == (KeyCode.NUM_LOCK)||event.getCode() == KeyCode.DIVIDE ||event.getCode() == (KeyCode.SLASH)||event.getCode() == KeyCode.TAB) 
            {}else {
            Error("DATE can only contain number and '/'.");
            clear(3);
            }
        });
        
        
        tfPub.addEventFilter(KeyEvent.KEY_PRESSED, event -> {
            if(event.getCode() == KeyCode.ENTER ||event.getCode() == KeyCode.BACK_SPACE ||event.getCode() == KeyCode.LEFT ||event.getCode() == KeyCode.RIGHT ||event.getCode() == KeyCode.TAB ||event.getCode() == KeyCode.SHIFT ||event.getCode() == KeyCode.CAPS ||event.getCode().isLetterKey()||event.getCode() == (KeyCode.SPACE)||event.getCode() == KeyCode.TAB)
                {} 
            else {
                Error("Publisher can only be alphabet.");
                clear(5);
            }
        });
        
        
        //--------------------EVENT LISTENER--------------------
        cbType.getSelectionModel().selectedItemProperty().addListener((options, oldValue, newValue) -> {
            if(newValue=="Classic"){
                GenerateId(1);
            }else if(newValue=="Fantasy"){
                GenerateId(2);
            }else if(newValue=="Comic"){
                GenerateId(3);
            }else if(newValue=="Novel"){
                GenerateId(4);
            }else if(newValue=="Horror"){
                GenerateId(5);
            }
        }); 
        
        
    }
    
    
    //------------------------------------------------------------------
    //----------------------------U.D Method----------------------------
    //------------------------------------------------------------------
    public boolean login(String checkUser, String checkPw){
        boolean correct=false;
        if(memList.checkLogin(checkUser, checkPw)==true) {
                Info("Login","Login successful!");
                clear(8);
                correct=true;
            }else{
                Error("Wrong username / password !");
                clear(8);
            }
        return correct;
    }
    
    public void create(){
        tidCreate.showAndWait();
        if(tfcName.getText().isEmpty()){
            Error("Empty input in User Name");return;
        }else if(pfCpw.getText().trim().isEmpty()){
            Error("Empty input in Password");return;
        }
        String userName = tfcName.getText();
        String userPw = pfCpw.getText();
        memList.addMember(new Member(userName,userPw));
        clear(9);
    }
    
    public void add(){      
        try{
            if(tfID.getText().trim().isEmpty()){
                Error("ID cannot be empty.\nPlease select a book type first");
                tfID.requestFocus();return;
            }else if(tfTitle.getText().trim().isEmpty()){
                Error("Book Title cannot be empty");
                tfTitle.requestFocus();return;
            }else if(tfAuthor.getText().trim().isEmpty()){
                Error("Book Author cannot be empty");
                tfAuthor.requestFocus();return;
            }else if(cbType.getSelectionModel().isEmpty()){
                Error("Book type cannot be empty");
                cbType.requestFocus();return;
            }else if(tfPub.getText().trim().isEmpty()){
                Error("Publisher cannot be empty");
                tfPub.requestFocus();return;
            }else if(dpdate.getEditor().getText().isEmpty()){
                Error("Published Date cannot be empty");
                dpdate.requestFocus();return;
            }

            String bookID = tfID.getText(),
                   bookTitle = tfTitle.getText(),
                   bookAuthor = tfAuthor.getText(),
                   bookType = (String)cbType.getValue(),
                   publisher = tfPub.getText();
            //DATE
            LocalDate datePublished = dpdate.getValue();
            bookList.add(new Book(bookID,bookTitle,bookAuthor,
                     bookType,publisher,datePublished));
            bookList.writeLibrary();
            writeFile(bookID,bookTitle,bookAuthor,bookType,publisher,datePublished);
            tableSelect(bookID,1);
            clear(0);
        }catch(Exception e){
            Error("Some error occurred!");
        }
    }
    
    public void remove(){
        try{
            int option =1; //1=id,2=title
            String bookInfo="";
            if(tfrsID.getText().isEmpty()&&tfrsTitle.getText().isEmpty()){
                Error("No empty value!");return;
            }
            if(rbID.isSelected()){bookInfo = tfrsID.getText();option=1;}
            if(rbTitle.isSelected()){bookInfo=tfrsTitle.getText();option=2;}
            
            bookList.remove(bookInfo,option);
            bookList.writeLibrary();
            print();
            clear(2);
        clear(0);
        }catch(NumberFormatException nfe){
            Error("BookID or BookTitle cannot be empty.");
        }
        
    }
    
    public void search(){
        try{
             int option =1;
             String bookInfo="";
             
            if(rbID.isSelected()){        
                if(tfrsID.getText().isEmpty()){
                Error("No empty value!");return;
            }
                bookInfo = tfrsID.getText();
                option=1;
            }
            
            if(rbTitle.isSelected()){
                if(tfrsTitle.getText().isEmpty()){
                Error("No empty value!");return;
            }
                bookInfo=tfrsTitle.getText();
                option=2;
            }
            bookList.search(bookInfo,option);
            tableSelect(bookInfo,2);
            clear(2);
        }catch(NumberFormatException e){
            Error("BookID or BookTitle cannot be empty.");
        }
    }
    
    public void update(){
        try{
            if(tfID.getText().trim().isEmpty()){
                Error("ID cannot be empty.\nPlease select a book type first");
                tfID.requestFocus();return;
            }else if(tfTitle.getText().trim().isEmpty()){
                Error("Book Title cannot be empty");
                tfTitle.requestFocus();return;
            }else if(tfAuthor.getText().trim().isEmpty()){
                Error("Book Author cannot be empty");
                tfAuthor.requestFocus();return;
            }else if(cbType.getSelectionModel().isEmpty()){
                Error("Book type cannot be empty");
                cbType.requestFocus();return;
            }else if(tfPub.getText().trim().isEmpty()){
                Error("Publisher cannot be empty");
                tfPub.requestFocus();return;
            }else if(dpdate.getEditor().getText().isEmpty()){
                Error("Published Date cannot be empty");
                dpdate.requestFocus();return;
            }
            
            String bookID = tfID.getText(),
                   bookTitle = tfTitle.getText(),
                   bookAuthor = tfAuthor.getText(),
                   bookType = (String)cbType.getValue(),
                   publisher = tfPub.getText();
            //DATE
            LocalDate datePublished = dpdate.getValue();

            bookList.update(bookID,bookTitle,bookAuthor,bookType,publisher,datePublished);
            bookList.writeLibrary();
            tableSelect(bookID,1);
            clear(0);
            writeFile(bookID,bookTitle,bookAuthor,bookType,publisher,datePublished);
        }catch(Exception e){
            Error("Some error occurs");
        }
    }
    
    public void print(){
        table.setItems(bookList.setTableContent());
        table.refresh();
        table1.setItems(bookList.setTableContent());
        table1.refresh();
    }
    
    public void GenerateId(int option){
        boolean found=false;
        int i=1;
        String id="XXX-000";
        while(found==false){
            String idnum=String.format("%03d", i);
            switch(option){
                case 1:id = "CLA-"+idnum;break;
                case 2:id = "FAN-"+idnum;break;
                case 3:id = "COM-"+idnum;break;
                case 4:id = "NOV-"+idnum;break;
                case 5:id = "HOR-"+idnum;break;
            }
            found = !bookList.bookExist(id,1);
            i++;
        }
        if(found==true){
            tfID.setText(id);
        }
    }
    
    public void tableSelect(String bookInfo, int option){
        print();
        int row;
        TableColumn col1 = table1.getColumns().get(0);
        TableColumn col2 = table1.getColumns().get(1);
        String data="";
        switch(option){
            case 1: for(row=0; row<table1.getItems().size(); row++){
                        data = (String) col1.getCellObservableValue(row).getValue();
                        if(bookInfo.equalsIgnoreCase(data)){
                            table.getSelectionModel().select(row);
                            table1.getSelectionModel().select(row);
                            break;
                        }else{
                            table.getSelectionModel().clearSelection();
                            table1.getSelectionModel().clearSelection();
                        }
                    }break;
            case 2: for(row=0; row<table1.getItems().size(); row++){
                        if(rbID.isSelected()){
                            data = (String) col1.getCellObservableValue(row).getValue();
                        }else if(rbTitle.isSelected()){
                            data = (String) col2.getCellObservableValue(row).getValue();
                        }
                        if(bookInfo.equalsIgnoreCase(data)){
                            table.getSelectionModel().select(row);
                            table1.getSelectionModel().select(row);
                            break;
                        }else{
                            table.getSelectionModel().clearSelection();
                            table1.getSelectionModel().clearSelection();
                        }
                    }break;
        }
    }
    
    public void writeFile(String bookID,String bookTitle,String bookAuthor, String bookType,String publisher,LocalDate datePublished){
        try{
            PrintWriter output = new PrintWriter(bookID+".txt");
            output.println("*****"+ bookTitle +"'s book details*****"+
                "\nBook ID       : " + bookID + 
                "\nBook Title    : " + bookTitle+ 
                "\nBook Author   : " + bookAuthor+
                "\nBook Type     : " + bookType+
                "\nPublisher     : " + publisher+
                "\nDate Published: " + datePublished 
            );
            output.close();
        }catch(FileNotFoundException fnfe){
            Error("File Not Found!");
        }
    }
    
    //--------------------BASIC FUNCTION--------------------
    public void clear(int opt){
        switch(opt){
            case 0 :tfID.clear();
                    tfTitle.clear();
                    tfAuthor.clear();
                    cbType.getSelectionModel().clearSelection();
                    tfPub.clear();
                    dpdate.getEditor().clear();
                    tfID.requestFocus();break;
            case 1: tfAuthor.clear();tfAuthor.requestFocus();
                    if(rbID.isSelected()){tfrsID.requestFocus();}else{tfrsTitle.requestFocus();}break;
            case 2: tfrsID.clear();tfrsTitle.clear();break;
            case 3: dpdate.getEditor().clear();dpdate.requestFocus();break;
            case 4: tfID.clear();tfID.requestFocus();break;
            case 5: tfPub.clear();tfPub.requestFocus();break;
            case 6: tfrsID.clear();tfrsID.requestFocus();break;
            case 7: tfrsTitle.clear();tfrsTitle.requestFocus();break;
            case 8: tfName.clear();pfpw.clear();tfName.requestFocus();break;
            case 9: tfcName.clear();pfCpw.clear();tfcName.requestFocus();break;
        }
    }
    
    public void Error(String e) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(e);
        alert.show();
    }
    
    public void Info(String i, String im){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(i);
        alert.setHeaderText(null);
        alert.setContentText(im);
        alert.show();
    }
    
    public static void main(String[] args) {
        launch(args);
    }
    
}
