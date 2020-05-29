/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Engine.Threads;

import java.util.*;

/**
 *
 * @author Notebook
 */
public class GameThread implements Runnable {

    private Thread MainThread;
    //Objects to call every iteration of this thread
    private ArrayList<ThreadableObject> Objects;

    private long startingTime;

    public GameThread(ThreadableObject[] objectsToManage) {
        MainThread = new Thread(this);
        startingTime = System.currentTimeMillis();
        Objects = new ArrayList<>(Arrays.asList(objectsToManage));
    }

    public synchronized void StartThread() {
        MainThread.start();
    }

    public synchronized void NotifyThread() throws Exception {
        this.notify();
    }

    public synchronized void SleepThread() {
        try {
            this.wait();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @Override
    public void run() {
        while (true) {
            //System.out.println("Thread: "+this.hashCode()+" time= "+((float)(System.currentTimeMillis() - startingTime) / 1000) + " s");
            if(DeadThread())
            {
                MainThread.stop();
                return;
            }
            
            for(ThreadableObject obj : Objects){
                if(!obj.threadRunning()){
                    RemoveManagingObject(obj);
                    continue;
                }
                obj.runThread();
            }
            
            SleepThread();
        }
    }
    
    public synchronized Thread.State GetThreadState(){
        return this.MainThread.getState();
    }
    
    public void AddManagingObject(ThreadableObject obj){
        Objects.add(obj);
    }

    public void RemoveManagingObject (ThreadableObject obj){
        Objects.remove(obj);
    }
    
    public boolean DeadThread(){
        if(Objects.size() == 0)
            return true;
        return false;
    }
}
