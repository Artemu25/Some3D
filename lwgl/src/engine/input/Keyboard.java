package engine.input;

/**
 * Created by Admin on 14.01.2016.
 */
import static org.lwjgl.glfw.GLFW.*;

import org.lwjgl.glfw.GLFWKeyCallback;

public class Keyboard extends GLFWKeyCallback{

    private int[] keyState = new int[GLFW_KEY_LAST];
    private int[] keyDown = new int[GLFW_KEY_LAST];

    public Keyboard(){
        for (int i=0;i<keyState.length;i++)
            keyState[i]=-1;
    }

    public void update(){
        for (int i=0;i<keyState.length;i++)
            keyState[i]=-1;

    }

    public boolean isDown(int key){
        if (key<=GLFW_KEY_LAST){
            return keyDown[key] == 1;
        }
        return  false;
    }

    public boolean isPressed(int key){
        if (key<=GLFW_KEY_LAST){
            return keyState[key] == 1;
        }
        return  false;
    }

    public boolean isReleased(int key){
        if (key<=GLFW_KEY_LAST){
            return keyState[key] == 0;
        }
        return  false;
    }

    @Override
    public void invoke(long window, int key, int scancode, int action, int mods) {
        if (key <= GLFW_KEY_LAST) {
            keyState[key] = action;
            keyDown[key] = action != GLFW_RELEASE ? 1 : 0;
        }
    }
}
