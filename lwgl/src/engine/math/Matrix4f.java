package engine.math;

import java.nio.FloatBuffer;

/**
 * Created by Admin on 21.01.2016.
 */

//vectors are horisontal
public class Matrix4f {

    private float[] elements = new float[16];

    public Matrix4f(){
        for (int i=0;i<16;i++)
            elements[i]=0;
    }

    public Matrix4f(float diagonal){
        for (int i=0;i<16;i++)
            elements[i]=0;
        elements[0]=diagonal;
        elements[5]=diagonal;
        elements[10]=diagonal;
        elements[15]=diagonal;
    }

    public Matrix4f(float[] elem){
        if (elem.length==16)
        for (int i=0;i<16;i++)
            elements[i]=elem[i];
    }

    public static Matrix4f identity(){
        return new Matrix4f(1.0f);
    }

    public Matrix4f multiply(Matrix4f other){
        float[] temp=new float[16];

        for (int y=0;y<4;y++)
            for (int x=0;x<4;x++)
            {
                float sum=0;
                for (int e=0;e<4;e++)
                    sum+=elements[x+e*4]*other.elements[e+y*4];
                temp[x+y*4]=sum;
            }
        for (int i=0;i<16;i++)
            elements[i]=temp[i];
        return this;
    }

    public void store(FloatBuffer buffer){
        for (int i=0;i<16;i++)
            buffer.put(elements[i]);
    }

    public static Matrix4f translate(Vector3f vector){
        Matrix4f result=Matrix4f.identity();
        result.elements[3*4]=vector.x;
        result.elements[1+3*4]=vector.y;
        result.elements[2+3*4]=vector.z;

        return result;
    }

    public static Matrix4f translate(Vector3f vector, Matrix4f source, Matrix4f dest){
        if (dest==null)
            dest=new Matrix4f();
        dest.elements[12]+=source.elements[0]*vector.x+source.elements[4]*vector.y+source.elements[8]*vector.z;
        dest.elements[13]+=source.elements[1]*vector.x+source.elements[5]*vector.y+source.elements[9]*vector.z;
        dest.elements[14]+=source.elements[2]*vector.x+source.elements[6]*vector.y+source.elements[10]*vector.z;
        dest.elements[15]+=source.elements[3]*vector.x+source.elements[7]*vector.y+source.elements[11]*vector.z;

        return dest;
    }

    public static Matrix4f rotate(float angle, Vector3f axis, Matrix4f source, Matrix4f dest){
        if (dest==null)
            dest=new Matrix4f();
        float c=(float)Math.cos(angle);
        float s=(float)Math.sin(angle);
        float oneminusc = 1.0f-c;
        float xy = axis.x*axis.y;
        float yz = axis.y*axis.z;
        float xz = axis.x*axis.z;
        float xs = axis.x*s;
        float ys = axis.y*s;
        float zs = axis.z*s;

        float f00 = axis.x*axis.x*oneminusc+c;
        float f01 = xy*oneminusc+zs;
        float f02 = xz*oneminusc-ys;
        // n[3] not used
        float f10 = xy*oneminusc-zs;
        float f11 = axis.y*axis.y*oneminusc+c;
        float f12 = yz*oneminusc+xs;
        // n[7] not used
        float f20 = xz*oneminusc+ys;
        float f21 = yz*oneminusc-xs;
        float f22 = axis.z*axis.z*oneminusc+c;

        float t00 = source.elements[0] * f00 + source.elements[4] * f01 + source.elements[8] * f02;
        float t01 = source.elements[1] * f00 + source.elements[5] * f01 + source.elements[9] * f02;
        float t02 = source.elements[2] * f00 + source.elements[6] * f01 + source.elements[10] * f02;
        float t03 = source.elements[3] * f00 + source.elements[7] * f01 + source.elements[11] * f02;
        float t10 = source.elements[0] * f10 + source.elements[4] * f11 + source.elements[8] * f12;
        float t11 = source.elements[1] * f10 + source.elements[5] * f11 + source.elements[9] * f12;
        float t12 = source.elements[2] * f10 + source.elements[6] * f11 + source.elements[10] * f12;
        float t13 = source.elements[3] * f10 + source.elements[7] * f11 + source.elements[11] * f12;
        dest.elements[8] = source.elements[0] * f20 + source.elements[4] * f21 + source.elements[8] * f22;
        dest.elements[9] = source.elements[1] * f20 + source.elements[5] * f21 + source.elements[9] * f22;
        dest.elements[10] = source.elements[2] * f20 + source.elements[6] * f21 + source.elements[10] * f22;
        dest.elements[11] = source.elements[3] * f20 + source.elements[7] * f21 + source.elements[11] * f22;
        dest.elements[0] = t00;
        dest.elements[1] = t01;
        dest.elements[2] = t02;
        dest.elements[3] = t03;
        dest.elements[4] = t10;
        dest.elements[5] = t11;
        dest.elements[6] = t12;
        dest.elements[7] = t13;
        return dest;
    }

    public static Matrix4f scale(Vector3f vec, Matrix4f source, Matrix4f dest) {
        if (dest == null)
            dest = new Matrix4f();
        dest.elements[0] = source.elements[0] * vec.x;
        dest.elements[1] = source.elements[1] * vec.x;
        dest.elements[2] = source.elements[2] * vec.x;
        dest.elements[3] = source.elements[3] * vec.x;
        dest.elements[4] = source.elements[4] * vec.y;
        dest.elements[5] = source.elements[5] * vec.y;
        dest.elements[6] = source.elements[6] * vec.y;
        dest.elements[7] = source.elements[7] * vec.y;
        dest.elements[8] = source.elements[8] * vec.z;
        dest.elements[9] = source.elements[9] * vec.z;
        dest.elements[10]= source.elements[10] * vec.z;
        dest.elements[11] = source.elements[11] * vec.z;
        return dest;
    }

}
