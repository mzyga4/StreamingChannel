package aa.mzyga4.aastreamingchannel.model;

public class Program {
    private final String title;
    private final String prefix;
    private final int lastElementIndex;
    private final String packageName;
    private final String activityName;

    public Program(String title, String prefix, int lastElementIndex,
                   String packageName, String activityName) {
        this.title = title;
        this.prefix = prefix;
        this.lastElementIndex = lastElementIndex;
        this.packageName = packageName;
        this.activityName = activityName;
    }

    public String getTitle() {
        return title;
    }

    public String getPrefix() {
        return prefix;
    }

    public int getLastElementIndex() {
        return lastElementIndex;
    }

    public String getPackageName() {
        return packageName;
    }

    public String getActivityName() {
        return activityName;
    }
}
