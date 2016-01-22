package toolbox;

import engine.math.Matrix4f;
import engine.math.Vector3f;
import entities.Camera;

/**
 * Created by Admin on 21.01.2016.
 */

//operations with Matrix (like rotation)

public class Math {

    public static Matrix4f createTransformationMatrix(Vector3f translation, float rx, float ry, float rz, float scale){
        Matrix4f matrix=Matrix4f.identity();
        Matrix4f.translate(translation, matrix, matrix);
        Matrix4f.rotate((float) java.lang.Math.toRadians(rx), new Vector3f(1, 0, 0), matrix, matrix);
        Matrix4f.rotate((float) java.lang.Math.toRadians(ry), new Vector3f(0, 1, 0), matrix, matrix);
        Matrix4f.rotate((float) java.lang.Math.toRadians(rz), new Vector3f(0, 0, 1), matrix, matrix);
        Matrix4f.scale(new Vector3f(scale, scale, scale), matrix, matrix);
        return matrix;
    }

    public static Matrix4f createviewMatrix(Camera camera){
        Matrix4f viewMatrix = Matrix4f.identity();
        Matrix4f.rotate((float) java.lang.Math.toRadians(camera.getPitch()), new Vector3f(1, 0, 0), viewMatrix, viewMatrix);
        Matrix4f.rotate((float) java.lang.Math.toRadians(camera.getYaw()), new Vector3f(0, 1, 0), viewMatrix, viewMatrix);
        Vector3f cameraPos = camera.getPosition();
        Vector3f negativeCameraPos = new Vector3f(-cameraPos.x, -cameraPos.y, -cameraPos.z);
        Matrix4f.translate(negativeCameraPos, viewMatrix, viewMatrix);
        return viewMatrix;
    }
}
