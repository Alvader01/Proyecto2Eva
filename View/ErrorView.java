package View;

import Interfaces.View.IError;

public class ErrorView implements IError {

    @Override
    public void userDontExist() {
        System.out.println("El usuario introducido no existe.");
    }

    @Override
    public void userAlreadyExist(){
        System.out.println("El usuario con el que te quieres registrar ya existe.");
    }

    @Override
    public void emailAlreadyExist(){
        System.out.println("El email introducido ya existe, por lo tanto no te puedes registrar con el mismo email.");
    }

    @Override
    public void projectDontExist(){
        System.out.println("El proyecto que estas buscando no existe.");
    }

    @Override
    public void taskDontExist(){
        System.out.println("La tarea que estas buscando no existe.");
    }
}
