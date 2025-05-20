package gui;
import commands.*;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import bouquet.Bouquet;
import utils.FxAlertService;
import utils.FxInputService;
import java.util.logging.Logger;

public class MainLayout {
    private final BorderPane root;
    private static final Logger logger = Logger.getLogger(MainLayout.class.getName());
    private final FxAlertService alertService = new FxAlertService();
    private final FxInputService inputService = new FxInputService();

    public MainLayout() {
        HBox mainContent = new HBox();
        mainContent.setSpacing(10);
        mainContent.setPadding(new Insets(10));

        VBox centerPanel = new VBox();
        centerPanel.setPadding(new Insets(10));
        centerPanel.getStyleClass().add("center-panel");
        HBox.setHgrow(centerPanel, Priority.ALWAYS);

        Bouquet bouquet = new Bouquet();
        FlowerListPanel flowerListPanel = new FlowerListPanel(centerPanel, bouquet);

        ScrollPane scrollPane = flowerListPanel.getScrollPane();
        scrollPane.setFitToWidth(true);
        scrollPane.setMinWidth(150);
        scrollPane.setMaxWidth(300);
        scrollPane.getStyleClass().add("scroll-pane-style");
        HBox.setHgrow(scrollPane, Priority.ALWAYS);

        VBox rightPanel = new VBox(10);
        rightPanel.setPadding(new Insets(10));
        rightPanel.getStyleClass().add("right-panel");
        rightPanel.setMinWidth(150);
        rightPanel.setMaxWidth(300);
        HBox.setHgrow(rightPanel, Priority.ALWAYS);

        Label heading = new Label("Опції:");
        heading.getStyleClass().add("heading-label");
        rightPanel.getChildren().add(heading);

        Button showBouquetButton = new Button("Вивести букет");
        showBouquetButton.getStyleClass().add("option-button");
        showBouquetButton.setMaxWidth(Double.MAX_VALUE);
        showBouquetButton.setOnAction(e -> {
            logger.info("Користувач натиснув 'Вивести букет'");
            Command displayBouquetCommand = new DisplayBouquetCommand(bouquet, alertService);
            displayBouquetCommand.execute();
        });

        Button calculatePriceButton = new Button("Розрахувати ціну");
        calculatePriceButton.getStyleClass().add("option-button");
        calculatePriceButton.setMaxWidth(Double.MAX_VALUE);
        calculatePriceButton.setOnAction(e -> {
            logger.info("Користувач натиснув 'Розрахувати ціну'");
            Command command = new CalculatePriceCommand(bouquet, alertService);
            command.execute();
        });

        Button addAccessoryButton = new Button("Додати аксесуар");
        addAccessoryButton.getStyleClass().add("option-button");
        addAccessoryButton.setMaxWidth(Double.MAX_VALUE);
        addAccessoryButton.setOnAction(e -> {
            logger.info("Користувач натиснув 'Додати аксесуар'");
            Command command = new ChooseAccessoryCommand(bouquet, alertService, inputService);
            command.execute();
        });

        Button sortByFreshnessButton = new Button("Сортувати за свіжістю");
        sortByFreshnessButton.getStyleClass().add("option-button");
        sortByFreshnessButton.setMaxWidth(Double.MAX_VALUE);
        sortByFreshnessButton.setOnAction(e -> {
            logger.info("Користувач натиснув 'Сортувати за свіжістю'");
            Command command = new SortFreshnessCommand(bouquet, alertService);
            command.execute();
        });

        Button searchByStemButton = new Button("Пошук за стеблом");
        searchByStemButton.getStyleClass().add("option-button");
        searchByStemButton.setMaxWidth(Double.MAX_VALUE);
        searchByStemButton.setOnAction(e -> {
            logger.info("Користувач натиснув 'Пошук за стеблом'");
            Command command = new SearchStemCommand(bouquet, alertService, inputService);
            command.execute();
        });

        VBox buttonContainer = new VBox(20);
        buttonContainer.setAlignment(Pos.CENTER);
        buttonContainer.getChildren().addAll(
                showBouquetButton,
                calculatePriceButton,
                addAccessoryButton,
                sortByFreshnessButton,
                searchByStemButton
        );
        rightPanel.getChildren().add(buttonContainer);
        mainContent.getChildren().addAll(scrollPane, centerPanel, rightPanel);

        VBox topBox = new VBox();
        topBox.setPadding(new Insets(10));
        topBox.setSpacing(5);

        Label title = new Label("Flower shop ❀");
        title.getStyleClass().add("top-box-title");

        Label quote = new Label("Because every day deserves a little beauty");
        quote.getStyleClass().add("top-box-quote");

        topBox.getChildren().addAll(title, quote);

        root = new BorderPane();
        root.setTop(topBox);
        root.setCenter(mainContent);
    }

    public BorderPane getRoot() {
        return root;
    }
}
