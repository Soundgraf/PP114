package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    public static void main(String[] args) {
        UserService userService = new UserServiceImpl();
        userService.createUsersTable();

        userService.saveUser("САПАРМУРАТ", "НИЯЗОВ", (byte) 99);
        userService.saveUser("ГУРБАНГУЛЫ", "БЕРДЫМУХАММЕДОВ", (byte) 65);
        userService.saveUser("БОРИС", "ЕЛЬЦИН", (byte) 87);
        userService.saveUser("ДМИТРИЙ", "МЕДВЕДЕВ", (byte) 75);

        userService.getAllUsers();
        userService.cleanUsersTable();
        userService.dropUsersTable();
    }
}
