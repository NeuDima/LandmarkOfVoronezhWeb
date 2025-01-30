package landmarkOfVoronezhWeb.database.entity;

public class StatisticsEntity {
    private final String name;
    private final int count;

    public StatisticsEntity(String name, int count) {
        this.name = name;
        this.count = count;
    }

    public String getName() {
        return name;
    }

    public int getCount() {
        return count;
    }
}