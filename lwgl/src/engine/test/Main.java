package engine.test;

/**
 * Created by Admin on 14.01.2016.
 */
import engine.renderEngine.Loader;
import engine.math.Vector3f;
import engine.renderEngine.OBJLoader;
import entities.Camera;
import entities.Entity;
import entities.Light;
import models.RawModel;
import engine.renderEngine.Render;
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
    TexturedModel staticModel;
    Entity entity;
    Camera camera;
    Light light;



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
        light = new Light(new Vector3f(0, 0, -20), new Vector3f(1, 1, 1));
        model = OBJLoader.loadObjModel("dragon", loader);
        staticModel = new TexturedModel(model, new ModelTexture(loader.loadTexture("colors")));
        texture = staticModel.getTexture();
        texture.setShineDamper(400);
        texture.setReflectivity(1);
        entity = new Entity(staticModel, new Vector3f(0, 0, -50), 0, 0, 0, 1);
        shader = new StaticShader();
        renderer=new Render(shader, window.getWidth(), window.getHeight());
        update();
    }

    private void update()
    {
        while(!window.shoulclose())
        {
            entity.increaseRotation(0, 1, 0);
            camera.move();
            renderer.prepare();
            shader.start();
            shader.loadLight(light);
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
