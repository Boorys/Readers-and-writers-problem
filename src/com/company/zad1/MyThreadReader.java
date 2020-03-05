package com.company.zad1;

import java.io.*;

public class MyThreadReader extends Thread {


    private Lock lock;
    private String fileName;
    private static int index = -1;
    private int id = 0;

    public MyThreadReader(String fileName, Lock lock) {
        this.fileName = fileName;
        this.lock = lock;
        index++;
        id=index;
        lock.register();

    }


    private String readFile() throws IOException {
        BufferedReader input = new BufferedReader(new FileReader(fileName));
        String last = "", line;
        while ((line = input.readLine()) != null) {
            last = line;
        }
        return last;
    }

    public void run() {

        try {

            while (true) {
                sleep((int) (Math.random() * 10000));
                lock.startRead(id);
                sleep((int) (Math.random() * 100));
                System.out.println("Thread: " + getId() + " read: " + readFile());
                sleep((int) (Math.random() * 100));
                lock.finishRead();
            }

        } catch (InterruptedException | IOException e) {

        }
    }


}
