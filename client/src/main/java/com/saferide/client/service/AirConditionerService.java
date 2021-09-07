package com.saferide.client.service;

import com.saferide.common.ServerResponse;
import com.saferide.entity.AirConditioner;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author saferide
 * @since 2021-04-30
 */
public interface AirConditionerService {

    ServerResponse lowTemperatureEvent(String id);

    ServerResponse highTemperatureEvent(String id);

    ServerResponse setTypeEvent(String id, String type);

    ServerResponse lowSpeedEvent(String id);

    ServerResponse highSpeedEvent(String id);

    AirConditioner getById(String id);
}
