package commands;
import flower.Flower;
import utils.AlertService;
import java.util.logging.Level;
import java.util.logging.Logger;

public class EditFlowerCommand implements Command {
    private static final Logger logger = Logger.getLogger(EditFlowerCommand.class.getName());
    private final Flower flower;
    private final String newColor;
    private final double newPrice;
    private final int newFreshness;
    private final int newStemLength;
    private final AlertService alertService;

    public EditFlowerCommand(Flower flower, String newColor, double newPrice, int newFreshness, int newStemLength, AlertService alertService) {
        this.flower = flower;
        this.newColor = newColor;
        this.newPrice = newPrice;
        this.newFreshness = newFreshness;
        this.newStemLength = newStemLength;
        this.alertService = alertService;
    }

    @Override
    public void execute() {
        try {
            flower.setColor(newColor);
            flower.setPrice(newPrice);
            flower.setFreshness(newFreshness);
            flower.setStemLength(newStemLength);
            logger.info("Квітка оновлена: " + flower);
            alertService.showInfo("Редагування квітки", "Квітку оновлено:\n" + flower);
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Сталася помилка при редагуванні квітки", e);
        }
    }
}

