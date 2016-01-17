package engine.drawing;


import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.*;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Admin on 17.01.2016.
 */
public class Loader {


    private List<Integer> VAOs = new ArrayList<Integer>();
    private List<Integer> VBOs = new ArrayList<Integer>();;


    public RawModel loadtoVAO(float[] positions, int[] indeces)
    {
        int vaoID=createVAO();
        bindIndecesbuf(indeces);
        storeData(0, positions);
        unbind();
        return new RawModel(vaoID, indeces.length);
    }

    private int createVAO()
    {
        int vaoID = GL30.glGenVertexArrays();
        VAOs.add(vaoID);
        GL30.glBindVertexArray(vaoID);
        return vaoID;
    }

    private void storeData(int attrnum, float[] data)
    {
        int vboID = GL15.glGenBuffers();
        VBOs.add(vboID);
        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, vboID);
        FloatBuffer buf=DataInToBuffer(data);
        GL15.glBufferData(GL15.GL_ARRAY_BUFFER, buf, GL15.GL_STATIC_DRAW);
        GL20.glVertexAttribPointer(attrnum, 3, GL11.GL_FLOAT, false, 0, 0);
        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0);
    }

    private FloatBuffer DataInToBuffer(float[] data)
    {
        FloatBuffer buffer= BufferUtils.createFloatBuffer(data.length);
        buffer.put(data);
        buffer.flip();
        return buffer;
    }


    private void unbind()
    {
        GL30.glBindVertexArray(0);
    }

    public void cleanup()
    {
        for (int vao : VAOs)
            GL30.glDeleteVertexArrays(vao);
        for (int vbo : VBOs)
            GL15.glDeleteBuffers(vbo);
    }

    private void bindIndecesbuf(int[] indeces)
    {
        int vboID=GL15.glGenBuffers();
        VBOs.add(vboID);
        GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, vboID);
        IntBuffer buffer=storeDatInIntBuf(indeces);
        GL15.glBufferData(GL15.GL_ELEMENT_ARRAY_BUFFER, buffer, GL15.GL_STATIC_DRAW);

    }

    private IntBuffer storeDatInIntBuf(int[] data)
    {
        IntBuffer buf=BufferUtils.createIntBuffer(data.length);
        buf.put(data);
        buf.flip();
        return buf;
    }



}
