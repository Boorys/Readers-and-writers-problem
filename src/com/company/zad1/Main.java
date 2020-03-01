package com.company.zad1;


import java.util.List;

public class Main {
    public static void main(String[] args) throws InterruptedException {

        String fileName = "text.txt";
        Lock czytelnia = new Lock(4);
        List<Thread> readers = List.of(new MyThreadReader( fileName,czytelnia), new MyThreadReader( fileName,czytelnia), new MyThreadReader( fileName,czytelnia), new MyThreadReader( fileName,czytelnia));
        List<Thread> writers = List.of(new MyThreadWriter( fileName,czytelnia), new MyThreadWriter( fileName,czytelnia));
        writers.stream().forEach((t) -> t.start());
        readers.stream().forEach((t) -> t.start());
        Thread.sleep(100000);
        writers.stream().forEach(t -> t.interrupt());
        readers.stream().forEach(t -> t.interrupt());
    }
}