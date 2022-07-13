package xyz.haodblog.future;

import io.netty.channel.EventLoop;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.util.concurrent.DefaultPromise;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutionException;


@Slf4j
public class TestNettyPromise {
    public static void main(String[] args) {

        NioEventLoopGroup eventLoopGroup = new NioEventLoopGroup();
        EventLoop eventLoop = eventLoopGroup.next();


        //可以主动创建 promise--》》 结果容器
        DefaultPromise<Object> promise = new DefaultPromise<>(eventLoop);
        eventLoop.submit(() ->{
            try {
                Thread.sleep(1000);
                promise.setSuccess(0);
            } catch (InterruptedException e) {
                promise.setFailure(e);
            }

        });

        log.debug("await result...");
        try {
            log.debug("result -> {}", promise.get());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
    }
}
