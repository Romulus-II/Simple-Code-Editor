package com.builddata.forms;

import com.builddata.DataHandler;
import com.builddata.models.Variable;
import com.builddata.validators.VariableValidator;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class VariableForm {

    private boolean updatingVariable;
    private Variable variable;

    private Text errorMessage;
    private TextField nameTextField;
    private ChoiceBox<String> typeDropDown;
    private TextField stringValueTextField, integerValueTextField, floatValueTextField;
    private ChoiceBox<String> booleanValueDropDown;

    private Label content;

    private DataHandler dataHandler;

    public VariableForm(DataHandler dataHandler) {
        this.dataHandler = dataHandler;
        updatingVariable = false;
        Stage stage = generateForm();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();
    }

    public VariableForm(DataHandler dataHandler, Label content, Variable variable) {
        this.dataHandler = dataHandler;
        this.content = content;
        updatingVariable = true;
        this.variable = variable;
        Stage stage = generateForm();
        fillInValues(variable);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();
    }

    private Stage generateForm() {
        VBox root = new VBox();

        errorMessage = new Text("");
        errorMessage.setFont(Font.font("Times New Roman", FontWeight.BOLD, 12));
        errorMessage.setFill(Color.RED);

        GridPane form = new GridPane();

        Label nameLabel = new Label("Name:");
        nameTextField = new TextField();
        nameTextField.setPrefWidth(100);

        Label typeLabel = new Label("Type:");
        typeDropDown = new ChoiceBox<>();
        typeDropDown.setPrefWidth(100);
        typeDropDown.getItems().addAll("String", "integer", "float", "boolean");

        Label valueLabel = new Label("Value:");
        stringValueTextField = new TextField();
        stringValueTextField.setPrefWidth(100);
        integerValueTextField = new TextField();
        integerValueTextField.setPrefWidth(100);
        floatValueTextField = new TextField();
        floatValueTextField.setPrefWidth(100);
        booleanValueDropDown = new ChoiceBox<>();
        booleanValueDropDown.setPrefWidth(100);
        booleanValueDropDown.getItems().addAll("True", "False");
        booleanValueDropDown.getSelectionModel().select(0);

        form.add(nameLabel, 1, 1);
        form.add(nameTextField, 2, 1);
        form.add(typeLabel, 1, 2);
        form.add(typeDropDown, 2, 2);
        form.add(valueLabel, 1, 3);
        switch (typeDropDown.getSelectionModel().getSelectedIndex()) {
            case 0:
                form.add(stringValueTextField, 2, 3);
                break;
            case 1:
                form.add(integerValueTextField, 2, 3);
                break;
            case 2:
                form.add(floatValueTextField, 2, 3);
                break;
            case 3:
                form.add(booleanValueDropDown, 2, 3);
                break;
            default:
                break;
        }
        form.setAlignment(Pos.CENTER);
        form.setHgap(25);
        form.setVgap(10);

        // Change Value Input Area based on selected Variable Type
        typeDropDown.setOnAction((ActionEvent event) -> {
            Node valueNode = getNodeFromGridPane(form, 2, 3);
            if (valueNode == stringValueTextField) {
                form.getChildren().remove(stringValueTextField);
            } else if (valueNode == integerValueTextField) {
                form.getChildren().remove(integerValueTextField);
            } else if (valueNode == floatValueTextField) {
                form.getChildren().remove(floatValueTextField);
            } else if (valueNode == booleanValueDropDown) {
                form.getChildren().remove(booleanValueDropDown);
            }

            switch (typeDropDown.getSelectionModel().getSelectedIndex()) {
                case 0:
                    form.add(stringValueTextField, 2, 3);
                    break;
                case 1:
                    form.add(integerValueTextField, 2, 3);
                    break;
                case 2:
                    form.add(floatValueTextField, 2, 3);
                    break;
                case 3:
                    form.add(booleanValueDropDown, 2, 3);
                    break;
                default:
                    break;
            }
        });
        typeDropDown.getSelectionModel().select(0);

        Button button = new Button();
        if (!updatingVariable) {
            button.setText("Add Variable");
        } else {
            button.setText("Update Variable");
        }

        root.getChildren().addAll(form, errorMessage, button);
        root.setSpacing(10);
        root.setAlignment(Pos.CENTER);

        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        button.setAlignment(Pos.CENTER);
        button.setOnAction((ActionEvent event) -> {
            String variableName = nameTextField.getText();
            String variableType = typeDropDown.getValue().toLowerCase();
            String variableValue = "";
            switch (typeDropDown.getSelectionModel().getSelectedIndex()) {
                case 0:
                    variableValue = stringValueTextField.getText();
                    break;
                case 1:
                    variableValue = stringValueTextField.getText();
                    break;
                case 2:
                    variableValue = stringValueTextField.getText();
                    break;
                case 3:
                    variableValue = booleanValueDropDown.getValue();
                    break;
                default:
                    break;
            }
            Variable variable = new Variable(variableName, variableType, variableValue);
            VariableValidator validator = new VariableValidator(variable);
            if (validator.passesValidation()) {
                errorMessage.setText("");
                stage.close();
                if (!updatingVariable) {
                    dataHandler.addVariable(variable);
                } else {
                    dataHandler.updateVariable(content, this.variable, variable);
                }
            } else {
                errorMessage.setText(validator.getErrorMessage());
            }
        });

        stage.setWidth(300);
        stage.setHeight(300);
        stage.setResizable(false);
        if (!updatingVariable) {
            stage.setTitle("Create Variable");
        } else {
            stage.setTitle("Edit Variable");

        }

        return stage;
    }

    // function from https://newbedev.com/javafx-gridpane-retrieve-specific-cell-content
    private Node getNodeFromGridPane(GridPane gridPane, int col, int row) {
        for (Node node : gridPane.getChildren()) {
            if (GridPane.getColumnIndex(node) == col && GridPane.getRowIndex(node) == row) {
                return node;
            }
        }
        return null;
    }

    private void fillInValues(Variable variable) {
        nameTextField.setText(variable.getName());

        if (variable.getValue() == "False") {
            booleanValueDropDown.getSelectionModel().select(1);
        } else {
            booleanValueDropDown.getSelectionModel().select(0);
        }
        switch (variable.getType()) {
            case "string":
                typeDropDown.getSelectionModel().select(0);
                stringValueTextField.setText(variable.getValue());
                break;
            case "integer":
                typeDropDown.getSelectionModel().select(1);
                integerValueTextField.setText(variable.getValue());
                break;
            case "float":
                typeDropDown.getSelectionModel().select(2);
                floatValueTextField.setText(variable.getValue());
                break;
            case "boolean":
                typeDropDown.getSelectionModel().select(3);
                break;
            default:
                typeDropDown.getSelectionModel().select(0);
                break;
        }
    }

}
