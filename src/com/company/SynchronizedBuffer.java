/* Name: Youssef Al Hindi
 Course: CNT 4714 Spring 2016
 Assignment title: Project 2 – Synchronized, Cooperating Threads Under Locking
 Due Date: February 14, 2016
*/

package com.company;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.Condition;

public class SynchronizedBuffer implements Buffer {
    // Lock to control synchronization with this buffer
    private Lock accessLock = new ReentrantLock();

    // conditions to control reading and writing
    private Condition canWrite = accessLock.newCondition();
    private Condition canRead = accessLock.newCondition();

    private int buffer = -1; // shared by producer and consumer threads
    private boolean debug = false;

    public SynchronizedBuffer(boolean debug) {
        this.debug = debug;
    }

    // place int value into buffer
    public void set(int value) {
        accessLock.lock(); // lock this object

        buffer = value; // set new buffer value

        if (debug) {
            displayState("Producer writes " + buffer);
        }

        // signal thread waiting to read from buffer
        canRead.signal();
        accessLock.unlock(); // unlock this object

    } // end method set

    // return value from buffer
    public int get() {
        int readValue = 0; // initialize value read from buffer

        accessLock.lock(); // lock this object
        readValue = buffer; // retrieve value from buffer

        if (debug) {
            displayState("Consumer reads " + readValue);
        }

        // signal thread waiting for buffer to be empty
        canWrite.signal();
        accessLock.unlock(); // unlock this object
        return readValue;
    } // end method get


    // display current operation and buffer state
    public void displayState(String operation) {
        System.out.printf("%-40s%d\t\t\t\t\n", operation, buffer);
    } // end method displayState
}
