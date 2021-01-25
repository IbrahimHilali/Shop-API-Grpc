package de.home.collections;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import de.home.entities.User;

@XmlRootElement(name = "Users")
public class Users {


    @XmlElementWrapper(name = "userList")
    @XmlElement(name = "User")
    private ArrayList<User> userList;

    public Users() {
    }

    public ArrayList<User> getuserList() {
        return userList;
    }

    public void setUserList(ArrayList<User> userList) {
        this.userList = userList;
    }


    public User search(String name) {
        for (User User : userList) {
            if (User.getName() == name) {
                return User;
            }
        }
        return null;
    }

    public User find(Integer id) {
        for (User User : userList) {
            if (User.getId().equals(id)) {
                return User;
            }
        }
        return null;

    }

    public Users add(User User) {
        userList.add(User);
        return this;
    }

    public JsonObject toJsonObject() {
        Gson gson = new Gson();

        return gson.toJsonTree(this.getuserList()).getAsJsonObject();
    }

    @Override
    public String toString() {
        return (new Gson()).toJson(this.getuserList());
    }
}
