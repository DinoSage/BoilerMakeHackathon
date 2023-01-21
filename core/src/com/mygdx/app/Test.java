package com.mygdx.app;

import java.util.ArrayList;
import java.util.Scanner;


public class Test {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int totalHours = 0;
        ArrayList<Task> tasks1 = new ArrayList<Task>();
        User user1 = new User(0, 0, 0, tasks1, 0);

        while (!scanner.nextLine().equals("pause")) {
            user1.elapsedTimeHours();
        }
        System.out.println(user1.elapsedTimeHours());

        ArrayList<Task> tasks2 = new ArrayList<Task>();
        User user2 = new User(0, 0, 0, tasks2, 0);

        ArrayList<Task> tasks3 = new ArrayList<Task>();
        User user3 = new User(0, 0, 0, tasks3, 0);

        ArrayList<Task> tasks4 = new ArrayList<Task>();
        User user4 = new User(0, 0, 0, tasks4, 0);

    }




}
