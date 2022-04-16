package tracker.commands;

import tracker.Tracker;
import static tracker.ConsoleHelper.*;

public class ListCommand extends Command{
    public ListCommand(Tracker tracker) {
        super(tracker);
    }

    @Override
    public boolean execute() {
        if(tracker.students.isEmpty()) {
            print(LIST_FAIL);
        }
        else {
            print(LIST);
            tracker.students
                    .values()
                    .forEach(s->print(String.valueOf(s.getId())));
        }
        return false;
    }
}
