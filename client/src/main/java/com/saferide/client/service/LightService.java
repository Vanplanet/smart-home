package com.saferide.client.service;

import com.saferide.common.ServerResponse;
import com.saferide.entity.AirConditioner;
import com.saferide.entity.Light;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author saferide
 * @since 2021-04-30
 */
public interface LightService {

    ServerResponse lowBrightEvent(String id);

    ServerResponse highBrightEvent(String id);

    ServerResponse setTemperatureEvent(String id, Integer temperature);

    Light getById(String id);
}
