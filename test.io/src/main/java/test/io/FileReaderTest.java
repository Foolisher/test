/**
 * 
 */
package test.io;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;

import lombok.Cleanup;
import lombok.extern.slf4j.Slf4j;

/**
 * @author wanggen
 * @date 2014年6月28日
 */
@Slf4j
public class FileReaderTest {

    /**
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        
        
        RandomAccessFile aFile = new RandomAccessFile("nio-data.txt", "rw");
        FileChannel inChannel = aFile.getChannel();
        ByteBuffer buf = ByteBuffer.allocate(48);

        System.out.println((char)139+"   "+((char)29579));
        
        //-25, -114, -117
        int bytesRead = inChannel.read(buf);
        
        System.out.println(25<<16+114<<8+117);
        
        System.out.println(new String(buf.array(), "UTF-8"));
        while (bytesRead != -1) {

            System.out.println("Read " + bytesRead);
            buf.flip();

            while (buf.hasRemaining()) {
                System.out.print((char) buf.get());
            }

            buf.clear();
            bytesRead = inChannel.read(buf);
        }
        aFile.close();

    }

}
