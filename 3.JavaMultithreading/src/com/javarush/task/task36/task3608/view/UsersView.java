package com.javarush.task.task36.task3608.view;

import com.javarush.task.task36.task3608.bean.User;
import com.javarush.task.task36.task3608.controller.Controller;
import com.javarush.task.task36.task3608.model.ModelData;

public class UsersView implements View {
    private Controller controller;

    public void fireEventOpenUserEditForm(long id) {
        controller.onOpenUserEditForm(id);
    }

    @Override
    public void refresh(ModelData modelData) {
        if (modelData.isDisplayDeletedUserList()) {
            System.out.println("All deleted users:");
            for (User u : modelData.getUsers()) System.out.println("\t" + u);
        }
        if (!modelData.isDisplayDeletedUserList()) {
            System.out.println("All users:");
            for (User u : modelData.getUsers()) System.out.println("\t" + u);
        }
        System.out.println("===================================================");
    }

    public void fireEventShowDeletedUsers() {
        controller.onShowAllDeletedUsers();
    }

    @Override
    public void setController(Controller controller) {
        this.controller = controller;
    }


    public void fireEventShowAllUsers() {
        controller.onShowAllUsers();
    }
}
