package com.builddata.models;

import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.ArrayList;

public class Section extends Tab {

    private final ArrayList<Command> COMMANDS;
    private Tab commandView;
    private HBox sectionContainer;
    private ScrollPane commandDisplay;
    private VBox commandContent;

    private String name;

    public Section(String name, TabPane sectionView) {
        super(name);
        COMMANDS = new ArrayList<>();
        this.name = name;

        sectionContainer = new HBox();
        commandDisplay = new ScrollPane();
        commandContent = new VBox();
        sectionContainer.setPrefSize(sectionView.getPrefWidth()-15, sectionView.getPrefHeight());
        sectionContainer.setMinSize(sectionView.getMinWidth()-15, sectionView.getMinHeight());
        sectionContainer.prefWidthProperty().bind(sectionView.prefWidthProperty().subtract(15));
        sectionContainer.prefHeightProperty().bind(sectionView.prefHeightProperty());
        commandDisplay.setPrefSize(sectionContainer.getPrefWidth(), sectionContainer.getPrefHeight());
        commandDisplay.setMinSize(sectionContainer.getMinWidth(), sectionContainer.getMinHeight());
        commandDisplay.prefWidthProperty().bind(sectionContainer.prefWidthProperty());
        commandDisplay.prefHeightProperty().bind(sectionContainer.prefHeightProperty());
        commandContent.setPrefSize(commandDisplay.getPrefWidth()-15, commandDisplay.getPrefHeight());
        commandContent.setMinSize(commandDisplay.getMinWidth()-15, commandDisplay.getMinHeight());
        commandContent.prefWidthProperty().bind(commandDisplay.prefWidthProperty().subtract(15));
        commandContent.prefHeightProperty().bind(commandDisplay.prefHeightProperty());
        commandDisplay.setContent(commandContent);

        sectionContainer.getChildren().add(commandDisplay);
        this.setContent(sectionContainer);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void addCommand(Command command) {
        COMMANDS.add(command);

    }
}
