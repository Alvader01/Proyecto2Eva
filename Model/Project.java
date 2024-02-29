package Model;

import java.util.ArrayList;

public class Project {
    private String name;
    private String description;
    private String projectCreator;
    private ArrayList<Task> tasks;

    public Project(String name, String description, String projectCreator, ArrayList<Task> tasks) {
        this.name = name;
        this.description = description;
        this.projectCreator = projectCreator;
        this.tasks = tasks;
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

    public String getProjectCreator() {
        return projectCreator;
    }

    public void setProjectCreator(String projectCreator) {
        this.projectCreator = projectCreator;
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public void setTasks(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }
}
