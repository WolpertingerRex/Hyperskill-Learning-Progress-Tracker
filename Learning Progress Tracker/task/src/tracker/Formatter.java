package tracker;

public class Formatter {
    public static final String NAME_PATTERN = "^[a-zA-Z]+(([\\'\\,\\.\\- ][a-zA-Z ])?[a-zA-Z]*)*";
    public static final String EMAIL_PATTERN = "^[a-zA-Z0-9_\\-\\.]+@{1}\\w+\\.{1}\\w+";
    public static final String POINTS_PATTERN = "^(\\d+\\s){4}\\d+$";
    public static final String ID_PATTERN = "^\\d+$";


    public static boolean isValid(String input, String pattern){
       return input.matches(pattern);
   }

}
