package menu;
import commands.Command;

public class MenuItem {
    private String description;
    private Command command;

    public MenuItem(String description, Command command) {
        this.description = description;
        this.command = command;
    }

    public String getDescription() {
        return description;
    }

    public void execute() {
        command.execute();
    }
}
