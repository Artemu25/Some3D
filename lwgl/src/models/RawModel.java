package models;

/**
 * Created by Admin on 17.01.2016.
 */
public class RawModel {

    private int vaoID;
    private int vertexcount;

    public RawModel(int ID, int vertexcount)
    {
        this.vaoID=ID;
        this.vertexcount=vertexcount;
    }

    public int getVaoID() {
        return vaoID;
    }


    public int getVertexcount() {
        return vertexcount;
    }

}
