package com.builddata.forms.commands;

import com.builddata.BuildData;
import com.builddata.DataHandler;
import com.builddata.models.Variable;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.ArrayList;

public class StringCommandForm implements BuildData {

    private boolean updatingCommand;
    private String action;
    private ArrayList<Variable> variables;

    private Text errorMessage;

    private DataHandler dataHandler;

    public StringCommandForm(DataHandler dataHandler, String action) {
        this.dataHandler = dataHandler;
        this.action = action;
        updatingCommand = false;
        variables = new ArrayList<>();
        generateForm();
    }

    private Stage generateForm() {

        errorMessage = new Text("");
        errorMessage.setFont(Font.font("Times New Roman", FontWeight.BOLD, 12));
        errorMessage.setFill(Color.RED);

        GridPane form = new GridPane();
        VBox root = new VBox();;
        switch (action) {
            case "assign":
                addStringAssignContent();
                break;
            default:
                root = new VBox();
                break;
        }

        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        return stage;
    }

    private VBox addStringAssignContent() {
        VBox root = new VBox();

        Label valueLabel = new Label("Name:");
        TextField valueTextField = new TextField();
        valueTextField.setPrefWidth(100);

        Label typeLabel = new Label("Type:");
        ChoiceBox destinationDropDown = new ChoiceBox<>();
        destinationDropDown.setPrefWidth(100);
        for (Variable variable: BuildData.VARIABLES) {
            if (variable.getType().equals("string")) {
                destinationDropDown.getItems().add(variable.getName());
            }
        }

        GridPane form = new GridPane();

        //root.getChildren().addAll(form, errorMessage, button);
        root.setSpacing(10);
        root.setAlignment(Pos.CENTER);
        return root;
    }

    private VBox addStringTrimContent() {
        VBox root = new VBox();

        Label valueLabel = new Label("Name:");
        TextField valueTextField = new TextField();
        valueTextField.setPrefWidth(100);

        Label typeLabel = new Label("Type:");
        ChoiceBox destinationDropDown = new ChoiceBox<>();
        destinationDropDown.setPrefWidth(100);
        for (Variable variable: BuildData.VARIABLES) {
            if (variable.getType().equals("string")) {
                destinationDropDown.getItems().add(variable.getName());
            }
        }

        GridPane form = new GridPane();

        //root.getChildren().addAll(form, errorMessage, button);
        root.setSpacing(10);
        root.setAlignment(Pos.CENTER);
        return root;
    }

}
