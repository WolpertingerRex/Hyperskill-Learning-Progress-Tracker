package tracker;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

public class Course {
    private final CourseType name;
    private int totalPoints;
    private final Set<Student> enrolled;
    private final Set<Student> graduated;
    private int numberOfCompletedTasks;

    public Course(CourseType name) {
        this.name = name;
        enrolled = new LinkedHashSet<>();
        graduated = new HashSet<>();
    }

    public CourseType getName() {
        return name;
    }

    public int getNumberOfCompletedTasks() {
        return numberOfCompletedTasks;
    }

    public void incrementNumberOfCompletedTasks(){
        numberOfCompletedTasks++;
    }

    public void addStudent(Student student){
        enrolled.add(student);
    }

    public Set<Student> getEnrolled() {
        return enrolled;
    }

    public int numberOfEnrolled(){
        return enrolled.size();
    }

    public void addGraduated(Student student){
        graduated.add(student);
    }

    public Set<Student> getGraduated() {
        return graduated;
    }

    @Override
    public String toString() {
        return name.toString();
    }

    public void addTotalPoints(int points){
        totalPoints+=points;
    }

    public double getAverageGrade(){
        return (double) totalPoints/numberOfCompletedTasks;
    }
}
