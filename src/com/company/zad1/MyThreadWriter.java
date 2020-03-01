package com.company.zad1;


import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class MyThreadWriter extends Thread {

    private Lock lock;
    private String fileName;
    static Integer idMessage=0;

    public MyThreadWriter(String fileName, Lock czytelnia) {
        this.fileName = fileName;
        this.lock = czytelnia;
    }


    private void saveFile() throws IOException {

        FileWriter plikWy = new FileWriter(fileName, true);
        synchronized (idMessage) {
            plikWy.write("Message from : " + getId() + " writter, mesage id: " + ++idMessage + "\n");
        }
        BufferedWriter bw = new BufferedWriter(plikWy);
        bw.newLine();
        plikWy.close();

    }

    public void run() {
        try {
            while (true) {
                lock.startWrite();
                sleep((int) (Math.random() * 2500));
                System.out.println("Thread: " + getId() + "writer");
                saveFile();
                sleep((int) (Math.random() * 2500));
                lock.finishWrite();

            }
        } catch (InterruptedException | IOException e) {
        }
    }
}
