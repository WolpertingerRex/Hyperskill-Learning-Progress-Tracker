package tracker;


public enum CourseType {
    JAVA("Java", 600),
    DSA("DSA", 400),
    DB("Databases", 480),
    SPRING("Spring", 550);

    private final String name;
    private final int value;

    CourseType(String name, int value) {
        this.name = name;
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }
}
