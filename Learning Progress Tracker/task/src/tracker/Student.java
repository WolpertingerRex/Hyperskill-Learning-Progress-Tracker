package tracker;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

public class Student {
    private final String firstName;
    private final String lastName;
    private final String email;
    private final int id;
    private final Map<CourseType, Integer> coursePoints;
    private Tracker tracker;

    public Student(String firstName, String lastName, String email, int id) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.id = id;
        coursePoints = new LinkedHashMap<>();
        coursePoints.put(CourseType.JAVA, 0);
        coursePoints.put(CourseType.DSA, 0);
        coursePoints.put(CourseType.DB, 0);
        coursePoints.put(CourseType.SPRING, 0);
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public int getId() {
        return id;
    }


    public int getCoursePoints(CourseType c) {
        return coursePoints.get(c);
    }

    public void updateCoursePoints(int[] points) {
        int i = 1;
        for (Map.Entry<CourseType, Integer> entry : coursePoints.entrySet()) {
            int p = entry.getValue() + points[i];
            entry.setValue(p);

            for (Course c : tracker.courses) {
                if (c.getName() == entry.getKey()) {
                    if (points[i] > 0) {
                        c.incrementNumberOfCompletedTasks();
                        c.addStudent(this);
                        c.addTotalPoints(points[i]);
                    }
                }
            }

            i++;
        }
    }

    public void printCoursePoints() {
        System.out.printf("%d points: Java=%d; DSA=%d; Databases=%d; Spring=%d", id,
                coursePoints.get(CourseType.JAVA), coursePoints.get(CourseType.DSA),
                coursePoints.get(CourseType.DB), coursePoints.get(CourseType.SPRING));
    }

    public double getCompletionPercent(CourseType course){
        return 100d*coursePoints.get(course)/course.getValue();
    }

    public boolean isGraduated(CourseType course){
        return coursePoints.get(course)==course.getValue();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return id == student.id && firstName.equals(student.firstName) && lastName.equals(student.lastName) && email.equals(student.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, email, id);
    }

    public void setTracker(Tracker tracker) {
        this.tracker = tracker;
    }
}
