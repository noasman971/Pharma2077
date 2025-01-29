import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Command {
    private List<Product> commands;
    private String type ; // urgency || default
    private Date commandDate ; // Format en anglais

    public Command(List<Product> commands, String type, Date commandDate) {
        this.commands = commands;
        this.type = type;
        this.commandDate = commandDate;
    }

    public boolean validateCommand() {
        return true;
    }

    public double calculateTotalPrice() {
        return 0;
    }

    public void setType(String type) {
        this.type = type;
    }
}
