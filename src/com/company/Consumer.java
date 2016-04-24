/* Name: Youssef Al Hindi
 Course: CNT 4714 Spring 2016
 Assignment title: Project 2 – Synchronized, Cooperating Threads Under Locking
 Due Date: February 14, 2016
*/

package com.company;

import java.util.Random;

public class Consumer implements Runnable {

    private static Random generator = new Random();
    private SynchronizedBuffer sharedLocation; // reference to shared object
    private String Name = "";

    public Consumer(SynchronizedBuffer shared, String Name) {
        sharedLocation = shared;
        this.Name = Name;
    } // end Consumer constructor

    public void run() {

        try {
            Thread.sleep(generator.nextInt(30));
        } catch (InterruptedException exception) {
            exception.printStackTrace();
        }


        while (true) {
            try {
                Thread.sleep(generator.nextInt(30));
                int amount = generator.nextInt(201);
                int current = sharedLocation.get();
                if (current - amount >= 0) {
                    sharedLocation.set(current - amount);
                    System.out.format("%-32s%-32s%-32s%n", "", Name + " withdraws $" + amount, "Balance is $"
                            + (current - amount));
                } else {
                    System.out.format("%-32s%-32s%-32s%n", "", Name + " withdraws $" + amount,
                            "Withdrawal - Blocked - Insufficient Funds");
                    Thread.sleep(generator.nextInt(10));
                }


            } catch (InterruptedException exception) {
                exception.printStackTrace();
            }
        }
    }
}
