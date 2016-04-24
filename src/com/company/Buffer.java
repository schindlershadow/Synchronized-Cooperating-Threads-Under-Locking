/* Name: Youssef Al Hindi
 Course: CNT 4714 Spring 2016
 Assignment title: Project 2 – Synchronized, Cooperating Threads Under Locking
 Due Date: February 14, 2016
*/

package com.company;

public interface Buffer {
    public void set(int value); // place int value into Buffer

    public int get(); // return int value from Buffer
}
