package entities;

import engine.math.Vector3f;
import engine.window.Window;

import static org.lwjgl.glfw.GLFW.*;

/**
 * Created by Admin on 22.01.2016.
 */
public class Camera {

    private Window window;

    private Vector3f position = new Vector3f(0, 0, 0);
    private float pitch;
    private float yaw;
    private float roll;

    public Camera(Window window){
        this.window=window;
    }

    public void move(){
        if(window.getKeyboard().isDown(GLFW_KEY_W)){
            position.z-=0.2f;
        }
        if(window.getKeyboard().isDown(GLFW_KEY_D)){
            position.x+=0.2f;
        }
        if(window.getKeyboard().isDown(GLFW_KEY_A)){
            position.x-=0.2f;
        }
        if(window.getKeyboard().isDown(GLFW_KEY_S)){
            position.z+=0.2f;
        }
        if(window.getKeyboard().isDown(GLFW_KEY_SPACE)){
            position.y+=0.2f;
        }
        if(window.getKeyboard().isDown(GLFW_KEY_LEFT_SHIFT)){
            position.y-=0.2f;
        }
    }

    public Vector3f getPosition() {
        return position;
    }

    public float getPitch() {
        return pitch;
    }

    public float getYaw() {
        return yaw;
    }

    public float getRoll() {
        return roll;
    }
}
