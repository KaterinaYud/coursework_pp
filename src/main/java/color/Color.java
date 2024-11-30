package color;
import java.util.Arrays;
import java.util.List;

public class Color {
    public static final List<String> VALID_COLORS = Arrays.asList(
            "ЧЕРВОНИЙ", "БІЛИЙ", "ЖОВТИЙ", "РОЖЕВИЙ", "БЛАКИТНИЙ", "СИНІЙ",
            "ФІОЛЕТОВИЙ", "ПОМАРАНЧЕВИЙ", "ЧОРНИЙ", "ЗЕЛЕНИЙ", "КОРИЧНЕВИЙ", "СІРИЙ", "БЕЖЕВИЙ", "ЗОЛОТИЙ"
    );

    public static boolean isValidColor(String color) {
        return VALID_COLORS.contains(color.toUpperCase());
    }
}

