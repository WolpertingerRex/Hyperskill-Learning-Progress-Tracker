package tracker;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class ConsoleHelper {
    public static final Scanner scanner = new Scanner(System.in);
    public static final String GREETING = "Learning Progress Tracker";
    public static final String EXIT = "Bye!";
    public static final String EMPTY = "No input";
    public static final String UNKNOWN = "Unknown command!";
    public static final String HINT = "Enter 'exit' to exit the program.";
    public static final String ENTER_CRED = "Enter student credentials or 'back' to return:";
    public static final String ADD_SUCCESS = "The student has been added.";
    public static final String ADD_FAIL = "Incorrect credentials.";
    public static final String ADD_TOTAL = "Total %d students have been added.\n";
    public static final String FIRSTNAME_FAIL = "Incorrect first name.";
    public static final String LASTNAME_FAIL = "Incorrect last name.";
    public static final String EMAIL_FAIL = "Incorrect email.";
    public static final String EMAIL_EXIST = "This email is already taken.";
    public static final String ADD_POINTS = "Enter an id and points or 'back' to return:";
    public static final String ADD_POINTS_SUCCESS = "Points updated.";
    public static final String ADD_POINTS_FAIL = "Incorrect points format.";
    public static final String FIND = "Enter an id or 'back' to return:";
    public static final String FIND_FAIL = "No student is found for id=";
    public static final String LIST = "Students:";
    public static final String LIST_FAIL = "No students found";
    public static final String STAT_PROMPT = "Type the name of a course to see details or 'back' to quit:";
    public static final String STAT_RESULT = "Most popular: %s\n" +
            "Least popular: %s\n" +
            "Highest activity: %s\n" +
            "Lowest activity: %s\n" +
            "Easiest course: %s\n" +
            "Hardest course: %s\n";
    public static final String DETAILED_STAT_RESULT = "\nid    points    completed";
    public static final DecimalFormat df;

    static{
        df = (DecimalFormat) NumberFormat.getNumberInstance(Locale.ENGLISH);
        df.setRoundingMode(RoundingMode.HALF_UP);
        df.applyPattern("#0.0");
    }

    public static void print(String message){
        System.out.println(message);
    }

    public static void print(String message, int n){
        System.out.printf(message, n);
    }

    public static String printList(List<?> list) {
        if (list.isEmpty()) return "n/a";
        StringBuilder sb = new StringBuilder();
        for (Object o : list){
            sb.append(o.toString()).append(", ");
        }
       return sb.substring(0, sb.length()-2);
    }

    public static Operation getInput(){
        String in = scanner.nextLine();

        if(in.isEmpty() || in.matches("\\s+")) {
            print(EMPTY);
            return Operation.ERROR;
        }
        switch (in){
            case "exit": return Operation.EXIT;
            case "back": return Operation.BACK;
            case "add students": return Operation.ADD_STUDENT;
            case "list": return Operation.LIST;
            case "add points": return Operation.ADD_POINTS;
            case "find": return Operation.FIND;
            case "statistics": return Operation.STATISTICS;
            case "notify":return Operation.NOTIFY;
            default: print(UNKNOWN);
                return Operation.ERROR;
        }
    }
    public static String readString(){
        return scanner.nextLine();
    }

}
