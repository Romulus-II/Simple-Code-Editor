package com.main.simplecodeeditor;

import com.builddata.BuildData;
import com.builddata.DataHandler;
import com.builddata.forms.VariableForm;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TabPane;
import javafx.scene.layout.HBox;
import javafx.stage.Screen;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;

public class MainController extends Application {

    private DataHandler dataHandler;
    private void addIllegalCharacters() {
        BuildData.ILLEGAL_CHARACTERS.add('!');
        BuildData.ILLEGAL_CHARACTERS.add('.');
        BuildData.ILLEGAL_CHARACTERS.add(',');
        BuildData.ILLEGAL_CHARACTERS.add('<');
        BuildData.ILLEGAL_CHARACTERS.add('>');
        BuildData.ILLEGAL_CHARACTERS.add(':');
        BuildData.ILLEGAL_CHARACTERS.add(';');
        BuildData.ILLEGAL_CHARACTERS.add('?');
        BuildData.ILLEGAL_CHARACTERS.add('@');
        BuildData.ILLEGAL_CHARACTERS.add('#');
        BuildData.ILLEGAL_CHARACTERS.add('%');
        BuildData.ILLEGAL_CHARACTERS.add('^');
        BuildData.ILLEGAL_CHARACTERS.add('&');
        BuildData.ILLEGAL_CHARACTERS.add('*');
        BuildData.ILLEGAL_CHARACTERS.add('(');
        BuildData.ILLEGAL_CHARACTERS.add(')');
        BuildData.ILLEGAL_CHARACTERS.add('{');
        BuildData.ILLEGAL_CHARACTERS.add('}');
        BuildData.ILLEGAL_CHARACTERS.add('[');
        BuildData.ILLEGAL_CHARACTERS.add(']');
        BuildData.ILLEGAL_CHARACTERS.add('-');
        BuildData.ILLEGAL_CHARACTERS.add('`');
        BuildData.ILLEGAL_CHARACTERS.add('~');
        BuildData.ILLEGAL_CHARACTERS.add('+');
        BuildData.ILLEGAL_CHARACTERS.add('=');
        BuildData.ILLEGAL_CHARACTERS.add('/');
        BuildData.ILLEGAL_CHARACTERS.add('\"');
        BuildData.ILLEGAL_CHARACTERS.add('\\');
        BuildData.ILLEGAL_CHARACTERS.add('\'');
    }

    ArrayList<Rectangle2D> screenInfo = new ArrayList<>();

    private void getDisplayInfo() {
        screenInfo = new ArrayList<>();
        ObservableList<Screen> screenSizes = Screen.getScreens();
        for (Screen screen : screenSizes) {
            screenInfo.add(screen.getVisualBounds());
        }
    }

    private double width=0, height=0;
    private int activeDisplay = 0;

    public void resizeDimensions() {
        getDisplayInfo();
        width = screenInfo.get(activeDisplay).getWidth();
        height = screenInfo.get(activeDisplay).getHeight();
    }

    @FXML private HBox menuBar;
    @FXML private VBox sideBar;
    @FXML private VBox main;

    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }

    /*
        Variable View Section
     */
    private double variablePadding = 10;
    @FXML private VBox variableView;
    @FXML private Label variableTitle;
    @FXML private VBox variableListContent = new VBox();
    @FXML private ScrollPane variableListContainer = new ScrollPane();
    @FXML private Button newVariableButton;

    /*
        Section View Section
     */
    @FXML private TabPane sectionView;

    /*
        Command View Section
     */




    int activeSection = 0;


    @Override
    public void start(Stage appWindow) throws Exception {
        addIllegalCharacters();

        FXMLLoader loader = new FXMLLoader(Main.class.getResource("hello-view.fxml"));
        loader.setController(this);
        VBox root = loader.load();
        final double MIN_WIDTH = 800, MIN_HEIGHT = 600;
        final double MENU_BAR_HEIGHT = 25;

        // Set Application Dimensions
        resizeDimensions();
        System.out.println("(" + width + ", " + height + ")");
        root.setPrefSize(width, height);
        root.setMinSize(MIN_WIDTH, MIN_HEIGHT);
        Scene scene = new Scene(root, width, height);
        appWindow.setTitle("Hello!");
        appWindow.setScene(scene);

        // Set App Component Dimensions
        root.prefWidthProperty().bind(appWindow.widthProperty());
        root.prefHeightProperty().bind(appWindow.heightProperty());
        appWindow.setWidth(width);
        appWindow.setMinWidth(MIN_WIDTH);
        appWindow.setHeight(height);
        appWindow.setMinHeight(MIN_HEIGHT);


        menuBar.setPrefSize(width, MENU_BAR_HEIGHT);
        menuBar.setMinSize(MIN_WIDTH,MENU_BAR_HEIGHT);
        menuBar.prefWidthProperty().bind(appWindow.widthProperty());

        sideBar.setPrefSize((width/4), (height-MENU_BAR_HEIGHT));
        sideBar.setMinSize((MIN_WIDTH/4), (MIN_HEIGHT-MENU_BAR_HEIGHT));
        sideBar.prefWidthProperty().bind(appWindow.widthProperty().divide(4));
        sideBar.prefHeightProperty().bind(appWindow.heightProperty().subtract(MENU_BAR_HEIGHT));

        variableView.setPrefSize((width/4), ((height-MENU_BAR_HEIGHT)/2));
        variableView.setMinSize((MIN_WIDTH/4), ((MIN_HEIGHT-MENU_BAR_HEIGHT)/2));
        variableView.prefWidthProperty().bind(appWindow.widthProperty().divide(4));
        variableView.prefHeightProperty().bind(appWindow.heightProperty().subtract(MENU_BAR_HEIGHT).divide(2));

        double variableListContainerPrefHeight = variableView.getPrefHeight() - (variablePadding*4) -
                variableTitle.getPrefHeight() - newVariableButton.getPrefHeight();
        double variableListContainerMinHeight = variableView.getMinHeight() - (variablePadding*4) -
                variableTitle.getPrefHeight() - newVariableButton.getPrefHeight();
        variableListContainer.setPrefSize((width/4)-(variablePadding*2)-20,
                variableListContainerPrefHeight);
        variableListContainer.setMinSize((MIN_WIDTH/4)-(variablePadding*2),
                variableListContainerMinHeight);
        variableListContainer.prefWidthProperty().bind(appWindow.widthProperty().divide(4).subtract(variablePadding*2));
        variableListContainer.prefHeightProperty().bind(appWindow.heightProperty().subtract(MENU_BAR_HEIGHT).divide(2)
                .subtract((variablePadding*4)+variableTitle.getPrefHeight()+newVariableButton.getPrefHeight()));
        variableListContent.setPrefSize(variableListContainer.getPrefWidth()-15,variableListContainer.getPrefHeight());
        variableListContent.setMinSize(variableListContainer.getMinWidth()-15,variableListContainer.getMinHeight());
        variableListContent.prefWidthProperty().bind(variableListContainer.widthProperty().subtract(15));
        variableListContent.prefHeightProperty().bind(variableListContainer.heightProperty());

        main.setPrefSize((width/4)*3, (height-MENU_BAR_HEIGHT));
        main.setMinSize((MIN_WIDTH/4)*3, (MIN_HEIGHT-MENU_BAR_HEIGHT));
        main.prefWidthProperty().bind(appWindow.widthProperty().divide(4).multiply(3));
        main.prefHeightProperty().bind(appWindow.heightProperty().subtract(MENU_BAR_HEIGHT));

        sectionView.setPrefSize((width/4)*3, (height-MENU_BAR_HEIGHT));
        sectionView.setMinSize((MIN_WIDTH/4)*3, (MIN_HEIGHT-MENU_BAR_HEIGHT));
        sectionView.prefWidthProperty().bind(appWindow.widthProperty().divide(4).multiply(3));
        sectionView.prefHeightProperty().bind(appWindow.heightProperty().subtract(MENU_BAR_HEIGHT));



        dataHandler = new DataHandler(variableListContent, sectionView);

        newVariableButton.setOnAction((ActionEvent event) -> {
            new VariableForm(dataHandler);
        });

        appWindow.setMaximized(true);
        appWindow.show();
    }
}