package tracker.commands;


import tracker.Tracker;

public abstract class Command {
    public Tracker tracker;
    public Command(Tracker tracker) {
        this.tracker = tracker;
    }

    public abstract boolean execute();
}
