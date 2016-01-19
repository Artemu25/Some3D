package engine.util;

import org.lwjgl.BufferUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

import static org.lwjgl.system.jemalloc.JEmalloc.*;

/**
 * Created by Admin on 19.01.2016.
 */
public class BufferUtil {

    public static ByteBuffer fileToByteBuffer(String path, int bufferSize) throws IOException {
        ByteBuffer buffer;
        FileInputStream fis=new FileInputStream(path);
        FileChannel fc = fis.getChannel();
        buffer = BufferUtils.createByteBuffer((int) fc.size()+1);

        while (fc.read(buffer)!=-1);
        fis.close();
        fc.close();

        buffer.flip();
        return  buffer;
    }
}
