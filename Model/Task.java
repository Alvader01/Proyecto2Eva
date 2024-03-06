package Model;

import Interfaces.Model.ITask;
import Model.Repos.RepoUser;
import Model.User;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Task implements ITask {
    private String name;
    private String description;
    private LocalDate startDate;
    private LocalDate endDate;
    private String assignedUser;
    private TaskState state;
    private List<String> comments;


    public Task(String name, String description, LocalDate startDate, LocalDate endDate, String assignedUser, TaskState state, List<String> comments) {
        this.name = name;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
        this.assignedUser = assignedUser;
        this.state = state;
        this.comments = comments;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public String getAssignedUser() {
        return assignedUser;
    }

    public void setAssignedUser(String assignedUser) {
        this.assignedUser = assignedUser;
    }

    public TaskState getState() {
        return state;
    }

    public void setState(TaskState state) {
        this.state = state;
    }

    public List<String> getComments() {
        return comments;
    }

    public void setComments(List<String> comments) {
        this.comments = comments;
    }
}




