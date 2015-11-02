package com.transitionbug;

import java.io.Serializable;

/**
 * Created by hat on 11/2/15.
 */
public class Item implements Serializable {
    private String name;
    private int imageId;

    public Item(String name, int imageId) {
        this.name = name;
        this.imageId = imageId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }
}
