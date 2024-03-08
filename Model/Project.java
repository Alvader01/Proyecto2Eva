package Model;

import Interfaces.Model.IProject;

import java.util.*;

public class Project implements IProject {
    private String name;
    private String description;
    private String projectCreator;
    private List<User> collaborators;
    private ArrayList<Task> tasks;


    public Project() {
        this("","", "");
        this.tasks = new ArrayList<>();
        this.collaborators = new ArrayList<>();
    }

    public Project(String name, String description, String projectCreator) {
        this.name = name;
        this.description = description;
        this.projectCreator = projectCreator;
        this.tasks = new ArrayList<>();
        this.collaborators = new ArrayList<>();
    }

    public List<User> getCollaborators() {
        return collaborators;
    }

    public void setCollaborators(List<User> collaborators) {
        this.collaborators = collaborators;
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

    @Override
    public Task addTask(Task task){
        tasks.add(task);
        return task;
    }

    @Override
    public List<Task> getAll() {
        return tasks;
    }

    public Task update(Task data) {
        Task result = getById(data.getName());
        if (result != null) {
            tasks.remove(result);
            tasks.add(data);
            result = data;
        }
        return result;
    }


    @Override
    public boolean deleteTask(String taskName){
        boolean taskDeleted = false;
        for (Task task : tasks) {
            if (task.getName().equals(taskName)) {
                tasks.remove(task);
                taskDeleted = true;
            }
        }
        return taskDeleted;
    }

    @Override
    public Task getById(String nameTask) {
        Task foundTask = null;
        for (Task task : tasks) {
            if (task.getName().equals(nameTask)) {
                foundTask = task;
            }
        }
        return foundTask;
    }

    @Override
    public void changeTaskStatus(String nameTask, TaskState state){
        for (Task task : tasks) {
            if (task.getName().equals(nameTask)) {
                task.setState(state);
            }
        }
    }

    @Override
    public void addComment(String nameTask, String comment){
        for (Task task : tasks) {
            if (task.getName().equals(nameTask)) {
                task.getComments().add(comment);
            }
        }
    }

    /**
     * Con este metodo añadimos usuarios a las tareas
     * @param task Tarea
     * @param username Nombre de usuario
     */
    @Override
    public boolean updateAssignedUser(Task task, String username) {
        boolean assignedUser = false;
        for (User user : collaborators) {
            if (user.getUsername().equals(username)) {
                task.setAssignedUser(user.getUsername());
                assignedUser = true;
            }
        }
        return assignedUser;
    }

    @Override
    public String toString() {
        return "Project{" +
                "Nombre del Proyecto='" + name + '\'' +
                ", Descripción del Proyecto='" + description + '\'' +
                ", Creador del Proyecto='" + projectCreator + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Project project = (Project) o;
        return Objects.equals(name, project.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
