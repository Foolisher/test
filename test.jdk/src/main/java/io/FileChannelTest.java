/*
 * Copyright (c) 2014
 */

package io;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * <pre>
 *   功能描述:
 * </pre>
 *
 * @author wanggen on 2015-06-18.
 */
public class FileChannelTest {

    static String root = "/Users/wanggen/workspace/test/test.jdk/src/main/resources/";

    public static void main(String[] args) throws IOException {

        FileChannel wchannel = new RandomAccessFile(root+"data", "rw").getChannel();

        ByteBuffer buf = ByteBuffer.allocate(64);

        buf.putChar('我');
        buf.flip();

        wchannel.write(buf);


        FileChannel rchannel = new FileInputStream(root+"data").getChannel();
        buf.clear();
        rchannel.read(buf);
        buf.rewind();
        System.out.println(buf.getChar());


    }

}
