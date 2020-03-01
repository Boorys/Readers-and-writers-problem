package com.company.zad1;


import java.util.concurrent.Semaphore;

public class Lock extends Thread {


    private Semaphore readSemaphore = new Semaphore(0);
    private Semaphore writeSemaphore = new Semaphore(1);
   private int numberReadersInQueue;
    private int numberReadres = 0;

public Lock(int numberReadersInQueue)
{
    this.numberReadersInQueue=numberReadersInQueue;
}

    public void startRead() throws InterruptedException {

        readSemaphore.acquire(1);

    }

    public synchronized void finishRead() throws InterruptedException {

 numberReadres++;

        if(numberReadres==numberReadersInQueue) {
         numberReadres=0;
            writeSemaphore.release(1);
        }

    }

    public void startWrite() throws InterruptedException {
        writeSemaphore.acquire();
    }

    public void finishWrite() throws InterruptedException {
        readSemaphore.release(numberReadersInQueue);
    }


}
