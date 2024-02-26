package Model.Repo;
import Model.Task;

import java.util.ArrayList;
import java.util.List;

public class Repo {
    private List<Task> tasks;

    public Repo() {
        this.tasks = new ArrayList<>();
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public void changeTaskStatus(Task task, String newState) {
        // Lógica para validar o procesar el cambio de estado si es necesario
        task.setState(newState);
    }
}
