package engine.drawing;

import engine.math.Matrix4f;
import entities.Entity;
import models.RawModel;
import models.TexturedModel;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;
import shaders.StaticShader;
import toolbox.Math;

/**
 * Created by Admin on 17.01.2016.
 */
public class Render {

    private static final float FOV = 70;
    private static final float NEAR_PLANE = 1.0f;
    private static final float FAR_PLANE = 1000;

    private Matrix4f projectionMatrix;

    public Render(StaticShader shader, int width, int height){
        createProjectionMatrix(width, height);
        shader.start();
        shader.loadProjectionMatrix(projectionMatrix);
        shader.stop();;
    }

    public void prepare()
    {
        GL11.glClear(GL11.GL_COLOR_BUFFER_BIT);
        GL11.glClearColor(0, 0.5f, 0, 1);
    }

    public void render(Entity entity, StaticShader shader)
    {
        TexturedModel model=entity.getModel();
        RawModel rawModel = model.getRawModel();
        GL30.glBindVertexArray(rawModel.getVaoID());
        GL20.glEnableVertexAttribArray(0);
        GL20.glEnableVertexAttribArray(1);
        Matrix4f transformationMatrix = Math.createTransformationMatrix(entity.getPosition(), entity.getRotX(), entity.getRotY(), entity.getRotZ(), entity.getScale());
        shader.loadTransformationMatrix(transformationMatrix);
        GL13.glActiveTexture(GL13.GL_TEXTURE0);
        GL11.glBindTexture(GL11.GL_TEXTURE_2D, model.getTexture().getID());
        GL11.glDrawElements(GL11.GL_TRIANGLES, rawModel.getVertexcount(), GL11.GL_UNSIGNED_INT, 0);
        GL20.glDisableVertexAttribArray(0);
        GL20.glDisableVertexAttribArray(1);
        GL30.glBindVertexArray(0);
    }

    private void createProjectionMatrix(int width, int height){
        float aspectRatio = (float)width/(float)height;
        float y_scale = (float) ((1f/ java.lang.Math.tan(java.lang.Math.toRadians(FOV/2f)))*aspectRatio);
        float x_scale = y_scale / aspectRatio;
        float frustrum_length = FAR_PLANE -NEAR_PLANE;

        float[] mat=new float[16];
        for (int i=0;i<16;i++)
            mat[i]=0;
        mat[0]=x_scale;
        mat[5]=y_scale;
        mat[10] = -((FAR_PLANE + NEAR_PLANE) / frustrum_length);
        mat[11] = -1;
        mat[14] = -((2 + NEAR_PLANE * FAR_PLANE) / frustrum_length);
        mat[15] = 0;

        projectionMatrix=new Matrix4f(mat);
    }
}
