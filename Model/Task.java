package Model;

import Interfaces.Model.ITask;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Task implements ITask {
    private String name;
    private String description;
    private LocalDate startDate;
    private LocalDate endDate;
    private String collaboratorInCharge;
    private String state;
    private List<String> comments;


    public Task(String name, String description, LocalDate startDate, LocalDate endDate, String collaboratorInCharge, String state, List<String> comments) {
        this.name = name;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
        this.collaboratorInCharge = collaboratorInCharge;
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

    public String getCollaboratorInCharge() {
        return collaboratorInCharge;
    }

    public void setCollaboratorInCharge(String collaboratorInCharge) {
        this.collaboratorInCharge = collaboratorInCharge;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public List<String> getComments() {
        return comments;
    }

    public void setComments(List<String> comments) {
        this.comments = comments;
    }

    /**
     * Este metodo crea los comentarios de las tareas
     * @param comment
     */
    @Override
    public void createComment(Task task,String comment) {
        if (comments == null) {
            comments = new ArrayList<>();
        }
        comments.add(comment);
        //Añadir comentario a una tarea en especifico
    }



}




