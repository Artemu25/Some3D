package engine.test;

/**
 * Created by Admin on 14.01.2016.
 */
import engine.drawing.Loader;
import models.RawModel;
import engine.drawing.Render;
import engine.window.Window;
import models.TexturedModel;
import shaders.StaticShader;
import textures.ModelTexture;


public class Main {

    private Window window;

    Render renderer;
    Loader loader;
    RawModel model;
    StaticShader shader;
    ModelTexture texture;
    TexturedModel textureModel;


    float[] vertices = {
            -0.5f, 0.5f, 0f,
            -0.5f, -0.5f, 0f,
            0.5f, -0.5f, 0f,
            0.5f, 0.5f, 0f,
    };

    int[] indexec = {
            0, 1, 3, 3, 1, 2
    };

    float[] textureCoords = {
            0,0,
            0,1,
            1,1,
            1,0
    };



    public Main()
    {
        loader=new Loader();
        renderer=new Render();
        window=new Window("Test", 800, 600, true, false, true, false);
        model= loader.loadtoVAO(vertices, textureCoords, indexec);
        texture = new ModelTexture(loader.loadTexture("piramid"));
        textureModel = new TexturedModel(model, texture);
        shader = new StaticShader();
        update();
    }

    private void update()
    {
        while(!window.shoulclose())
        {
           renderer.prepare();
            shader.start();
            renderer.render(textureModel);
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
