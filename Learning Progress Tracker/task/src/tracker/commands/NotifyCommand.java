package tracker.commands;

import tracker.Course;
import tracker.Student;
import tracker.Tracker;

import java.util.HashSet;
import java.util.Set;

public class NotifyCommand extends Command {

    public NotifyCommand(Tracker tracker) {
        super(tracker);
    }

    @Override
    public boolean execute() {
        Set<Student> receivers = new HashSet<>();
        for (Course course : tracker.courses) {
            for (Student s : tracker.students.values()) {
                if (s.isGraduated(course.getName()) && !course.getGraduated().contains(s)) {
                    course.addGraduated(s);
                    receivers.add(s);
                    System.out.printf("To: %s\n" + "Re: Your Learning Progress\n" +
                                    "Hello, %s! You have accomplished our %s course!\n", s.getEmail(),
                            s.getFirstName() + " " + s.getLastName(), course.getName());
                }
            }
        }
        System.out.printf("Total %d students have been notified.\n", receivers.size());
        return false;
    }
}
