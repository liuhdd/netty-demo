import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
@Slf4j
public class TestByteBuffer {


    @Test
    public void testRead(){

    }
    public static void main(String[] args) {
        try (FileChannel channel = new FileInputStream("data.txt").getChannel()) {
            ByteBuffer buffer = ByteBuffer.allocateDirect(10);
            int len = -1;
            while ((len = channel.read(buffer)) != -1) {
                buffer.flip();
                log.debug("bytes: {}", len);
                while (buffer.hasRemaining()) {
                    byte b = buffer.get();
                    System.out.println((char) b);
                }
                buffer.clear();
            }
            channel.transferTo(0, channel.size(), new RandomAccessFile("to.txt", "rw").getChannel());
        } catch (IOException e) {
        }

    }
}
