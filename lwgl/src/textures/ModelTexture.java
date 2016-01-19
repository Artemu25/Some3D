package textures;

import java.nio.ByteBuffer;

import static org.lwjgl.opengl.GL11.*;
/**
 * Created by Admin on 18.01.2016.
 */
public class ModelTexture {

    private int textureID;

    public ModelTexture(int id)
    {
        this.textureID=id;
    }

    public int getID(){
        return this.textureID;
    }
}
