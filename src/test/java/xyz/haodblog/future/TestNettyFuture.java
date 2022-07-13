package xyz.haodblog.future;

import io.netty.channel.EventLoop;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;

@Slf4j
public class TestNettyFuture {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        NioEventLoopGroup eventLoopGroup = new NioEventLoopGroup();
        EventLoop eventLoop = eventLoopGroup.next();
        Future<Object> future = eventLoop.submit(new Callable<Object>() {
            @Override
            public Object call() throws Exception {
                log.debug("执行计算");
                Thread.sleep(1000);
                return 1000;
            }
        });
//        同步获取结果
//        log.debug("wait...");
//        log.debug("result: {}", future.get());
        // 异步获取结果
        future.addListener(new GenericFutureListener<Future<? super Object>>() {
            @Override
            public void operationComplete(Future<? super Object> future) throws Exception {
                log.debug("接受结果: {}", future.getNow());
                eventLoopGroup.shutdownGracefully();
            }
        });


    }
}
