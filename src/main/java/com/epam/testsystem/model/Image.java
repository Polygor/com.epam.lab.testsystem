package com.epam.testsystem.model;

import javax.persistence.Entity;
import javax.persistence.Lob;

@Entity
public class Image extends BaseEntity {
    private String caption;
// TODO NOT USED IN PROJECT, ADD SUPPORT OR DELETE
    @Lob
    private byte[] image;

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }
}
