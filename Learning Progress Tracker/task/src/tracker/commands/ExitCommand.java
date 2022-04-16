package tracker.commands;
import tracker.Tracker;

import static tracker.ConsoleHelper.*;

public class ExitCommand extends Command{
    public ExitCommand(Tracker tracker) {
        super(tracker);
    }

    @Override
    public boolean execute() {
        print(EXIT);
        System.exit(0);
        return true;
    }
}
