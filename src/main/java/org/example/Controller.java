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

import java.io.IOException;

public class Controller {
    private Stage stage;
    private Scene scene;
    private FXMLLoader loader;
    private BookCatalog books = BookCatalog.getInstance();


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



    @FXML
    private TextField printTitleField, printAuthorField, printIsbnField, printPagesField,
            audioTitleField, audioAuthorField, audioIsbnField, audioDurationField,
            ebookTitleField, ebookAuthorField, ebookIsbnField, ebookSizeField;

    public void switchScene1(ActionEvent event) throws IOException {
        loader = new FXMLLoader(getClass().getResource("/org/example/scene1.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(loader.load());
        stage.setScene(scene);
        stage.show();
    }

    public void switchScene2(ActionEvent event) throws IOException {
        loader = new FXMLLoader(getClass().getResource("/org/example/scene2.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(loader.load());
        stage.setScene(scene);
        stage.show();
    }
    public void switchScene3(ActionEvent event) throws IOException {
        loader = new FXMLLoader(getClass().getResource("/org/example/scene3.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(loader.load());
        Controller controller = loader.getController();
        controller.initializeBookTable();
        stage.setScene(scene);
        stage.show();

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
    public void initializeBookTable() {
        // Bind columns to Book properties
        titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        authorColumn.setCellValueFactory(new PropertyValueFactory<>("author"));
        isbnColumn.setCellValueFactory(new PropertyValueFactory<>("isbn"));
        detailsColumn.setCellValueFactory(new PropertyValueFactory<>("details"));

        // Populate TableView with books from the catalog
        bookTableView.setItems(FXCollections.observableArrayList(books.getBooks()));
    }

    public void displayBooksTest() {
        books.addBook(new PrintBook("222","2222","2222",2222));

        System.out.println(books.getBooks().size());
    }


}