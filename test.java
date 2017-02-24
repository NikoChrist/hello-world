/*
 * Copyright (c) 2015 - 2016 SuperDream Inc. <http://www.superdream.me>
 */

package me.superdream.fpoker.mj.executor.v1;

import akka.actor.ActorRef;
import me.superdream.fpoker.mj.core.event.Begin;
import me.superdream.fpoker.mj.executor.AbstractJoinedExecutor;
import me.superdream.fpoker.net.RequestData;
import me.superdream.fpoker.net.executor.annotations.Executor;

import java.util.List;

import static java.util.Collections.emptyList;

/**
 * 开始游戏  嘿嘿哈哈
 *
 * @author <a href="mailto:517926804@qq.com">Mike Chen</a>
 * @version 0.1.0
 * @since 0.1.0
 */
@Executor(type = "begin")
public class BeginExecutor extends AbstractJoinedExecutor {
    public BeginExecutor(String type, int version) {
        super(type, version);
    }

    @Override
    public Class<? extends RequestData> getDataClass() {
        return Data.class;
    }

    @Override
    protected void doExec(ActorRef conn, long userId, String roomId, RequestData data) {
        Data d = (Data) data;
        if (d == null)
            roomManager().tell(new Begin(getType(), getVersion(), userId, roomId, emptyList()), conn);
        else
            roomManager().tell(new Begin(getType(), getVersion(), userId, roomId, d.cards), conn);
    }

    public static class Data extends RequestData {
        public List<List<Integer>> cards = emptyList();
    }
}
