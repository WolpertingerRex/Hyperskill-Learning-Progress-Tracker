package tracker.commands;


import tracker.Student;
import tracker.Tracker;

import static tracker.ConsoleHelper.*;
import static tracker.Formatter.*;

public class AddStudentCommand extends Command {

    public AddStudentCommand(Tracker tracker) {
        super(tracker);
    }

    @Override
    public boolean execute() {
        int count = 0;

        print(ENTER_CRED);
        while (true) {
            String[] input = readString().split(" ");
            if (input[0].equals("back")) {
                break;
            }

            if (input.length < 3) {
                print(ADD_FAIL);
            } else {
                String firstName = input[0];
                String email = input[input.length - 1];
                String lastName = "";
                for (int i = 1; i < input.length - 1; i++) {
                    lastName += input[i] + " ";
                }
                lastName = lastName.trim();

                if (firstName.length() < 2 || !isValid(firstName, NAME_PATTERN)) {
                    print(FIRSTNAME_FAIL);
                } else if (lastName.length() < 2 || !isValid(lastName, NAME_PATTERN)) {
                    print(LASTNAME_FAIL);
                } else if (!isValid(email, EMAIL_PATTERN)) {
                    print(EMAIL_FAIL);
                } else if (isTaken(email)) {
                    print(EMAIL_EXIST);
                } else {
                    int id = 0;
                    while (tracker.students.containsKey(id)) {
                        id++;
                    }
                    Student student = new Student(firstName, lastName, email, id);
                    student.setTracker(tracker);
                    tracker.students.put(id, student);

                    print(ADD_SUCCESS);
                    count++;
                }
            }
        }
        print(ADD_TOTAL, count);
        return false;
    }

    private boolean isTaken(String email) {
        for (Student s : tracker.students.values()) {
            if (s.getEmail().equals(email)) return true;
        }
        return false;
    }
}
