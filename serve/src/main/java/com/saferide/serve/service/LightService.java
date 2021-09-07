package com.saferide.serve.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.saferide.common.ServerResponse;
import com.saferide.entity.AirConditioner;
import com.saferide.entity.Light;
import com.saferide.serve.event.IEventHandler;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author saferide
 * @since 2021-04-30
 */
public interface LightService extends IService<Light>, IEventHandler {

    /**
     * 添加事件到队列
     */
    ServerResponse lowBrightEvent(String id);

    ServerResponse highBrightEvent(String id);

    ServerResponse setTemperatureEvent(String id, Integer temperature);

    /**
     * 执行事件
     */
    ServerResponse lowBright(String id);

    ServerResponse highBright(String id);

    ServerResponse setTemperature(String id, Integer temperature);

}
