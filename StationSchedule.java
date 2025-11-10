
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.text.*;
import javafx.scene.paint.*;
import javafx.geometry.*;
import javafx.scene.layout.*;
import java.util.*;
import javafx.scene.control.Alert.*;

public class StationSchedule<Z> extends Application
{
    public void start(Stage mainStage)
    {
        // New train station object is required to run
        TrainStation station = new TrainStation();
        // Set the main layout and title
        BorderPane root = new BorderPane();
        Scene mainScene = new Scene(root,400,600);
        mainStage.setTitle("Train Station Schedule");
        mainStage.setScene(mainScene);
        VBox layout = new VBox();
        root.setCenter(layout);
        layout.setAlignment(Pos.CENTER);
        layout.setSpacing(10);
        // Set up the columns in one HBox
        HBox scheduleRow = new HBox();
        scheduleRow.setAlignment(Pos.CENTER);
        // Destination Column
        VBox destColumn = new VBox();
        destColumn.setSpacing(15);
        destColumn.setStyle("-fx-border-color: black; -fx-border-width: 2; -fx-padding: 10;");
        Label destLabel = new Label("Destination");
        destLabel.setStyle("-fx-font-weight: bold; -fx-font-size: 20");
        destColumn.getChildren().add(destLabel);
        // Time Column
        VBox timeColumn = new VBox();
        timeColumn.setSpacing(15);
        timeColumn.setStyle("-fx-border-color: black; -fx-border-width: 2; -fx-padding: 10;");
        Label timeLabel = new Label("Time");
        timeLabel.setStyle("-fx-font-weight: bold; -fx-font-size: 20");
        timeColumn.getChildren().add(timeLabel);
        // Add both columns
        scheduleRow.getChildren().addAll(destColumn,timeColumn);
        layout.getChildren().add(scheduleRow);
        // Buttons to simulate arrival and departure in separate row
        HBox buttonBox = new HBox();
        Button arrivalButton = new Button("New Arrival");
        arrivalButton.setStyle("-fx-font-size: 20");
        arrivalButton.setOnAction((e->{
            Train arrived = station.arrival();
            Label arrivedDest = new Label(arrived.getDestination());
            arrivedDest.setStyle("-fx-font-size: 20");
            destColumn.getChildren().add(arrivedDest);
            Label arrivedTime = new Label(arrived.getDepartTime());
            arrivedTime.setStyle("-fx-font-size: 20");
            timeColumn.getChildren().add(arrivedTime);
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Arrival Notification");
            alert.setHeaderText(null);
            alert.setContentText("Train bound for " + arrived.getDestination() + " at " + arrived.getDepartTime() + " has arrived.");
            alert.showAndWait(); 
        }));
        Button departButton = new Button("New Departure");
        departButton.setStyle("-fx-font-size: 20");
        departButton.setOnAction((e->{
            Train departed = station.departure();
            if (departed != null) {
                // Remove the first entry in each column
                if (!destColumn.getChildren().isEmpty()) {
                    destColumn.getChildren().remove(1);
                }
                if (!timeColumn.getChildren().isEmpty()) {
                    timeColumn.getChildren().remove(1);
                }
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Departure Notification");
                alert.setHeaderText(null);
                alert.setContentText("Train bound for " + departed.getDestination() + " at " + departed.getDepartTime() + " has departed.");
                alert.showAndWait();
            } else {
                Alert alert = new Alert(AlertType.WARNING);
                alert.setTitle("No Departures");
                alert.setHeaderText(null);
                alert.setContentText("No trains available for departure.");
                alert.showAndWait();
            }
        }));
        buttonBox.setAlignment(Pos.CENTER);
        buttonBox.setSpacing(20);
        buttonBox.getChildren().addAll(arrivalButton, departButton);
        layout.getChildren().add(buttonBox);
        // Show once all elements are added
        mainStage.show();
    }
    public static Void main (String[] args)
    {
        launch(args);
        return null;
    }
}
