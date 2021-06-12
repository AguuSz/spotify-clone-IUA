package main.java.models;

import main.java.utils.Formatter;

import java.util.Date;
import java.util.Objects;

public class Activity {
    private int userId;
    private int idContent;
    private String date;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getIdContent() {
        return idContent;
    }

    public void setIdContent(int idContent) {
        this.idContent = idContent;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setDate(Date date) {
        this.date = Formatter.formatDate(date);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Activity activity = (Activity) o;
        return userId == activity.userId && idContent == activity.idContent && Objects.equals(date, activity.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, idContent, date);
    }

    @Override
    public String toString() {
        return "Activity{" +
                "userId=" + userId +
                ", idContent=" + idContent +
                ", date='" + date + '\'' +
                '}';
    }
}
