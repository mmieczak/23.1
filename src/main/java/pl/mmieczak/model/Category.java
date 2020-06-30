package pl.mmieczak.model;

public enum Category {
    GROCERIES("spozywcze","Artykuły spożywcze"),
    HOUSEHOLD_ITEMS("artgospdomowego","Artykuły gospodarstwa domowego"),
    OTHER("inne","Inne produkty");

    private String name;
    private String description;

    private Category(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public static Category getCategoryUsingDescription(String description) {
        for (Category category : values()) {
            if (category.description.equals(description)) {
                return category;
            }
        }
        throw new IllegalArgumentException(description);
    }

}
