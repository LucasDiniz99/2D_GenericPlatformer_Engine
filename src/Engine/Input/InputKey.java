/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Engine.Input;

/**
 *
 * @author Notebook
 */
public class InputKey {
    public int keyCode = -1;
    public boolean held = false;
    
    public InputKey(int keyCode, boolean held){
        this.keyCode = keyCode;
        this.held = held;
    }
    
    @Override
    public String toString(){
        return "Code: " + keyCode + " Held?: " + held;
    }
}
