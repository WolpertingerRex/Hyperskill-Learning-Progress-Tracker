package tracker.commands;

import tracker.Tracker;

import static tracker.ConsoleHelper.*;
import static tracker.Formatter.*;

public class FindCommand extends Command {
    public FindCommand(Tracker tracker) {
        super(tracker);
    }

    @Override
    public boolean execute() {
        print(FIND);
        while (true) {
            String input = readString();
            if (input.equals("back")) {
                break;
            }
            if (!isValid(input, ID_PATTERN)) {
                print(FIND_FAIL + input.split(" ")[0]);
            } else {
                int id = Integer.parseInt(input);

                if (!tracker.isExist(id)) {
                    print(FIND_FAIL, id);
                } else {
                    tracker.students.get(id).printCoursePoints();
                }
            }
        }
        return false;
    }
}
