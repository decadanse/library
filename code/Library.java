package jmp;

import java.util.ArrayList;
import java.util.List;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Library extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {

//                testbooks.add(new Book("ff", "fd", "ff", "ff"));
//                for (Book b : testbooks) {
//                    System.out.println(b.getId());
//                    if (b.isAvailable()) ;
//                    {
//                      System.out.println("helo");
//                    }
//                } 
                                
                users.addStudent(new Student("id = 1 is reserved as", " Reading Hall id", "'Student'"));
            
		userBox.getItems().addAll();
                bookBox.getItems().addAll();             
               		
		availableFilterBox.getItems().addAll(
				"no matter", //all
				"not yet used", //available==true
				"used for exact project" //available==false
				);

		root.getChildren().add(strings);
		
		strings.setPadding(new Insets(10, 30, 10, 30));
		strings.setSpacing(20);
		
		strings.getChildren().add(new Text("Select the project"));
		strings.getChildren().add(buttonBox);
		strings.getChildren().add(new Text("Select the info resourse"));
		strings.getChildren().add(buttonBookBox);
                strings.getChildren().add(buttonReturnBookBox);                
		strings.getChildren().add(new Text("Add new project"));
		strings.getChildren().add(addUserBox);
                strings.getChildren().add(new Text("Add new info resourse"));
		strings.getChildren().add(addBookBox);
                strings.getChildren().add(deleteBookBox);
		strings.getChildren().add(filters);
		strings.getChildren().add(resultFilter);

		buttonBox.setSpacing(10);
		buttonBox.getChildren().add(userBox);
		buttonBox.getChildren().add(buttonGetStudentInfo);
		buttonBox.getChildren().add(textStudentInfo);
		
		buttonBookBox.setSpacing(10);
		buttonBookBox.getChildren().add(bookBox);
		buttonBookBox.getChildren().add(buttonGetBookInfo);
		buttonBookBox.getChildren().add(textBookInfo);
                buttonBookBox.getChildren().add(buttonTakeBookAsStudent);
                buttonBookBox.getChildren().add(buttonTakeBookToReadingHall);
                
                buttonReturnBookBox.setSpacing(10);
                buttonReturnBookBox.getChildren().add(buttonReturnBookAsStudent);
                buttonReturnBookBox.getChildren().add(buttonReturnBookFromReadingHall);
                buttonReturnBookBox.getChildren().add(actionBookStatusInfo);
                                                
		addUserBox.setSpacing(10);
		addUserBox.getChildren().add(new Text("Name: "));
		addUserBox.getChildren().add(name);
		addUserBox.getChildren().add(new Text("Theme: "));
		addUserBox.getChildren().add(middleName);
		addUserBox.getChildren().add(new Text("Purpose of creation: "));
		addUserBox.getChildren().add(surname);
		addUserBox.getChildren().add(buttonAddUser);
                
                addBookBox.setSpacing(10);
		addBookBox.getChildren().add(new Text("Title: "));
		addBookBox.getChildren().add(title);
		addBookBox.getChildren().add(new Text("Authors: "));
		addBookBox.getChildren().add(author);
		addBookBox.getChildren().add(new Text("Link: "));
		addBookBox.getChildren().add(genre);
		addBookBox.getChildren().add(new Text("Year Of Publication: "));
		addBookBox.getChildren().add(yearOfPublication);
		addBookBox.getChildren().add(buttonAddBook);               
		
                deleteBookBox.setSpacing(10);
		deleteBookBox.getChildren().add(buttonDeleteBook); 
		
		filters.setSpacing(10);
		filters.getChildren().add(new Text("Actual"));
		filters.getChildren().add(availableFilterBox);
		filters.getChildren().add(filter);
		

		Scene scene = new Scene(root, WIDTH, HEIGHT);
		primaryStage.setTitle("LIBRARY");
		primaryStage.setScene(scene);
		primaryStage.show();

		buttonGetStudentInfo.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				Student u = (Student) userBox.getSelectionModel().getSelectedItem();
				if (u != null) {
					textStudentInfo.setText("Project info: " + u.toStringForLog());
				} else {
					textStudentInfo.setText("Project not selected");
				}
			}
		}); 
                
                buttonGetBookInfo.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				Book b = (Book) bookBox.getSelectionModel().getSelectedItem();
				if (b != null) {
					textBookInfo.setText("Info resourse info: " + b.toStringForLog());
				} else {
					textBookInfo.setText("Info resourse not selected");
				}
			}
		});
                
                buttonTakeBookToReadingHall.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				Book b = (Book) bookBox.getSelectionModel().getSelectedItem(); 
                                String infoText;
                                if (b != null) {
                                    infoText = books.giveBook(b);
                                    actionBookStatusInfo.setText(infoText);                                    
				} else {
                                    textBookInfo.setText("Info resourse not selected");
				}
			}
		});
                
                buttonTakeBookAsStudent.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				Book b = (Book) bookBox.getSelectionModel().getSelectedItem();
                                Student u = (Student) userBox.getSelectionModel().getSelectedItem();
                                String infoText;
                                if (b != null && u!= null) {
                                    infoText = books.giveBook(b, u); 
                                    actionBookStatusInfo.setText(infoText);
				} else {
                                    textBookInfo.setText("Info resourse not selected");
				}
			}
		});
                
                buttonReturnBookFromReadingHall.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				Book b = (Book) bookBox.getSelectionModel().getSelectedItem();    
                                String infoText;
                                if (b != null) {
                                    infoText = books.takeBook(b);
                                    actionBookStatusInfo.setText(infoText);
				} else {
                                    textBookInfo.setText("Info resourse not selected");
				}
			}
		});
                
                buttonReturnBookAsStudent.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				Book b = (Book) bookBox.getSelectionModel().getSelectedItem();
                                Student u = (Student) userBox.getSelectionModel().getSelectedItem();
                                String infoText;
                                if (b != null && u!= null) {
                                    infoText = books.takeBook(b, u);
                                    actionBookStatusInfo.setText(infoText);
				} else {
                                    textBookInfo.setText("Info resourse not selected");
				}
			}
		});

//    public void loadbooks() {
//        String tokens[] = null;
//        String title, genre, author, yearOfPublication;
//        boolean availability;
//        try
//        {
//            FileReader fr = new FileReader("books.txt");
//            BufferedReader br = new BufferedReader(fr);
//            String line = br.readLine();
//            while(line != null)
//            {
//                tokens = line.split(",");
//                title = tokens[0];
//                author = tokens[1];
//                genre = tokens[2];
//                yearOfPublication = tokens[3];
//                availability = Boolean.parseBoolean(tokens[4]);
//                Book b = new Book(title, author, genre, yearOfPublication, availability);
//                catalog.add(b);
//                line = br.readLine();
//            }
//            br.close();
//            fr.close();
//        }
//        catch(IOException ioEx)
//        {
//            System.out.println(ioEx);
//        }
//    }
//    
//    //save books to file
//    public void savebooks() {
//        try
//        {
//            Book b;
//
//            String line;
//            FileWriter fw = new FileWriter("books.txt");
//            PrintWriter pw = new PrintWriter(fw);
//            for(int i = 0; i < catalog.size(); i++)
//            {
//                b = (Book) catalog.get(i);
//                line = b.getTitle() + "," + b.getAuthor() + "," + b.getGenre()
//                        + "," + b.getYearOfPublication()+ "," + b.isAvailable();
//                // writes line to file (books.txt)
//                pw.println(line);
//            }
//            pw.flush();
//            pw.close();
//            fw.close();
//        }
//        catch(IOException ioEx)
//        {
//            System.out.println(ioEx);
//        }
//    }
		
		buttonAddUser.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
			    Student u = new Student(name.getText(), middleName.getText(), surname.getText());
                            //check if not all fields filled
                            if (studentValidator.checkAllTextField(u)) {                                
                                users.addStudent(u);
				userBox.getItems().addAll(u);
                            }
			}
		});
                buttonAddBook.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
                            Book b = new Book(title.getText(), author.getText(), genre.getText(), yearOfPublication.getText());
                            //check if not all fields filled
                            if (bookValidator.checkAllTextField(b)) {
                                books.addBookToCatalog(b);
                                bookBox.getItems().addAll(b);
                            }                            
			}
		});
                buttonDeleteBook.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
                            Book b = (Book) bookBox.getSelectionModel().getSelectedItem();
                            String infoText;
                            if (b != null) {
                                infoText = b.toStringForLog();
                                books.deleteBook(b);
                                bookBox.getItems().remove(b);                             
                                actionBookStatusInfo.setText(infoText+ " was successfully delited");
                            }else{   
                                textBookInfo.setText("Info resourse not selected");
                            }
			}
		});
		
		filter.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
                                
				int ava; //available
				int index = availableFilterBox.getSelectionModel().getSelectedIndex();
                                ava = (index == 0)? 0: (index == 1)? 1: (index == 2)? 2: 3;
				
				System.out.println("ava is "+ava);                   		
				List<Book> list = new ArrayList<>( );                           
				
				if(index != 3){  
                                    if (ava==0){
                                        list = books.getAllBooks();
                                    }else if(ava==1){
                                        list = books.getAvailableBooks();
                                    }else{
                                        list = books.getNotAvailableBooks();
                                    }
                                    
                                    String res = "";

                                    for(Book b: list) {
					res += "Title: "+b.getTitle().toString() + "   Catalog Identificator: "+b+"\n";   
                                    }
                                    System.out.println(res);
				
                                    resultFilter.setText(res);         
                                }	
			}
		});                               

	}

	public static void main(String[] args) {
            launch(args);
	}

	Group root = new Group();
	
	VBox strings = new VBox();
	
	HBox buttonBox = new HBox();
        HBox buttonBookBox = new HBox();
        HBox buttonReturnBookBox = new HBox();
	HBox changeSalaryBox = new HBox();
	HBox addUserBox = new HBox();
        HBox addBookBox = new HBox();
        HBox deleteBookBox = new HBox();
	HBox filters = new HBox();
                       
	ComboBox<Student> userBox = new ComboBox<>();
        ComboBox<Book> bookBox = new ComboBox<>();
	ComboBox<String> availableFilterBox = new ComboBox<>();
	ComboBox<String> genreFilterBox = new ComboBox<>();


	final private int WIDTH = 1000;
	final private int HEIGHT = 600;

	private StudentService users = new StudentService();
        private BookService books = new BookService();
        
        // testbooks - test array
            ArrayList<Book> testbooks = new ArrayList<>();//for testing
        // testbooks - test array
        
        BookValidator bookValidator = new BookValidator();
        StudentValidator studentValidator = new StudentValidator();
        
	Button buttonGetStudentInfo = new Button("Project Info");
        Button buttonGetBookInfo = new Button("Info resourse Info");
        
	Text textStudentInfo = new Text();
        Text textBookInfo = new Text();
        Text actionBookStatusInfo = new Text();
	
	Button buttonChangeSalary = new Button("Change salary");
	TextField howMuchChange = new TextField();
	
	Button buttonAddUser = new Button("Add project");
	TextField name = new TextField();
	TextField middleName = new TextField();
	TextField surname = new TextField();
        
        Button buttonAddBook = new Button("Add info resourse");
	TextField title = new TextField();
	TextField author = new TextField();
	TextField genre = new TextField();
	TextField yearOfPublication = new TextField();
        
        Button buttonDeleteBook = new Button("Delete info resourse");   
        
        Button buttonTakeBookToReadingHall = new Button("Mark info resourse as used");
        Button buttonTakeBookAsStudent = new Button("Mark info resourse as belonging to the project");
        Button buttonReturnBookAsStudent = new Button("Unmark info resourse as belonging to the project");
        Button buttonReturnBookFromReadingHall = new Button("Unmark Info resourse as used");
	
	Button filter = new Button("filter");
	Text resultFilter = new Text();       

}
