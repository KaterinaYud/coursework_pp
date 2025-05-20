package utils;
import java.util.Optional;

public interface InputService {
    Optional<String> promptChoice(String title, String header, String content, java.util.List<String> options);
    Optional<String> promptText(String title, String header);
}
