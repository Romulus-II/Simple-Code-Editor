package com.builddata;

import com.builddata.forms.VariableForm;
import com.builddata.models.Section;
import com.builddata.models.Variable;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.TabPane;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

import java.util.ArrayList;

public class DataHandler implements BuildData {

    VBox variableListContent;
    TabPane sectionView;

    public DataHandler(VBox variableListContent, TabPane sectionView) {
        this.variableListContent = variableListContent;
        this.sectionView = sectionView;

        addSection("Section1");
    }

    public void addSection(String sectionName) {
        Section section = new Section(sectionName, sectionView);
        sectionView.getTabs().add(section);
        BuildData.SECTIONS.add(section);
    }

    public void addVariable(Variable variable) {
        HBox variableDisplay = new HBox();
        variableDisplay.setPrefWidth(variableListContent.getPrefWidth());
        variableDisplay.prefWidthProperty().bind(variableDisplay.widthProperty());
        variableDisplay.setStyle("-fx-border-color:black; -fx-border-width: 1 0 1 0; -fx-border-style: solid;");
        Label variableLabel = new Label(variable.getName());
        variableLabel.setPadding(new Insets(0,0,0,5));
        variableLabel.setOnMouseClicked((MouseEvent event) -> {
            // On Left Click, edit the variable;
            if (event.getButton().equals(MouseButton.PRIMARY)) {
                System.out.println("Hello");
                new VariableForm(this, variableLabel, variable);
            }
        });
        variableLabel.setFont(Font.font("Times New Roman", 14));
        variableDisplay.getChildren().add(variableLabel);
        variableListContent.getChildren().add(variableDisplay);
        BuildData.VARIABLES.add(variable);
    }

    public void updateVariable(Label variableLabel, Variable oldVariable, Variable newVariable) {
        int index = BuildData.VARIABLES.indexOf(oldVariable);
        BuildData.VARIABLES.set(index, newVariable);
        oldVariable = newVariable;
        variableLabel.setText(newVariable.getName());
    }

    public Section getSectionByName(String name) throws Exception {
        for (Section section: BuildData.SECTIONS) {
            if (name == section.getName()) {
                return section;
            }
        }
        throw new Exception("Section with name " + name + " not found");
    }
}
