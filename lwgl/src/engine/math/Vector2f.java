package engine.math;

/**
 * Created by Admin on 20.01.2016.
 */
public class Vector2f {

    public float x,y;


    public Vector2f(){
        x=y=0;
    }

    public Vector2f(float xy){
        x=y=xy;
    }

    public Vector2f(float x, float y){
        this.x=x;
        this.y=y;
    }

    public Vector2f(Vector2f vector){
        set(vector.x, vector.y);
    }

    public Vector2f scale(float s){
        return set(x*s, y*s);
    }

    public Vector2f scale(float sx, float sy){
        return set(x*sx, y*sy);
    }

    public Vector2f scale(Vector2f vector){
        return set(x*vector.x, y*vector.y);
    }

    public Vector2f add(float a){
        return set(x+a, y+a);
    }

    public Vector2f add(float ax, float ay){
        return set(x+ax, y+ay);
    }

    public Vector2f add(Vector2f vector){
        return set(x+vector.x, y+vector.y);
    }

    public Vector2f subtract(float s){
        return set(x-s, y-s);
    }

    public Vector2f subtract(float sx, float sy){
        return set(x-sx, y-sy);
    }

    public Vector2f subtract(Vector2f vector){
        return set(x-vector.x, y-vector.y);
    }

    public Vector2f devide(float d){
        return set(x/d, y/d);
    }

    public Vector2f devide(float dx, float dy){
        return set(x/dx, y/dy);
    }

    public Vector2f devide(Vector2f vector){
        return set(x/vector.x, y/vector.y);
    }

    public Vector2f copy(){
        return new Vector2f(x,y);
    }

    public Vector2f set(float x, float y){
        this.x=x;
        this.y=y;
        return this;
    }

    @Override
    public boolean equals(Object o){
        if (o!=null && o instanceof Vector2f)
          return (x==((Vector2f)o).x) && (y==((Vector2f)o).y);
        return false;
    }
}
