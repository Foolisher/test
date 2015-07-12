/*
 * Copyright (c) 2014 杭州端点网络科技有限公司
 */

package net;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class Server {


    public static void main(String[] args) {

        ServerSocketChannel serverSocketChannel = null;
        Selector selector = null;
        try {
            serverSocketChannel = ServerSocketChannel.open();
            serverSocketChannel.configureBlocking(false);
            serverSocketChannel.bind(new InetSocketAddress(8084));
            selector = Selector.open();
            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        } catch(IOException e) {
            e.printStackTrace();
        }



        while(true) {

            int selected = 0;
            try {
                selected = selector.select(1000);
            } catch(IOException e) {
                e.printStackTrace();
                break;
            }


            if(selected > 0){
                Set<SelectionKey> selectionKeys = selector.selectedKeys();
                Iterator<SelectionKey> iterator = selectionKeys.iterator();

                while(iterator.hasNext()) {
                    SelectionKey selectionKey = iterator.next();
                    iterator.remove();
                    if(selectionKey.isAcceptable()){
                        System.out.println("isAcceptable");
                        SocketChannel socketChannel = null;
                        try {
                            socketChannel = serverSocketChannel.accept();
                            socketChannel.configureBlocking(false);
                            socketChannel.socket().setKeepAlive(true);
                            socketChannel.register(selector, SelectionKey.OP_READ);
                        } catch(IOException e) {
                            e.printStackTrace();
                            try {
                                assert socketChannel != null;
                                socketChannel.close();
                            } catch(IOException e1) {
                                e1.printStackTrace();
                            }
                            selectionKey.cancel();
                        }

                    } else if(selectionKey.isConnectable()){
                        System.out.println("isConnectable");
                    } else if(selectionKey.isReadable()){
                        System.out.println("isReadable");
                        SocketChannel channel = (SocketChannel) selectionKey.channel();
                        if(!selectionKey.isValid())
                            continue;

                        ByteBuffer buf = ByteBuffer.allocate(64);
                        try {
                            int readed = channel.read(buf);
                            if(readed<0){
                                channel.close();
                                selectionKey.cancel();
                                continue;
                            }
                        } catch(IOException e) {
                            e.printStackTrace();
                            selectionKey.cancel();
                            try {
                                selectionKey.channel().close();
                            } catch(IOException e1) {
                                e1.printStackTrace();
                            }
                        }
                        buf.rewind();
                        while(buf.hasRemaining())
                            System.out.println("get:"+(char)buf.get());

                        buf.clear();


                        selectionKey.attach(buf);
                        selectionKey.interestOps(selectionKey.interestOps() | SelectionKey.OP_WRITE);

                    }else if(selectionKey.isWritable()){
                        System.out.println("isWritable");
                        SocketChannel channel = (SocketChannel) selectionKey.channel();
                        try {
                            if(channel.read((ByteBuffer) selectionKey.attachment())<0){
                                channel.close();
                                selectionKey.cancel();
                            }
                        } catch(IOException e) {
                            e.printStackTrace();
                        }

                        ByteBuffer buf = (ByteBuffer) selectionKey.attachment();
                        buf.putChar('w');
                        buf.flip();
                        try {
                            channel.write(buf);
                        } catch(IOException e) {
                            e.printStackTrace();
                        }

                        selectionKey.interestOps(selectionKey.interestOps() & (~SelectionKey.OP_WRITE));
                    }
                }
                System.out.println("selected keys:" + selected);
            }
            System.out.println("try accept");
//            try { Thread.sleep(1000); } catch(InterruptedException e) {}
        }


    }


}
