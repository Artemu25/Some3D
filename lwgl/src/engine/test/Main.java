package engine.test;

/**
 * Created by Admin on 14.01.2016.
 */
import engine.window.Window;
import static org.lwjgl.opengl.GL11.*;


public class Main {

    private Window window;
    public Main()
    {
        window=new Window("Test", 800, 600, true, false, true, false);

        update();
    }

    private void update()
    {
        while(!window.shoulclose())
        {
            window.clear();
            glColor3f(0f, 1f, 1f);
            glBegin(GL_QUADS);
            glVertex2f(-0.5f, -0.5f);
            glVertex2f(-0.5f, 0.5f);
            glVertex2f(0.5f, 0.5f);
            glVertex2f(0.5f, -0.5f);
            glEnd();
            window.update();
        }
        window.dispose();
    }


    public static void main(String[] args)
    {
        new Main();
    }
}
