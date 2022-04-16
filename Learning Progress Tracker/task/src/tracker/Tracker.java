package tracker;

import tracker.commands.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static tracker.ConsoleHelper.*;

public class Tracker {
    public Map<Integer, Student> students = new HashMap<>();
    public List<Course> courses = new ArrayList<>();

    public Tracker() {
        courses.add(new Course(CourseType.JAVA));
        courses.add(new Course(CourseType.DSA));
        courses.add(new Course(CourseType.DB));
        courses.add(new Course(CourseType.SPRING));
    }

    public void init(){
        print(GREETING);

        while(true) {
            Operation op = getInput();
            switch (op){
                case EXIT: executeCommand(new ExitCommand(this));
                break;
                case ADD_STUDENT: executeCommand(new AddStudentCommand(this));
                break;
                case ADD_POINTS: executeCommand(new AddPointsCommand(this));
                break;
                case FIND: executeCommand(new FindCommand(this));
                break;
                case LIST: executeCommand(new ListCommand(this));
                break;
                case BACK: ConsoleHelper.print(HINT);
                break;
                case STATISTICS: executeCommand(new StatisticsCommand(this));
                break;
                case NOTIFY: executeCommand(new NotifyCommand(this));
                break;

            }
        }
    }


    private void executeCommand(Command command) {
        command.execute();
    }

    public boolean isExist(int id) {
        for (Student s : students.values()) {
            if (s.getId() == id) return true;
        }
        return false;
    }

    public Course getCourseByType(CourseType type){
        return courses.stream()
                .filter(c-> c.getName()==type)
                .findFirst()
                .orElseThrow(()->new IllegalArgumentException("Course " + type + " doesn't exist"));
    }
}
