package landmarkOfVoronezhWeb.database.entity;

public class LandmarkEntity {
    private final int id;
    private final String name;
    private final String description;
    private final String history;
    private final int countVisits;
    private final int category;

    public LandmarkEntity(int id, String name, String description, String history, int countVisits, int category) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.history = history;
        this.countVisits = countVisits;
        this.category = category;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getHistory() {
        return history;
    }

    public int getCountVisits() {
        return countVisits;
    }

    public int getCategory() {
        return category;
    }
}
