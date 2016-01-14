package engine.window;

/**
 * Created by Admin on 14.01.2016.
 */

import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.opengl.GL;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryUtil.NULL;

public class Window {

    private String title;
    private int width, height;
    private boolean vsync, fullscreen, visible, resizable;

    private long windowID;

    public Window(String title, int width, int height, boolean vsync, boolean fuulsceen, boolean visible, boolean resizable)
    {
        this.title=title;
        this.width=width;
        this.height=height;
        this.vsync=vsync;
        this.fullscreen=fuulsceen;
        this.visible=visible;
        this.resizable=resizable;

        init();
    }

    public void init()
    {
        glfwInit();
        glfwDefaultWindowHints();;
        glfwWindowHint(GLFW_VISIBLE, visible? GL_TRUE : GL_FALSE);
        glfwWindowHint(GLFW_RESIZABLE, resizable? GL_TRUE : GL_FALSE);

        windowID=glfwCreateWindow(width ,height, title, fullscreen? glfwGetPrimaryMonitor() : NULL, NULL);
        GLFWVidMode vidmode=glfwGetVideoMode(glfwGetPrimaryMonitor());
        glfwSetWindowPos(windowID, (vidmode.width()-width)/2, (vidmode.height()-height)/2);

        glfwMakeContextCurrent(windowID);

        glfwSwapInterval(vsync? 1: 0);

        GL.createCapabilities();


    }


    public void update()
    {
        glfwSwapBuffers(windowID);
        glfwPollEvents();
    }

    public void clear()
    {
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
    }

    public void dispose()
    {

        glfwTerminate();

    }

    public void close()
    {
        glfwSetWindowShouldClose(windowID, GL_TRUE);
    }

    public boolean shoulclose()
    {
        return glfwWindowShouldClose(windowID)==GL_TRUE;
    }



}
