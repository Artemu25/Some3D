package engine.drawing;




import models.RawModel;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.*;

import java.nio.ByteBuffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.ArrayList;
import java.util.List;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_2D;
import static org.lwjgl.opengl.GL11.glBindTexture;
import static org.lwjgl.stb.STBImage.*;

/**
 * Created by Admin on 17.01.2016.
 */
public class Loader {


    private List<Integer> VAOs = new ArrayList<>();
    private List<Integer> VBOs = new ArrayList<>();
    private List<Integer> textures = new ArrayList<>();


    public RawModel loadtoVAO(float[] positions, float[] textureCoords, int[] indeces)
    {
        int vaoID=createVAO();
        bindIndecesbuf(indeces);
        storeData(0, 3, positions);
        storeData(1, 2, textureCoords);
        unbind();
        return new RawModel(vaoID, indeces.length);
    }

    public int loadTexture(String fileName){
        ByteBuffer image;
        int width, height, components;
        int textureID;
        IntBuffer w = BufferUtils.createIntBuffer(1);
        IntBuffer h = BufferUtils.createIntBuffer(1);
        IntBuffer c = BufferUtils.createIntBuffer(1);
        image = stbi_load("res/"+fileName+".png", w, h, c, 0);
        width=w.get(0);
        height=h.get(0);
        components=c.get(0);
        textureID=glGenTextures();
        glBindTexture(GL_TEXTURE_2D, textureID);

        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_S, GL_REPEAT);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL_REPEAT);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_LINEAR);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_LINEAR);
        glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA, width, height, 0, components==4?GL_RGBA:GL_RGB, GL_UNSIGNED_BYTE, image);

        glBindTexture(GL_TEXTURE_2D, 0);
        stbi_image_free(image);
        textures.add(textureID);
        return  textureID;
    }

    private int createVAO()
    {
        int vaoID = GL30.glGenVertexArrays();
        VAOs.add(vaoID);
        GL30.glBindVertexArray(vaoID);
        return vaoID;
    }

    private void storeData(int attrnum, int coordinateSize, float[] data)
    {
        int vboID = GL15.glGenBuffers();
        VBOs.add(vboID);
        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, vboID);
        FloatBuffer buf=DataInToBuffer(data);
        GL15.glBufferData(GL15.GL_ARRAY_BUFFER, buf, GL15.GL_STATIC_DRAW);
        GL20.glVertexAttribPointer(attrnum, coordinateSize, GL11.GL_FLOAT, false, 0, 0);
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

        for (int texture:textures)
            GL11.glDeleteTextures(texture);
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
