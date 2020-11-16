package sample.factory;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import sample.handler.InfoPopUpHandler;

public class PopUpFactory {

    public Stage createWaitingPopUp(String text) {
        Stage stage = new Stage();

        stage.initStyle(StageStyle.UNDECORATED);
        VBox pane = new VBox();
        pane.setStyle(waintingPopUpPaneStyle());
        pane.setAlignment(Pos.CENTER);
        pane.setSpacing(10);
        Label label = new Label(text);
        label.setStyle(waitingLabelStyle());
        ProgressBar progressBar = new ProgressBar();
        pane.getChildren().addAll(progressBar, label);


        stage.setScene(new Scene(pane, 200, 100));
        stage.initModality(Modality.APPLICATION_MODAL);
        return stage;
    }

    private String waitingLabelStyle() {
        return "-fx-text-fill: #003366";
    }

    private String waintingPopUpPaneStyle() {
        return "-fx-background-color: #c7c7c7; " +
                "-fx-border-color: #003366";
    }

    public String okButtonStyle() {
        return ".login-button{\n" +
                "    -fx-text-fill: #003366;\n" +
                "    -fx-background-color: #c7c7c7;\n" +
                "    -fx-border-color: #003366;\n" +
                "    -fx-background-radius: 0;\n" +
                "}\n" +
                ".login-button:hover{\n" +
                "    -fx-background-color: #e1e1e1;\n" +
                "}";
    }

    private String okButtonHoverStyle() {
        return ".login-button{\n" +
                "    -fx-text-fill: #003366;\n" +
                "    -fx-background-color: #c7c7c7;\n" +
                "    -fx-border-color: #003366;\n" +
                "    -fx-background-radius: 0;\n" +
                "}\n" +
                ".login-button:hover{\n" +
                "    -fx-background-color: #e1e1e1;\n" +
                "}";
    }

    public Stage createInfoPopUp(String text, InfoPopUpHandler handler) {
        Stage stage = new Stage();

        stage.initStyle(StageStyle.UNDECORATED);
        VBox pane = new VBox();
        pane.setStyle(waintingPopUpPaneStyle());
        pane.setAlignment(Pos.CENTER);
        pane.setSpacing(10);
        Label label = new Label(text);
        label.setStyle(waitingLabelStyle());
        Button okButton = new Button("OK");
        okButton.setStyle(okButtonStyle());
        okButton.setOnMouseEntered(x ->{
            okButton.setStyle(okButtonHoverStyle());
        });
        okButton.setOnMouseExited(x -> {
            okButton.setStyle(okButtonStyle());
        });
        okButton.setOnAction(x -> {
            stage.close();
            handler.handle();
        });

        pane.getChildren().addAll(label, okButton);
        stage.setScene(new Scene(pane, 200, 100));
        stage.initModality(Modality.APPLICATION_MODAL);
        return stage;
    }
}
