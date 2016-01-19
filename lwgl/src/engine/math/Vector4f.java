package engine.math;

/**
 * Created by Admin on 20.01.2016.
 */
public class Vector4f {

    public float x,y,z,w;

    public Vector4f(){
        x=y=z=w=0;
    }

    public Vector4f(float xyzw){
        x=y=z=w=xyzw;
    }

    public Vector4f(float x, float y, float z, float w){
        this.x=x;
        this.y=y;
        this.z=z;
        this.w=w;
    }

    public Vector4f(Vector4f vector){
        set(vector.x, vector.y, vector.z, vector.w);
    }

    public Vector4f scale(float s){
        return set(x*s, y*s, z*s, w*s);
    }

    public Vector4f scale(float sx, float sy, float sz, float sw){
        return set(x*sx, y*sy, z*sz, w*sw);
    }

    public Vector4f scale(Vector4f vector){
        return set(x*vector.x, y*vector.y, z*vector.z, w*vector.w);
    }

    public Vector4f add(float a){
        return set(x+a, y+a, z+a, w+a);
    }

    public Vector4f add(float ax, float ay, float az, float aw){
        return set(x+ax, y+ay, z+az, w+aw);
    }

    public Vector4f add(Vector4f vector){
        return set(x+vector.x, y+vector.y, z+vector.z, w+vector.w);
    }

    public Vector4f subtract(float s){
        return set(x-s, y-s, z-s, w-s);
    }

    public Vector4f subtract(float sx, float sy, float sz, float sw){
        return set(x-sx, y-sy, z-sz, w-sw);
    }

    public Vector4f subtract(Vector4f vector){
        return set(x-vector.x, y-vector.y, z-vector.z, w-vector.w);
    }

    public Vector4f devide(float d){
        return set(x/d, y/d, z/d, w/d);
    }

    public Vector4f devide(float dx, float dy, float dz, float dw){
        return set(x/dx, y/dy, z/dz, w/dw);
    }

    public Vector4f devide(Vector4f vector){
        return set(x/vector.x, y/vector.y, z/vector.z, w/vector.w);
    }

    public Vector4f copy(){
        return new Vector4f(x, y, z, w);
    }

    public Vector4f set(float x, float y, float z, float w){
        this.x=x;
        this.y=y;
        this.z=z;
        this.w=w;
        return this;
    }

    @Override
    public boolean equals(Object o){
        if (o!=null && o instanceof Vector4f)
            return (x==((Vector4f)o).x) && (y==((Vector4f)o).y) && (z==((Vector4f)o).z) && (w==((Vector4f)o).w);
        return false;
    }
}
