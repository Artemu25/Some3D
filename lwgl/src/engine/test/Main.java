package engine.test;

/**
 * Created by Admin on 14.01.2016.
 */
import engine.drawing.Loader;
import engine.math.Vector3f;
import entities.Camera;
import entities.Entity;
import models.RawModel;
import engine.drawing.Render;
import engine.window.Window;
import models.TexturedModel;
import org.lwjgl.opengl.GL11;
import shaders.StaticShader;
import textures.ModelTexture;

import static org.lwjgl.glfw.GLFW.*;

public class Main {

    private Window window;

    Render renderer;
    Loader loader;
    RawModel model;
    StaticShader shader;
    ModelTexture texture;
    TexturedModel staticModel;
    Entity entity;
    Camera camera;

    float[] vertices = {
            -0.5f,0.5f,-0.5f,
            -0.5f,-0.5f,-0.5f,
            0.5f,-0.5f,-0.5f,
            0.5f,0.5f,-0.5f,

            -0.5f,0.5f,0.5f,
            -0.5f,-0.5f,0.5f,
            0.5f,-0.5f,0.5f,
            0.5f,0.5f,0.5f,

            0.5f,0.5f,-0.5f,
            0.5f,-0.5f,-0.5f,
            0.5f,-0.5f,0.5f,
            0.5f,0.5f,0.5f,

            -0.5f,0.5f,-0.5f,
            -0.5f,-0.5f,-0.5f,
            -0.5f,-0.5f,0.5f,
            -0.5f,0.5f,0.5f,

            -0.5f,0.5f,0.5f,
            -0.5f,0.5f,-0.5f,
            0.5f,0.5f,-0.5f,
            0.5f,0.5f,0.5f,

            -0.5f,-0.5f,0.5f,
            -0.5f,-0.5f,-0.5f,
            0.5f,-0.5f,-0.5f,
            0.5f,-0.5f,0.5f

    };

    float[] textureCoords = {

            0,0,
            0,1,
            1,1,
            1,0,
            0,0,
            0,1,
            1,1,
            1,0,
            0,0,
            0,1,
            1,1,
            1,0,
            0,0,
            0,1,
            1,1,
            1,0,
            0,0,
            0,1,
            1,1,
            1,0,
            0,0,
            0,1,
            1,1,
            1,0


    };

    int[] indices = {
            0,1,3,
            3,1,2,
            4,5,7,
            7,5,6,
            8,9,11,
            11,9,10,
            12,13,15,
            15,13,14,
            16,17,19,
            19,17,18,
            20,21,23,
            23,21,22

    };

    /*float[] vertices = {
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
    };*/



    public Main()
    {
        loader = new Loader();
        window = new Window("Test", 800, 600, true, false, true, false);
        camera = new Camera(window);
        model = loader.loadtoVAO(vertices, textureCoords, indices);
        texture = new ModelTexture(loader.loadTexture("piramid"));
        staticModel = new TexturedModel(model, texture);
        entity = new Entity(staticModel, new Vector3f(0, 0, -5), 0, 0, 0, 1);
        shader = new StaticShader();
        renderer=new Render(shader, window.getWidth(), window.getHeight());
        update();
    }

    private void update()
    {
        while(!window.shoulclose())
        {
            entity.increaseRotation(1, 1, 0);
            camera.move();
            renderer.prepare();
            shader.start();
            shader.loadViewMatrix(camera);
            renderer.render(entity, shader);
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
