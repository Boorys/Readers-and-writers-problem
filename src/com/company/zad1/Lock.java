package com.company.zad1;

import java.util.*;
import java.util.concurrent.Semaphore;


public class Lock extends Thread {


    private Semaphore readSemaphore = new Semaphore(0);
    private Semaphore writeSemaphore = new Semaphore(1);
    private int numberReadersInQueue;
    private int numberReadres = 0;

    private  List<SetLock> set = Collections.synchronizedList(new ArrayList<>());


    public Lock(int numberReadersInQueue)
    {
        this.numberReadersInQueue = numberReadersInQueue;
    }

    public void register()
    {
        set.add(new SetLock(new Semaphore(1)));
    }

    public void startRead(int id) throws InterruptedException {


        set.get(id).getSemaphore().acquire();

        readSemaphore.acquire(1);

    }


    public synchronized void finishRead() throws InterruptedException {

        numberReadres++;

        if (numberReadres == numberReadersInQueue) {

            numberReadres = 0;

            writeSemaphore.release(1);
            for(SetLock setLock : set)
            {
                setLock.getSemaphore().release();
            }
        }
    }

    public void startWrite() throws InterruptedException {

        writeSemaphore.acquire();

    }

    public void finishWrite() throws InterruptedException {

        readSemaphore.release(numberReadersInQueue);
    }


}
