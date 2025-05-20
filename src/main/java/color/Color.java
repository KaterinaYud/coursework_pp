package color;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

public class Color {
    private static final Logger logger = Logger.getLogger(Color.class.getName());
    public static final List<String> VALID_COLORS = Arrays.asList(
            "ЧЕРВОНИЙ", "БІЛИЙ", "ЖОВТИЙ", "РОЖЕВИЙ", "БЛАКИТНИЙ", "СИНІЙ", "БОРДОВИЙ",
            "ФІОЛЕТОВИЙ", "ПОМАРАНЧЕВИЙ", "ЧОРНИЙ", "ЗЕЛЕНИЙ", "КОРИЧНЕВИЙ", "СІРИЙ", "БЕЖЕВИЙ", "ЗОЛОТИЙ"
    );

    public static boolean isValidColor(String color) {
        boolean valid = VALID_COLORS.contains(color.toUpperCase());
        if (!valid) {
            logger.warning("Некоректний колір: " + color);
        }
        return valid;
    }
}

