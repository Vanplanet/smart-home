package com.saferide.serve.event;

import java.util.Map;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * 事件处理
 */
public interface IEventHandler {

    void consumerEvent(ConcurrentLinkedQueue<Map<String, Object>> queue);
}
