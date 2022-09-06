package com.example.prm391x_project_1_quangntnfx05544;

public class Animal {
    private final int id;
    private final int name;
    private final int backgroundImage;
    private final int description;
    private final int favorite;
    private boolean isFavorite;

    public Animal(int name, int id, int backgroundImage, int description, int favorite, boolean isFavorite) {
        this.name = name;
        this.id = id;
        this.backgroundImage = backgroundImage;
        this.description = description;
        this.favorite = favorite;
        this.isFavorite = isFavorite;
    }

    public int getId() {
        return id;
    }

    public int getName() {
        return name;
    }

    public int getBackgroundImage() {
        return backgroundImage;
    }

    public int getDescription() {
        return description;
    }

    public int getFavorite() {
        return favorite;
    }

    public boolean isFavorite() {
        return isFavorite;
    }

    public void setFavorite(boolean favorite) {
        isFavorite = favorite;
    }
}
