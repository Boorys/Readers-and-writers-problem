package com.company.zad1;

import java.util.concurrent.Semaphore;

public class SetLock {

private long id;
private Semaphore semaphore;

    public SetLock( Semaphore semaphore) {
        this.id = id;
        this.semaphore = semaphore;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Semaphore getSemaphore() {
        return semaphore;
    }

    public void setSemaphore(Semaphore semaphore) {
        this.semaphore = semaphore;
    }
}
