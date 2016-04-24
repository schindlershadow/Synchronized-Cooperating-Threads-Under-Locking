/* Name: Youssef Al Hindi
 Course: CNT 4714 Spring 2016
 Assignment title: Project 2 – Synchronized, Cooperating Threads Under Locking
 Due Date: February 14, 2016
*/

package com.company;

import java.util.Random;

public class Producer implements Runnable {
    private static Random generator = new Random();
    private SynchronizedBuffer sharedLocation; // reference to shared object
    private String Name = "";

    // constructor
    public Producer(SynchronizedBuffer shared, String Name) {
        sharedLocation = shared;
        this.Name = Name;
    } // end Producer constructor

    public void run() {

        //System.out.println(Name  + " start");

        while (true) {
            try {
                Thread.sleep(generator.nextInt(30));
                int amount = generator.nextInt(201);
                int current = sharedLocation.get();
                sharedLocation.set(current + amount);
                System.out.format("%-32s%-32s%-32s%n", Name + " deposits $" + amount, "", "Balance is $" + (current + amount));

            } catch (InterruptedException exception) {
                exception.printStackTrace();
            }
        }
    }
}
