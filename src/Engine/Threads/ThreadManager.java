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
public class ThreadManager implements Runnable {

    private Thread MainThread;
    private ArrayList<GameThread> ManagingThreads;

    private long FrameTime = 0;
    private int LimitedFramerate = 60;

    public ThreadManager() {
        MainThread = new Thread(this);
        ManagingThreads = new ArrayList<>();
    }
    
    public ThreadManager(GameThread[] threads) {
        MainThread = new Thread(this);
        ManagingThreads = new ArrayList<>(Arrays.asList(threads));
    }
    
    public synchronized void AddThread(ThreadableObject[] objs){
        ManagingThreads.add(new GameThread(objs));
    }

    public synchronized void startThread() {
        for (GameThread t : ManagingThreads) {
            if(t.GetThreadState() != Thread.State.RUNNABLE)
                t.StartThread();
        }
        FrameTime = System.currentTimeMillis();
        MainThread.start();
    }

    @Override
    public void run() {
        long time;
        while (true) {
            time = System.currentTimeMillis();
            // Limit the ammount of frames per second of the main thread
            /*
            if(time - FrameTime >= ((double)1 / LimitedFramerate)*1000){
                //System.out.println((double)(time - FrameTime) / 1000);
                FrameTime = time;
                rollOutNotifications();
            }*/
            rollOutNotifications();
        }
    }

    private void rollOutNotifications() {
        for (GameThread t : ManagingThreads) {
            try {
                if(t.DeadThread()){
                    ManagingThreads.remove(t);
                    continue;
                }
                
                t.NotifyThread();
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }

}
