/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Engine.Input;

import Engine.Threads.ThreadableObject;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author Notebook
 */
public class InputManager implements KeyListener {
    
    private ArrayList<InputKey> keyDown;
    
    private static final InputKey INVALID_KEY = (InputKey) null;
    
    public InputManager(){
        keyDown = new ArrayList<>();
    }
    
    // Searches for a key in the key pressed list
    public synchronized InputKey GetKeyHistory(int keyCode){
        if(keyDown.isEmpty())
            return INVALID_KEY;
        
        Iterator<InputKey> it = keyDown.iterator();
        while(it.hasNext()){
            InputKey key = it.next();
            if(key == null)
                break;
            if(key.keyCode == keyCode)
                return key;
        }
        
        return INVALID_KEY;
    }
    
    @Override
    public void keyPressed(KeyEvent e) {
        InputKey key = GetKeyHistory(e.getKeyCode());
        //System.out.println("code: " + e.getKeyCode() + " char:" + e.getKeyChar());
        if(key == INVALID_KEY)
            keyDown.add(new InputKey(e.getKeyCode(), false));
        else
            key.held = true;
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e){
        InputKey kDown = GetKeyHistory(e.getKeyCode());
        if(kDown == INVALID_KEY)
            System.out.println("Exception: released an unset key");
        else
            keyDown.remove(kDown);
    }
    
    // Returns if the key was pressed and not held 
    public synchronized boolean GetKeyDown(int code){
        InputKey key = GetKeyHistory(code);
        if(key == INVALID_KEY)
            return false;
        else {
            if(!key.held){
                key.held = true;
                return true;
            } else
            {
                return false;
            }
        }
    }
    
    // Returns if the key was either pressed or held
    public boolean GetKey(int code){
        InputKey key = GetKeyHistory(code);
        //System.out.println("code: " + code + " result: " +key);
        if(key == INVALID_KEY)
            return false;
        return true;
    }
}
