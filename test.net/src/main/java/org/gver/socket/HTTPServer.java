package org.gver.socket;

import com.github.jknack.handlebars.Handlebars;
import com.github.jknack.handlebars.Template;
import com.github.jknack.handlebars.io.ClassPathTemplateLoader;
import com.github.jknack.handlebars.io.TemplateLoader;
import com.google.common.collect.Maps;
import com.google.common.io.Resources;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Map;
import java.util.Set;

/**
 * 功能描述:
 * <p/>
 *
 * @author wanggen on 14-8-21.
 */
public class HTTPServer {

    public static String classpath = Resources.getResource("").getFile();
    static {
        System.err.println("classpath: "+classpath);
    }

    public static void main(String[] args) throws IOException {


        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.configureBlocking(false);
        ServerSocket serverSocket = serverSocketChannel.socket();
        serverSocket.bind(new InetSocketAddress(9000));
        Selector selector = Selector.open();
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

        while(true){
            int num = selector.select();
            if(num<1)
                continue;
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            for (SelectionKey selectionKey: selectionKeys){
                selectionKeys.remove(selectionKey);
                if(selectionKey.isAcceptable()){
                    SocketChannel client = serverSocketChannel.accept();
                    client.configureBlocking(false);


                    TemplateLoader loader = new ClassPathTemplateLoader("/");
                    Handlebars handlebars = new Handlebars(loader);

                    Template temp = handlebars.compile("response");

                    Map context = Maps.newConcurrentMap();
                    context.put("username", "端点网络");

                    String html = temp.apply(context);

                }
            }
        }

    }

}
