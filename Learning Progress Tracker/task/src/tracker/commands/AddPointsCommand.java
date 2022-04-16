package tracker.commands;

import tracker.Student;
import tracker.Tracker;

import static tracker.ConsoleHelper.*;
import static tracker.Formatter.*;

public class AddPointsCommand extends Command {
    public AddPointsCommand(Tracker tracker) {
        super(tracker);
    }

    @Override
    public boolean execute() {
        print(ADD_POINTS);

        while (true) {
            String input = readString();
            if (input.split(" ")[0].equals("back")) {
                break;
            }
            String[] splitted = input.split(" ");
            if(!isValid(splitted[0], ID_PATTERN)) {
                print(FIND_FAIL + splitted[0]);
            }

            else if (!isValid(input, POINTS_PATTERN)) {
                print(ADD_POINTS_FAIL);
            } else {
                int[] points = new int[5];

                int i = 0;
                for (String s : input.split(" ")) {
                    points[i] = Integer.parseInt(s);
                    i++;
                }

                if (!tracker.isExist(points[0])) print(FIND_FAIL + points[0]);
                else {
                    Student student = tracker.students.get(points[0]);
                    student.updateCoursePoints(points);
                    print(ADD_POINTS_SUCCESS);
                }
            }
        }
        return false;
    }

}
