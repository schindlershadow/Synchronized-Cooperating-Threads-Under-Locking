/* Name: Youssef Al Hindi
 Course: CNT 4714 Spring 2016
 Assignment title: Project 2 – Synchronized, Cooperating Threads Under Locking
 Due Date: February 14, 2016
*/

package com.company;

import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;


public class Main {

    public static void main(String[] args) {

        boolean debugMode = false;

        ExecutorService application = Executors.newFixedThreadPool(9);
        SynchronizedBuffer Bank = new SynchronizedBuffer(debugMode);

        Bank.set(0);

        Producer producer1 = new Producer(Bank, "Thread 1");
        Producer producer2 = new Producer(Bank, "Thread 2");
        Producer producer3 = new Producer(Bank, "Thread 3");

        Consumer consumer1 = new Consumer(Bank, "Thread 1");
        Consumer consumer2 = new Consumer(Bank, "Thread 2");
        Consumer consumer3 = new Consumer(Bank, "Thread 3");
        Consumer consumer4 = new Consumer(Bank, "Thread 4");
        Consumer consumer5 = new Consumer(Bank, "Thread 5");
        Consumer consumer6 = new Consumer(Bank, "Thread 6");

        String line = "-------------------------------";

        System.out.format("%-32s%-32s%-32s%n", "Deposit Threads", "Withdrawal Threads", "Balance");
        System.out.format("%-32s%-32s%-32s%n", line, line, line);

        try // try to start producer and consumer
        {
            application.execute(producer1);
            application.execute(producer2);
            application.execute(producer3);

            application.execute(consumer1);
            application.execute(consumer2);
            application.execute(consumer3);
            application.execute(consumer4);
            application.execute(consumer5);
            application.execute(consumer6);
        } // end try
        catch (Exception exception) {
            exception.printStackTrace();
        } // end catch

        application.shutdown();
    }
}