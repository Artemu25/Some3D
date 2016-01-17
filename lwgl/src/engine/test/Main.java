package engine.test;

/**
 * Created by Admin on 14.01.2016.
 */
import engine.drawing.Loader;
import engine.drawing.RawModel;
import engine.drawing.Render;
import engine.window.Window;
import shaders.StaticShader;



public class Main {

    private Window window;

    Render renderer;
    Loader loader;
    RawModel model;
    StaticShader shader;

    float[] vertices = {
            -0.5f, 0.5f, 0f,
            -0.5f, -0.5f, 0f,
            0.5f, -0.5f, 0f,
            0.5f, 0.5f, 0f,
    };

    int[] indexec = {
            0, 1, 3, 3, 1, 2
    };



    public Main()
    {
        loader=new Loader();
        renderer=new Render();
        window=new Window("Test", 800, 600, true, false, true, false);
        model= loader.loadtoVAO(vertices, indexec);
        shader = new StaticShader();
        update();
    }

    private void update()
    {
        while(!window.shoulclose())
        {
           renderer.prepare();
            shader.start();
            renderer.render(model);
            shader.stop();
            window.update();
        }

        shader.cleanUp();
        loader.cleanup();
        window.dispose();
    }


    public static void main(String[] args)
    {
        new Main();
    }
}
