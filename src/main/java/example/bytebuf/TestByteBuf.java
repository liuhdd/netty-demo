package example.bytebuf;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;

import java.nio.charset.StandardCharsets;

import static example.utils.LogUtil.log;

public class TestByteBuf {
    public static void main(String[] args) {
        ByteBuf byteBuf = ByteBufAllocator.DEFAULT.buffer();
        System.out.println(byteBuf);
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < 30; i++) {
            stringBuilder.append((char) ('a' + i % 26));
        }
        byteBuf.writeBytes(stringBuilder.toString().getBytes(StandardCharsets.UTF_8));

        log(byteBuf);
    }
}
