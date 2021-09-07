package com.saferide.serve.event;

import java.util.Map;

/**
 * 事件接口
 */
public interface IEvent {

    void setEventHandler(IEventHandler eventHandle);

    /**
     * 触发事件
     * @param actions 事件(建议使用LinkedHashMap保证操作顺序)
     */
    boolean eventTrigger(Map<String, Object> actions);
}
