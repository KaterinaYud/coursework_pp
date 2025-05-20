package gui;
import bouquet.Bouquet;
import commands.*;
import database.FlowerRepository;
import flower.Flower;
import color.Color;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import utils.AlertService;
import utils.FxAlertService;
import java.io.InputStream;
import java.util.List;
import java.util.logging.Logger;

public class FlowerListPanel {
    private final ScrollPane scrollPane;
    private static final Logger logger = Logger.getLogger(FlowerListPanel.class.getName());
    AlertService alertService = new FxAlertService();

    public FlowerListPanel(VBox centerPanel, Bouquet bouquet) {
        FlowerRepository repository = new FlowerRepository();
        List<Flower> flowers = repository.loadFlowers();
        logger.info("Завантажено квітів з бази: " + flowers.size());

        VBox flowerListBox = new VBox(10);
        flowerListBox.setPadding(new Insets(10));

        Label heading = new Label("Квіти:");
        heading.getStyleClass().add("flower-list-heading");
        flowerListBox.getChildren().add(heading);

        for (Flower flower : flowers) {
            String imageFileName = flower.getName().replace(" ", "_") + ".jpg";
            InputStream imageStream = getClass().getResourceAsStream("/flowers/" + imageFileName);
            Image image = imageStream != null
                    ? new Image(imageStream)
                    : new Image("https://via.placeholder.com/100x100.png?text=NA");

            ImageView imageView = new ImageView(image);
            imageView.setFitWidth(100);
            imageView.setFitHeight(100);
            imageView.setPreserveRatio(true);

            Label nameLabel = new Label(flower.getName());
            nameLabel.getStyleClass().add("flower-name-label");

            VBox flowerItem = new VBox(imageView, nameLabel);
            flowerItem.getStyleClass().add("flower-item");
            flowerItem.setSpacing(5);
            flowerItem.setPadding(new Insets(5));
            flowerItem.setPrefWidth(100);

            flowerItem.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> showFlowerDetails(centerPanel, flower, bouquet));
            flowerListBox.getChildren().add(flowerItem);
        }
        scrollPane = new ScrollPane(flowerListBox);
        scrollPane.setFitToWidth(true);
        scrollPane.setMinWidth(150);
        scrollPane.setMaxWidth(300);
        scrollPane.getStyleClass().add("scroll-pane");
    }

    public ScrollPane getScrollPane() {
        return scrollPane;
    }

    private void showFlowerDetails(VBox centerPanel, Flower flower, Bouquet bouquet) {
        centerPanel.getChildren().clear();

        String imageFileName = flower.getName().replace(" ", "_") + ".jpg";
        InputStream imageStream = getClass().getResourceAsStream("/flowers/" + imageFileName);
        Image image = imageStream != null
                ? new Image(imageStream)
                : new Image("https://via.placeholder.com/270x270.png?text=NA");

        Label title = new Label(flower.getName() + " (" + flower.getType() + ")");
        title.getStyleClass().add("detail-title");

        ImageView largeImageView = new ImageView(image);
        largeImageView.setFitWidth(270);
        largeImageView.setFitHeight(270);
        largeImageView.setPreserveRatio(true);

        Label color = new Label("Колір: " + flower.getColor());
        Label price = new Label("Ціна: " + flower.getPrice() + " грн");
        Label freshness = new Label("Свіжість: " + flower.getFreshness());
        Label stem = new Label("Довжина стебла: " + flower.getStemLength() + " см");

        Button addButton = new Button("Додати до букета");
        addButton.getStyleClass().add("detail-button");
        addButton.setOnAction(e -> {
            Command command = new AddToBouquetCommand(bouquet, flower, new FxAlertService());
            command.execute();
        });

        Button editButton = new Button("Редагувати квітку");
        editButton.getStyleClass().add("detail-button");
        editButton.setOnAction(e -> showEditDialog(flower, centerPanel, bouquet));

        Button deleteButton = new Button("Видалити з букета");
        deleteButton.getStyleClass().addAll("detail-button", "delete-button");
        deleteButton.setOnAction(e -> {
            Command command = new DeleteFromBouquetCommand(bouquet, flower, new FxAlertService());
            command.execute();
        });

        VBox detailBox = new VBox(15);
        detailBox.getStyleClass().add("detail-box");
        detailBox.setAlignment(Pos.TOP_CENTER);
        detailBox.setPadding(new Insets(20));
        detailBox.getChildren().addAll(title, largeImageView, color, price, freshness, stem, addButton, editButton, deleteButton);
        VBox.setVgrow(detailBox, Priority.ALWAYS);

        centerPanel.getChildren().add(detailBox);
    }

    private void showEditDialog(Flower flower, VBox centerPanel, Bouquet bouquet) {
        Stage dialog = new Stage();

        dialog.setTitle("Редагування квітки");
        dialog.setResizable(false);
        dialog.setWidth(400);
        VBox dialogVBox = new VBox(10);
        dialogVBox.setPadding(new Insets(15));

        TextField colorField = new TextField(flower.getColor());
        TextField priceField = new TextField(String.valueOf(flower.getPrice()));
        TextField freshnessField = new TextField(String.valueOf(flower.getFreshness()));
        TextField stemField = new TextField(String.valueOf(flower.getStemLength()));

        Button saveButton = new Button("Зберегти");
        saveButton.setOnAction(e -> {
            try {
                String color = colorField.getText().trim();
                double price = Double.parseDouble(priceField.getText().trim());
                int freshness = Integer.parseInt(freshnessField.getText().trim());
                int stemLength = Integer.parseInt(stemField.getText().trim());

                if (!Color.isValidColor(color)) {
                    alertService.showError("Помилка", "Немає наявного кольору.");
                    return;
                }

                if (price <= 0 || freshness <= 0 || stemLength <= 0) {
                    alertService.showError("Помилка", "Усі значення мають бути додатні!");
                    logger.warning("Введено недопустимі значення при редагуванні квітки.");
                    return;
                }

                Command editCommand = new EditFlowerCommand(
                        flower, color, price, freshness, stemLength, alertService
                );
                editCommand.execute();
                dialog.close();
                showFlowerDetails(centerPanel, flower, bouquet);
            } catch (NumberFormatException ex) {
                alertService.showError("Помилка", "Некоректний формат чисел.");
            }
        });

        dialogVBox.getChildren().addAll(
                new Label("Колір:"), colorField,
                new Label("Ціна:"), priceField,
                new Label("Свіжість:"), freshnessField,
                new Label("Довжина стебла:"), stemField,
                saveButton
        );
        Scene scene = new Scene(dialogVBox);
        dialog.setScene(scene);
        dialog.showAndWait();
    }
}
