package com.Calendar.Database;
import com.Calendar.User.User;
import com.Calendar.Events.Events;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

public class Database {
    private List<User> dataUser;
    private List<Events> dataEnvents;
    @JsonCreator
    public Database(
            @JsonProperty("dataUser") List<User> dataUser,
            @JsonProperty("dataEnvents") List<Events> dataEnvents) {
        this.dataUser = dataUser;
        this.dataEnvents = dataEnvents;
    }

    public List<User> getDataUser() {
        return dataUser;
    }

    public void setDataUser(List<User> dataUser) {
        this.dataUser = dataUser;
    }
    public void addUser(User user) {
        if (dataUser == null) {
            dataUser = new ArrayList<>();
        }
        this.dataUser.add(user);
    }

    public List<Events> getDataEnvents() {
        return dataEnvents;
    }

    public void setDataEnvents(List<Events> dataEnvents) {
        this.dataEnvents = dataEnvents;
    }

    @Override
    public String toString() {
        return "Database{" +
                "dataUser=" + dataUser +
                ", dataEnvents=" + dataEnvents +
                '}';
    }
}