package test.http;

import com.google.common.io.Files;
import org.junit.Test;
import sun.net.TelnetInputStream;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.List;

/**
 * @author wanggen on 14-8-1.
 */
public class FTPClientTest {

    @Test
    public void download() throws IOException {

        FtpDownloader ftpDownloader = new FtpDownloader("58.56.128.22", 21, "GPS_EX", "GPS_EX#123");

        String dir = "/eai/JHPT";
        final String fileName = "20140801_VENDOR_MONTH.csv";

        ftpDownloader.resolveFile(new FtpDownloader.Resolver() {
            public void doResolve(TelnetInputStream telnetInputStream) throws IOException {
                File tmp = new File(fileName);
                DataOutputStream dataOutputStream = null;
                try {
                    dataOutputStream = new DataOutputStream(new FileOutputStream(tmp));
                    int len;
                    byte buf[] = new byte[1024];
                    while ((len = telnetInputStream.read(buf)) > -1) {
                        dataOutputStream.write(buf, 0, len);
                    }
                    List<String> lines = Files.readLines(tmp, Charset.forName("GBK"));
                    for (String line : lines)
                        System.err.println(line);
                } finally {
                    if(dataOutputStream!=null)
                        dataOutputStream.close();
                    tmp.delete();
                }
            }
        }, dir, fileName);


    }


}
