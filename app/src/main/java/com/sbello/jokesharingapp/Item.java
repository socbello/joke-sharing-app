package com.sbello.jokesharingapp;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;

import java.util.Date;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by socbe on 11/11/2017.
 */

@Entity
public class Item {
    @Id
    public Integer id;
    public String key;
    public Integer rating;
    public String audio;
    public String email;
    public String description;
    public Date date;
    @Generated(hash = 1069721730)
    public Item(Integer id, String key, Integer rating, String audio, String email,
            String description, Date date) {
        this.id = id;
        this.key = key;
        this.rating = rating;
        this.audio = audio;
        this.email = email;
        this.description = description;
        this.date = date;
    }
    @Generated(hash = 1470900980)
    public Item() {
    }
    public Integer getId() {
        return this.id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getKey() {
        return this.key;
    }
    public void setKey(String key) {
        this.key = key;
    }
    public Integer getRating() {
        return this.rating;
    }
    public void setRating(Integer rating) {
        this.rating = rating;
    }
    public String getAudio() {
        return this.audio;
    }
    public void setAudio(String audio) {
        this.audio = audio;
    }
    public String getEmail() {
        return this.email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getDescription() {
        return this.description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public Date getDate() {
        return this.date;
    }
    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Item{" +
                "id=" + id +
                ", key='" + key + '\'' +
                ", rating=" + rating +
                ", audio='" + audio + '\'' +
                ", email='" + email + '\'' +
                ", description='" + description + '\'' +
                ", date=" + date +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Item)
            return this.key.equals(((Item) obj).key);
        else
            return super.equals(obj);
    }
}
