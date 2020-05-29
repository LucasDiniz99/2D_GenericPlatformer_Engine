/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gradius_1;

import Engine.Graphic.GraphicalDisplay;
import Engine.Input.InputManager;
import Engine.Threads.GameThread;
import Engine.Threads.ThreadManager;
import Engine.Threads.ThreadableObject;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Iterator;
import javafx.scene.input.KeyCode;
import javax.swing.*;

/**
 *
 * @author Notebook
 */
public class Main implements ThreadableObject {

    
    public Main(){
        StartUI();
        TestingStuff();
    }
    
    public static void main(String[] args) throws Exception {
        new Main();
    }
    
    private void StartUI(){
        /*
        JFrame jframe = new JFrame();
        InputManager im = new InputManager();
        
        jframe.addKeyListener(im);
        jframe.setSize(400, 350);
        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jframe.setVisible(true);*/
        
        new GraphicalDisplay();
    }
    
    private void TestingStuff(){
        ThreadManager tm = new ThreadManager();
        tm.AddThread(new ThreadableObject[]{this});
        tm.startThread();
    }

    @Override
    public void runThread() {
        System.out.println("hello !");
    }

    @Override
    public boolean threadRunning() {
        return true;
    }

}
