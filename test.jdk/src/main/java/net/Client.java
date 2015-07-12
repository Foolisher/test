/*
 * Copyright (c) 2014 杭州端点网络科技有限公司
 */

package net;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

/**
 * <pre>
 *   功能描述:
 * </pre>
 *
 * @author wanggen on 2015-06-19.
 */
public class Client {


    public static void main(String[] args) throws IOException {


        SocketChannel socketChannel = SocketChannel.open();
        socketChannel.configureBlocking(false);
        socketChannel.connect(new InetSocketAddress("localhost", 8084));
        Selector selector = Selector.open();

        socketChannel.register(selector, SelectionKey.OP_CONNECT);

        while(true){
            int selected = selector.select();
            if(!socketChannel.isConnected())
                socketChannel.close();
            if(selected>0){
                System.err.println("selected keys:"+selected);
                Iterator<SelectionKey> iter = selector.selectedKeys().iterator();
                while(iter.hasNext()){
                    SelectionKey key = iter.next();
                    iter.remove();
                    SocketChannel channel = (SocketChannel)key.channel();
                    channel.configureBlocking(false);
                    if(key.isConnectable()){
                        System.err.println("isConnectable");
                        socketChannel.finishConnect();
                        socketChannel.register(selector, socketChannel.validOps() | SelectionKey.OP_WRITE);
                    }else if(key.isWritable()){
                        System.err.println("isWritable");
                        ByteBuffer buf = ByteBuffer.allocate(64);
                        buf.putChar('w');
                        buf.flip();
                        socketChannel.write(buf);

                        socketChannel.register(selector, socketChannel.validOps() & (~SelectionKey.OP_WRITE));

                    }
                    if(!key.isValid()){
                        key.channel().close();
                    }
                }
            }
//            System.err.println("client try");

        }


    }

}
