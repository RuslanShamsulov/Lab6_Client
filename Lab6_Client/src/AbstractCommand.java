import java.io.Serializable;

public class AbstractCommand<T> implements Serializable  {

    private final String commandName;
    private T argument;


    public AbstractCommand(String commandName, T argument) {
        this.commandName = commandName;
        this.argument = argument;
    }

    public String getCommandName() {
        return commandName;
    }

    public T getArgument() {
        return argument;
    }

    public void setArgument(T argument) {
        this.argument = argument;
    }
}
