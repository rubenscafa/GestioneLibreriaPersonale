package glp.command;
import java.util.*;

public class CommandManager {
	private Stack<Command> history = new Stack<>();

    public void eseguiCommand(Command cmd) {
        cmd.execute();
        history.push(cmd);
    }
}
