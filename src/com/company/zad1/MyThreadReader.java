package com.company.zad1;

import java.io.*;

public class MyThreadReader extends Thread {


    Lock lock;
    String fileName;

    public MyThreadReader( String fileName, Lock lock) {
        this.fileName = fileName;
        this.lock = lock;

   lock.register(getId());

    }


    private String readFile() throws IOException {
        BufferedReader input = new BufferedReader(new FileReader(fileName));
        String last="", line;
        while ((line = input.readLine()) != null) {
            last = line;
        }
        return last;
    }


    public void run() {

        try {

            while (true) {
                lock.startRead(getId());
                sleep((int) (Math.random() * 100));
                System.out.println("Thread: " + getId() + " read: " + readFile());
                sleep((int) (Math.random() * 100));
                lock.finishRead(getId());
            }

        } catch (InterruptedException | IOException e) {

        }
    }


}
