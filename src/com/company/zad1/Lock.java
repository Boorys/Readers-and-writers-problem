package com.company.zad1;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Semaphore;

public class Lock extends Thread {


    private Semaphore readSemaphore = new Semaphore(0);
    private Semaphore writeSemaphore = new Semaphore(1);
    private int numberReadersInQueue;
    private int numberReadres = 0;
    private Map<Long, Semaphore> mapOfReadres = new HashMap<>();


    public Lock(int numberReadersInQueue) {
        this.numberReadersInQueue = numberReadersInQueue;
    }


    public void register(long threadId) {
        synchronized (mapOfReadres) {
            mapOfReadres.put(threadId, new Semaphore(1));
        }
    }


    public void startRead(long id) throws InterruptedException {

        synchronized (mapOfReadres) {
            mapOfReadres.get(id).acquire();

        }
        readSemaphore.acquire(1);

    }

    public synchronized void finishRead(long id) throws InterruptedException {

        numberReadres++;

        if (numberReadres == numberReadersInQueue) {
            numberReadres = 0;
            writeSemaphore.release(1);
        }
        synchronized (mapOfReadres) {
            mapOfReadres.put(id, new Semaphore(1));
        }
    }

    public void startWrite() throws InterruptedException {

        writeSemaphore.acquire();

    }

    public void finishWrite() throws InterruptedException {

        readSemaphore.release(numberReadersInQueue);
    }


}
