package org.example;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.collections.FXCollections;
import javafx.scene.control.TableView;
import javafx.scene.control.Label;
import javax.swing.*;
import java.io.IOException;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;

public class Controller {
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


/*coresponds to the fxid of the controls in (addbook) fxml file*/
    @FXML
    private TextField printTitleField, printAuthorField, printIsbnField, printPagesField,
            audioTitleField, audioAuthorField, audioIsbnField, audioDurationField,
            ebookTitleField, ebookAuthorField, ebookIsbnField, ebookSizeField;

    @FXML
    private TextField removeField;  // for the removebook text field

    /*corresponds to the bubble selectors for book type*/
    @FXML
    private TextField searchTitle; //for the search title field



/*scene one is the main scene*/

    public void switchScene1(ActionEvent event) throws IOException {
        loader = new FXMLLoader(getClass().getResource("/org/example/scene1.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(loader.load());
        Controller controller = loader.getController();
        controller.initializeBookTable();
        stage.setScene(scene);
        stage.show();

    }


/*scene two is the addbook scene*/
    public void addBook_Choice(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/addBook_Choice.fxml"));

        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(loader.load());
        stage.setScene(scene);
        stage.show();
    }


    public void addAudioScene(ActionEvent event) throws IOException {
        loader = new FXMLLoader(getClass().getResource("/org/example/addAudioScene.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(loader.load());
        stage.setScene(scene);
        stage.show();

    }
    public void addEBookScene(ActionEvent event) throws IOException {
        loader = new FXMLLoader(getClass().getResource("/org/example/addEBookScene.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(loader.load());
        stage.setScene(scene);
        stage.show();
    }
    public void addPrintScene(ActionEvent event) throws IOException {
        loader = new FXMLLoader(getClass().getResource("/org/example/addPrintScene.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(loader.load());
        stage.setScene(scene);
        stage.show();
    }

/*these function corresponds to the button to add a printbook/audiobook/ebook based on the input fields
* the books are added to the static instance(singleton) in the bookcatalog class because the controller class is reinitialized
* whenever scenes change*/
    public void addPrintBook(ActionEvent event) throws IOException {
        boolean added;
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

            added = books.addBook(new PrintBook(title, author, isbn, pages));
            if (!added) {
             System.out.println("ISBN already exists inside book catalog");
            }




            printTitleField.clear();
            printAuthorField.clear();
            printIsbnField.clear();
            printPagesField.clear();
        } catch (IllegalArgumentException e) {

            System.err.println("Error adding book: " + e.getMessage());
        }
        switchScene1(event);
    }
    public void addAudioBook(ActionEvent event) throws IOException {
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
        switchScene1(event);
    }
    public void addEBook(ActionEvent event) throws IOException {
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
        switchScene1(event);
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
    /*removes book based on isbn in textfield*/
    public void RemoveBook(ActionEvent event) throws IOException {
        String isbn = removeField.getText();
        books.removeBook(isbn);
        switchScene1(event);
    }
    /*searches and displays books based on titles that match the string sequence*/
    public void searchBooks(ActionEvent event) {
        String searchQuery = searchTitle.getText().toLowerCase();

        if (searchQuery == null || searchQuery.trim().isEmpty()) {
            bookTableView.setItems(FXCollections.observableArrayList(books.getBooks())); //if empty display all books
            return;
        }

        var filteredBooks = books.getBooks().stream()
                .filter(book -> book.getTitle().toLowerCase().contains(searchQuery))      //create list of books that match searchquery
                .toList();


        bookTableView.setItems(FXCollections.observableArrayList(filteredBooks));         //display filtered list
    }




}
