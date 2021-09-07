package com.saferide.serve.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.saferide.common.ServerResponse;
import com.saferide.entity.AirConditioner;
import com.saferide.serve.event.IEventHandler;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author saferide
 * @since 2021-04-30
 */
public interface AirConditionerService extends IService<AirConditioner>, IEventHandler {

    /**
     * 添加事件到队列
     */
    ServerResponse lowTemperatureEvent(String id);

    ServerResponse highTemperatureEvent(String id);

    ServerResponse lowSpeedEvent(String id);

    ServerResponse highSpeedEvent(String id);

    ServerResponse setTypeEvent(String id, String type);

    /**
     * 执行事件
     */
    ServerResponse lowTemperature(String id);

    ServerResponse highTemperature(String id);

    ServerResponse lowSpeed(String id);

    ServerResponse highSpeed(String id);

    ServerResponse setType(String id, String type);

}
