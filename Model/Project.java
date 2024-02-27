package Model;

import java.util.ArrayList;

public class Project {
    private String name;
    private String description;
    private String proyectCreator;
    private ArrayList<Task> tasks;

    public Project(String name, String description, String proyectCreator, ArrayList<Task> tasks) {
        this.name = name;
        this.description = description;
        this.proyectCreator = proyectCreator;
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

    public String getProyectCreator() {
        return proyectCreator;
    }

    public void setProyectCreator(String proyectCreator) {
        this.proyectCreator = proyectCreator;
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public void setTasks(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }
}
