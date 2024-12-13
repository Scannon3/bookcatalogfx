package org.example;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.collections.FXCollections;


import java.io.IOException;

public class Controller {
    public VBox screen;
    private Stage stage;
    private Scene scene;
    private FXMLLoader loader;
    private BookCatalog books = BookCatalog.getInstance(); //uses singleton so books always has the up to date catalog

/*corresponds to the fxid of the controls in the scene3(displaybooks) fxml file*/
    @FXML
    private TableView<Book> bookTableView;
    @FXML
    private TableColumn<Book, String> titleColumn;
    @FXML
    private TableColumn<Book, String> authorColumn;
    @FXML
    private TableColumn<Book, String> isbnColumn;
    @FXML
    private TableColumn<Book, String> detailsColumn;


/*coresponds to the fxid of the controls in scene 2(addbook) fxml file*/
    @FXML
    private TextField printTitleField, printAuthorField, printIsbnField, printPagesField,
            audioTitleField, audioAuthorField, audioIsbnField, audioDurationField,
            ebookTitleField, ebookAuthorField, ebookIsbnField, ebookSizeField;
/*scene one is the main scene*/
    public void switchScene1(ActionEvent event) throws IOException {
        loader = new FXMLLoader(getClass().getResource("/org/example/mainMenu.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(loader.load());
        stage.setScene(scene);
        stage.show();
    }
/*scene two is the addbook scene*/
    public void switchScene2(ActionEvent event) throws IOException {
        loader = new FXMLLoader(getClass().getResource("/org/example/addBook.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(loader.load());
        stage.setScene(scene);
        stage.show();
    }
    /*scene three is the display books scene*/
    public void switchScene3(ActionEvent event) throws IOException {
        loader = new FXMLLoader(getClass().getResource("/org/example/displayBook.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(loader.load());
        Controller controller = loader.getController();
        controller.initializeBookTable();
        stage.setScene(scene);
        stage.show();

    }
    @FXML
    private RadioButton optAudioBook;
    @FXML
    private RadioButton optEBook;
    @FXML
    private RadioButton optPrintBook;


    public void switchaddBook_choice(ActionEvent event) throws IOException {
        loader = new FXMLLoader(getClass().getResource("/org/example/addBook_choice.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(loader.load());
        scene.getStylesheets().add(getClass().getResource("/org/example/addBook_choice.css").toExternalForm());
        System.out.println(getClass().getResource("/org/example/addBook_choice.css"));
        // Get controller after loading FXML
        Controller controller = loader.getController();

        // Initialize ToggleGroup and other logic in the controller
        controller.initialize_addBook_choice();

        stage.setScene(scene);
        stage.show();
    }




    public void initialize_addBook_choice() {



        ToggleGroup bookChoiceGroup = new ToggleGroup();

        optAudioBook.setToggleGroup(bookChoiceGroup);
        optEBook.setToggleGroup(bookChoiceGroup);
        optPrintBook.setToggleGroup(bookChoiceGroup);

        optEBook.setSelected(true);

    }
/*these function corresponds to the button to add a printbook/audiobook/ebook based on the input fields
* the books are added to the static instance(singleton) in the bookcatalog class because the controller class is reinitialized
* whenever scenes change*/


    public int addBook_choice(ActionEvent event) {

        if(optAudioBook.isSelected()) {
            return 1;
        }
        if(optEBook.isSelected()) {
            return 2;
        }
        if(optPrintBook.isSelected()) {
            return 3;
        }

        return 0;

    }

    public void addPrintBook(ActionEvent event) {
        try {

            String title = printTitleField.getText();
            if (title == null || title.trim().isEmpty()) {
                throw new IllegalArgumentException("Title cannot be empty");
            }

            String author = printAuthorField.getText();
            if (author == null || author.trim().isEmpty()) {
                throw new IllegalArgumentException("Author cannot be empty");
            }

            String isbn = printIsbnField.getText();
            if (isbn == null || isbn.trim().isEmpty()) {
                throw new IllegalArgumentException("ISBN cannot be empty");
            }

            String pagesText = printPagesField.getText();
            int pages;
            try {
                pages = Integer.parseInt(pagesText);
                if (pages <= 0) {
                    throw new IllegalArgumentException("Pages must be a positive integer");
                }
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("Pages must be a valid integer", e);
            }


            books.addBook(new PrintBook(title, author, isbn, pages));
            System.out.println(books.getBooks().size());



            printTitleField.clear();
            printAuthorField.clear();
            printIsbnField.clear();
            printPagesField.clear();
        } catch (IllegalArgumentException e) {

            System.err.println("Error adding book: " + e.getMessage());
        }
    }
    public void addAudioBook(ActionEvent event) {
        try {

            String title = audioTitleField.getText();
            if (title == null || title.trim().isEmpty()) {
                throw new IllegalArgumentException("Title cannot be empty");
            }

            String author = audioAuthorField.getText();
            if (author == null || author.trim().isEmpty()) {
                throw new IllegalArgumentException("Author cannot be empty");
            }

            String isbn = audioIsbnField.getText();
            if (isbn == null || isbn.trim().isEmpty()) {
                throw new IllegalArgumentException("ISBN cannot be empty");
            }

            String durationText = audioDurationField.getText();
            int duration;
            try {
                duration = Integer.parseInt(durationText);
                if (duration <= 0) {
                    throw new IllegalArgumentException("Duration must be a positive integer");
                }
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("Duration must be a valid integer", e);
            }


            books.addBook(new AudioBook(title, author, isbn, duration));


            audioTitleField.clear();
            audioAuthorField.clear();
            audioIsbnField.clear();
            audioDurationField.clear();
        } catch (IllegalArgumentException e) {

            System.err.println("Error adding book: " + e.getMessage());
        }
    }
    public void addEBook(ActionEvent event) {
        try {

            String title = ebookTitleField.getText();
            if (title == null || title.trim().isEmpty()) {
                throw new IllegalArgumentException("Title cannot be empty");
            }

            String author = ebookAuthorField.getText();
            if (author == null || author.trim().isEmpty()) {
                throw new IllegalArgumentException("Author cannot be empty");
            }

            String isbn = ebookIsbnField.getText();
            if (isbn == null || isbn.trim().isEmpty()) {
                throw new IllegalArgumentException("ISBN cannot be empty");
            }

            String sizeText = ebookSizeField.getText();
            int size;
            try {
                size = Integer.parseInt(sizeText);
                if (size <= 0) {
                    throw new IllegalArgumentException("Duration must be a positive integer");
                }
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("Duration must be a valid integer", e);
            }


            books.addBook(new EBook(title, author, isbn, size));


            ebookTitleField.clear();
            ebookAuthorField.clear();
            ebookIsbnField.clear();
            ebookSizeField.clear();
        } catch (IllegalArgumentException e) {

            System.err.println("Error adding book: " + e.getMessage());
        }
    }
    /*this assigns column names to the table/column through the fxid */
    public void initializeBookTable() {

        titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        authorColumn.setCellValueFactory(new PropertyValueFactory<>("author"));
        isbnColumn.setCellValueFactory(new PropertyValueFactory<>("isbn"));
        detailsColumn.setCellValueFactory(new PropertyValueFactory<>("details"));

        /*this tells the table what to display using the list returned by getbooks()*/
        bookTableView.setItems(FXCollections.observableArrayList(books.getBooks()));
    }




}
