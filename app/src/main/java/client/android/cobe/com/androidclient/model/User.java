package client.android.cobe.com.androidclient.model;

import com.google.gson.GsonBuilder;

public class User {

    private String name;
    private String age;
    private String gender;

    public String getName() {
        return name;
    }

    public User setName(String name) {
        this.name = name;
        return this;
    }

    public String getAge() {
        return age;
    }

    public User setAge(String age) {
        this.age = age;
        return this;
    }

    public String getGender() {
        return gender;
    }

    public User setGender(String gender) {
        this.gender = gender;
        return this;
    }

    @Override
    public String toString() {
        return new GsonBuilder().create().toJson(this, User.class);
    }
}
