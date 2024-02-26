package Model;

import java.time.LocalDate;

public class Task {
    private String name;
    private String description;
    private LocalDate startDate;
    private LocalDate endDate;
    private String collaboratorInCharge;
    private String state;


    public Task(String name, String description, LocalDate startDate, LocalDate endDate, String collaboratorInCharge, String state) {
        this.name = name;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
        this.collaboratorInCharge = collaboratorInCharge;
        this.state = state;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
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

    public String getCollaboratorInCharge() {
        return collaboratorInCharge;
    }

    public void setCollaboratorInCharge(String collaboratorInCharge) {
        this.collaboratorInCharge = collaboratorInCharge;
    }

    @Override
    public String toString() {
        return "Task{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", collaboratorInCharge='" + collaboratorInCharge + '\'' +
                ", state='" + state + '\'' +
                '}';
    }

}



