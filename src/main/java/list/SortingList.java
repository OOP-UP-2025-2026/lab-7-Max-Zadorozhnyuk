package list;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class SortingList extends Application {

    private ObservableList<Student> students;

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Список студентів");

        students = FXCollections.observableArrayList(
                new Student("Борис", "Іванов", 75),
                new Student("Петро", "Петренко", 92),
                new Student("Сергій", "Сергієнко", 61),
                new Student("Григорій", "Сковорода", 88)
        );

        ListView<Student> listView = new ListView<>(students);
        listView.setPrefSize(400, 240);

        Button sortByNameButton = new Button("Сортувати за ім'ям");
        Button sortByLastNameButton = new Button("Сортувати за прізвищем");
        Button sortByMarkButton = new Button("Сортувати за оцінкою");

        HBox.setHgrow(sortByNameButton, Priority.ALWAYS);
        HBox.setHgrow(sortByLastNameButton, Priority.ALWAYS);
        HBox.setHgrow(sortByMarkButton, Priority.ALWAYS);
        sortByNameButton.setMaxWidth(Double.MAX_VALUE);
        sortByLastNameButton.setMaxWidth(Double.MAX_VALUE);
        sortByMarkButton.setMaxWidth(Double.MAX_VALUE);

        final boolean[] nameOrder = {true};
        final boolean[] lastNameOrder = {true};
        final boolean[] markOrder = {true};

        // Сортування за ім'ям (лямбда)
        sortByNameButton.setOnAction(event -> {
            students.sort((s1, s2) -> nameOrder[0]
                    ? s1.getName().compareTo(s2.getName())
                    : s2.getName().compareTo(s1.getName()));
            nameOrder[0] = !nameOrder[0];
        });

        // Сортування за прізвищем (лямбда)
        sortByLastNameButton.setOnAction(event -> {
            students.sort((s1, s2) -> lastNameOrder[0]
                    ? s1.getLastName().compareTo(s2.getLastName())
                    : s2.getLastName().compareTo(s1.getLastName()));
            lastNameOrder[0] = !lastNameOrder[0];
        });

        // Сортування за середнім балом (лямбда)
        sortByMarkButton.setOnAction(event -> {
            students.sort((s1, s2) -> markOrder[0]
                    ? Double.compare(s1.getAvgMark(), s2.getAvgMark())
                    : Double.compare(s2.getAvgMark(), s1.getAvgMark()));
            markOrder[0] = !markOrder[0];
        });

        HBox hbox = new HBox(5, sortByNameButton, sortByLastNameButton, sortByMarkButton);
        hbox.setAlignment(Pos.CENTER);

        VBox vbox = new VBox(10, listView, hbox);
        vbox.setPadding(new Insets(10));
        vbox.setAlignment(Pos.CENTER);

        primaryStage.setScene(new Scene(vbox));
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}