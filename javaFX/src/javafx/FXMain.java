/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafx;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.Window;

/**
 *
 * @author abdo1438
 */
public class FXMain extends Application {

    Scene scene1, scene;
    Stage window;
    Image image;
    static FileInputStream input;

    static InputStreamReader sr;

    static BufferedReader br;

    static Scanner scan = new Scanner(System.in);

    File[] inputFiles = null;

    final File directory = new File("C:\\Users\\abdo1438\\Documents\\security_computer\\EN_EVENTS\\");
    int o;
    String line;
    TextArea textArea;

    @Override
    public void start(final Stage primaryStage) {
        /*
         */
        window = primaryStage;

        image = new Image(("file:L20YLOGO.png"));
        Image image_search = new Image(("file:search.png"));

        Button Ar_butt = new Button("AR");

        Ar_butt.setTranslateX(430);
        Ar_butt.setTranslateY(0);
        Ar_butt.setPrefWidth(70);
        Ar_butt.setStyle("-fx-border-color: #734d0f; -fx-border-width: 2px;-fx-text-fill: #151514");

        Ar_butt.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent arg0) {

                try {

                    FXMLLoader pane = new FXMLLoader(getClass().getResource("second_FXML.fxml"));

                    Parent root1 = (Parent) pane.load();

                    Stage stage = new Stage();

                    stage.setTitle("hhh");

                    stage.setScene(new Scene(root1));

                    stage.show();

                } catch (Exception e) {
                    System.out.println("Cannot find the file");

                }
            }

        });

        Label lb = new Label("ENTER THE YEAR FROM 2000 TO UP ", new ImageView(image_search));
        TextField td = new TextField();
        TextField text_error = new TextField();

        text_error.setTranslateX(50);
        text_error.setTranslateY(350);
        text_error.setFocusTraversable(false);
        text_error.setPrefWidth(400);
        text_error.setStyle("-fx-text-inner-color: red;");
        text_error.setDisable(true);
        Button butt = new Button("Search");

        butt.setStyle("-fx-border-color: #734d0f; -fx-border-width: 2px;-fx-text-fill: #151514");
        butt.setTranslateX(130);
        butt.setTranslateY(300);
        butt.setFocusTraversable(false);
        butt.setPrefWidth(250);
        butt.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent arg0) {

                String year = td.getText();

                int year2 = Integer.parseInt(year);

                if (year2 >= 2000) {
                    text_error.setText(" ");
                    text_error.setDisable(true);

                    String name_file = ("\\") + year.concat(".txt");

                    try {
                        input = new FileInputStream(directory + name_file);
                        sr = new InputStreamReader(input, Charset.forName("UTF-8"));

                        br = new BufferedReader(sr);
                        // if the year greater than 2000 then search the file list 
                    } catch (FileNotFoundException ex) {
                        Logger.getLogger(FXMain.class.getName()).log(Level.SEVERE, null, ex);

                    }
                    inputFiles = directory.listFiles();

                    for (int i = 0; i < inputFiles.length; i++) {
                        try {

                            if (inputFiles[i].isFile() && inputFiles[i].getName().contains(year)) {

                                while ((line = br.readLine()) != null) {

                                    window.setScene(scene1);
                                    textArea.appendText(line);

                                }

                                System.out.println(" ");

                            }

                        } catch (Exception e) {

                            System.out.println(e.getMessage());
                        }

                    }

                } else {
                    text_error.setDisable(false);
                    text_error.setText("The YEAR EVENT SHOULD START FROM 2000 TO UP !! TRY AGAIN ");

                }

            }

        });

        td.setTranslateX(110);
        td.setTranslateY(250);
        td.setFocusTraversable(false);
        td.setPrefWidth(300);

        ImageView imv = new ImageView();
        imv.setImage(image);

        imv.setLayoutX(200);
        imv.setLayoutY(40);
        imv.setFitHeight(100);
        imv.setFitWidth(100);

        lb.setContentDisplay(ContentDisplay.RIGHT);
        lb.setLayoutX(100);
        lb.setLayoutY(200);
        lb.setFont(new Font("Gill Sans", 20));
        lb.setTextFill(Color.BLACK);
        Group root = new Group();
        root.getChildren().addAll(lb, imv, td, butt, text_error);
        scene = new Scene(root, 500, 400, Color.WHITESMOKE);

        primaryStage.getIcons().add(new Image("file:L20YLOGO.png"));
        primaryStage.setScene(scene);
        primaryStage.setTitle("L20Y-Events");
        primaryStage.show();

        // second page 
        Image image1 = new Image(("file:L20YLOGO.png"));

        ImageView img = new ImageView(image1);

        img.setTranslateX(250);

        img.setImage(image1);
        img.setFitHeight(100);
        img.setFitWidth(100);

        textArea = new TextArea();
        double height = 400; //making a variable called height with a value 400
        double width = 600;

        textArea.setTranslateY(110);
        textArea.setTranslateX(5);
        //You can use these methods
        textArea.setPrefHeight(height);  //sets height of the TextArea to 400 pixels 
        textArea.setPrefWidth(width);    //sets width of the TextArea to 300 pixels
        textArea.setWrapText(true);

        ScrollPane scrollPane = new ScrollPane();
        scrollPane.getStyleClass().add("noborder-scroll-pane");
        scrollPane.setContent(textArea);
        scrollPane.setFitToWidth(true);
        scrollPane.setPrefWidth(500);
        scrollPane.setPrefHeight(500);

        Button butt1 = new Button();

        butt1.setTranslateX(20);
        butt1.setTranslateY(550);
        butt1.setPrefWidth(300);

        butt1.setFocusTraversable(false);

        butt1.setText("Home Page");
        butt1.setStyle("-fx-border-color: #734d0f; -fx-border-width: 2px;-fx-text-fill: #151514");

        butt1.setOnAction(e -> window.setScene(scene));

        Group root1 = new Group();
        root1.getChildren().addAll(butt1, img, textArea);
        scene1 = new Scene(root1, 600, 600, Color.WHITESMOKE);

        primaryStage.getIcons().add(new Image("file:L20YLOGO.png"));
        primaryStage.setTitle("L20Y-Events");

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
