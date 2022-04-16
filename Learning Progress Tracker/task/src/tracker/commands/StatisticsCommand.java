package tracker.commands;

import tracker.Course;
import tracker.CourseType;
import tracker.Student;
import tracker.Tracker;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


import static tracker.ConsoleHelper.*;

public class StatisticsCommand extends Command {
    public StatisticsCommand(Tracker tracker) {
        super(tracker);
    }

    @Override
    public boolean execute() {
        print(STAT_PROMPT);
        generalStats();
        while (true) {
            String input = readString();
            if (input.equals("back")) {
                break;
            }
            switch (input.toLowerCase()) {
                case "java":
                    courseStats(tracker.getCourseByType(CourseType.JAVA));
                    break;
                case "dsa":
                    courseStats(tracker.getCourseByType(CourseType.DSA));
                    break;
                case "databases":
                    courseStats(tracker.getCourseByType(CourseType.DB));
                    break;
                case "spring":
                    courseStats(tracker.getCourseByType(CourseType.SPRING));
                    break;
                default:
                    print("Unknown course.");
            }
        }
        return false;
    }

    private void courseStats(Course course) {
        CourseType type = course.getName();
        print(type.getName() + DETAILED_STAT_RESULT);
        List<Student> students = new ArrayList<>(course.getEnrolled().stream().toList());
        if (students.isEmpty()) return;
        Comparator<Student> comparator = Comparator.comparing((Student s) -> s.getCoursePoints(type))
                .reversed().thenComparing(Student::getId);

        students.sort(comparator);

        students
                .forEach(s -> print(s.getId() + "\t\t" +
                        s.getCoursePoints(type) + "\t\t" +
                        df.format(s.getCompletionPercent(type)) + "%"));

    }

    private void generalStats() {
        List<Course> courses = tracker.courses;
        courses.sort(Comparator.comparingInt(Course::numberOfEnrolled));
        int maxPop = courses.get(3).numberOfEnrolled();
        int minPop = courses.get(0).numberOfEnrolled();

        if (maxPop == 0) {
            System.out.printf(STAT_RESULT, "n/a", "n/a", "n/a",
                    "n/a", "n/a", "n/a");
            return;
        }
        List<Course> popular = courses.stream().filter(c -> c.numberOfEnrolled() == maxPop).toList();
        List<Course> unpopular = new ArrayList<>();
        if (maxPop != minPop) {
            unpopular = courses.stream().filter(c -> c.numberOfEnrolled() == minPop).toList();
        }

        courses.sort(Comparator.comparingInt(Course::getNumberOfCompletedTasks));
        int maxAct = courses.get(3).getNumberOfCompletedTasks();
        int minAct = courses.get(0).getNumberOfCompletedTasks();

        List<Course> mostActive = courses.stream().filter(c -> c.getNumberOfCompletedTasks() == maxAct).toList();
        List<Course> leastActive = new ArrayList<>();
        if (maxAct != minAct) {
            leastActive = courses.stream().filter(c -> c.getNumberOfCompletedTasks() == minAct).toList();
        }


        courses.sort(Comparator.comparingDouble(Course::getAverageGrade));
        double maxAv = courses.get(3).getAverageGrade();
        double minAv = courses.get(0).getAverageGrade();

        List<Course> hardest = new ArrayList<>();
        List<Course> easiest = courses.stream().filter(c -> c.getAverageGrade() == maxAv).toList();
        if (maxAv != minAv) {
            hardest = courses.stream().filter(c -> c.getAverageGrade() == minAv).toList();
        }

        System.out.printf(STAT_RESULT, printList(popular), printList(unpopular),
                printList(mostActive), printList(leastActive),
                printList(easiest), printList(hardest));
    }
}

