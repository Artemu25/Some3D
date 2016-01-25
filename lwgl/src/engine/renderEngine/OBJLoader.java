package engine.renderEngine;


import engine.math.Vector2f;
import engine.math.Vector3f;
import models.RawModel;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by Admin on 22.01.2016.
 */
public class OBJLoader {

    public static RawModel loadObjModel(String filename, Loader loader){
        FileReader fr = null;
        try {
            fr = new FileReader(new File("res/"+filename+".obj"));
        } catch (FileNotFoundException e) {
            System.out.println("Load failed!");
            e.printStackTrace();
        }

        BufferedReader reader = new BufferedReader(fr);
        String line;
        List<Vector3f> vertices = new ArrayList<>();
        List<Vector2f> textures = new ArrayList<>();
        List<Vector3f> normals = new ArrayList<>();
        List<Integer> indices = new ArrayList<>();
        float[] verticesArray = null;
        float[] normalsArray = null;
        float[] texturesArray = null;
        int[] indicesArray = null;
        try{

            while (true){
               line = reader.readLine();
               String[] currentList = line.split(" ");
               if (line.startsWith("v ")){
                   Vector3f vertex = new Vector3f(Float.parseFloat(currentList[1]), Float.parseFloat(currentList[2]), Float.parseFloat(currentList[3]));
                   vertices.add(vertex);
               }else  if (line.startsWith("vt ")){
                   Vector2f texture = new Vector2f(Float.parseFloat(currentList[1]), Float.parseFloat(currentList[2]));
                   textures.add(texture);
               }else  if (line.startsWith("vn ")){
                   Vector3f normal = new Vector3f(Float.parseFloat(currentList[1]), Float.parseFloat(currentList[2]), Float.parseFloat(currentList[3]));
                   normals.add(normal);
               }else  if (line.startsWith("f ")){
                   texturesArray = new float[vertices.size()*2];
                   normalsArray = new float[vertices.size()*3];
                   break;
               }
           }

            while (line!=null){
                if (!line.startsWith("f ")){
                    line = reader.readLine();
                    continue;
                }
                String[] currentLine = line.split(" ");
                String[] veretx1 = currentLine[1].split("/");
                String[] veretx2 = currentLine[2].split("/");
                String[] veretx3 = currentLine[3].split("/");


                processVertex(veretx1, indices, textures, normals, texturesArray, normalsArray);
                processVertex(veretx2, indices, textures, normals, texturesArray, normalsArray);
                processVertex(veretx3, indices, textures, normals, texturesArray, normalsArray);
                line = reader.readLine();
            }
            reader.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }

        verticesArray = new float[vertices.size()*3];
        indicesArray = new int[indices.size()];

        int vertexPointer = 0;
        for (Vector3f vertex:vertices){
            verticesArray[vertexPointer++] = vertex.x;
            verticesArray[vertexPointer++] = vertex.y;
            verticesArray[vertexPointer++] = vertex.z;
        }

        for (int i=0;i<indices.size();i++){
            indicesArray[i] = indices.get(i);
        }
        return loader.loadToVAO(verticesArray, texturesArray, normalsArray, indicesArray);
    }

    private static void processVertex(String[] verexData, List<Integer> indices, List<Vector2f> textures, List<Vector3f> normals, float[] textureArray, float[] normalsArray){
        int currentVeretxPointer = Integer.parseInt(verexData[0]) - 1;
        indices.add(currentVeretxPointer);
        Vector2f currentTex = textures.get(Integer.parseInt(verexData[1])-1);
        textureArray[currentVeretxPointer*2] = currentTex.x;
        textureArray[currentVeretxPointer*2+1] = 1 - currentTex.y;
        Vector3f currentNorm = normals.get(Integer.parseInt(verexData[2])-1);
        normalsArray[currentVeretxPointer*3] = currentNorm.x;
        normalsArray[currentVeretxPointer*3+1] = currentNorm.y;
        normalsArray[currentVeretxPointer*3+2] = currentNorm.z;
    }
}
