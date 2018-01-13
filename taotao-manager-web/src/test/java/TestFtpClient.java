import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class TestFtpClient {


    @Test
    public void testFtpClient() throws IOException {
        /**
         * 1. 创建FTP客户端
         * 2. 连接登录到FTP
         * 3. 上传图片
         * 4. 关闭FTP连接
         */
        FTPClient ftpClient = new FTPClient();
        ftpClient.connect("192.168.0.101", 21);
        ftpClient.login("ftpuser", "123456");
        ftpClient.changeWorkingDirectory("/home/ftpuser/www/images");
        ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
        FileInputStream inputStream = new FileInputStream(new File("D:\\abc.jpg"));
        ftpClient.storeFile("hello.jpg", inputStream);
        ftpClient.logout();
    }
}
