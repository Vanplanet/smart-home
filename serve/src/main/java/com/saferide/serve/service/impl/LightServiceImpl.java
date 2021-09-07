package com.saferide.serve.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.saferide.common.ServerResponse;
import com.saferide.entity.Light;
import com.saferide.serve.common.CommonConstants;
import com.saferide.serve.event.EventHandle;
import com.saferide.serve.mapper.LightMapper;
import com.saferide.serve.service.LightService;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author saferide
 * @since 2021-04-30
 */
@Service
public class LightServiceImpl extends ServiceImpl<LightMapper, Light> implements LightService {

    private final EventHandle lightHandle;

    public LightServiceImpl() {
        this.lightHandle = new EventHandle();
        this.lightHandle.setEventHandler(this);
    }

    @Override
    public void consumerEvent(ConcurrentLinkedQueue<Map<String, Object>> queue) {
        // 处理全部事件
        while (!queue.isEmpty()) {
            Map<String, Object> map = queue.peek();
            // 处理事件
            Set<String> set = map.keySet();
            queue.remove();
            // 遍历操作
            set.forEach(action -> {
                switch (action) {
                    case CommonConstants
                            .LIGHT_BRIGHT_LOW:
                        System.out.println("consumer " + CommonConstants.LIGHT_BRIGHT_LOW);
                        this.lowBright((String) map.get(action));
                        break;
                    case CommonConstants
                            .LIGHT_BRIGHT_HIGH:
                        System.out.println("consumer " + CommonConstants.LIGHT_BRIGHT_HIGH);
                        this.highBright((String) map.get(action));
                        break;
                    case CommonConstants
                            .LIGHT_TEMPERATURE:
                        System.out.println("consumer " + CommonConstants.LIGHT_TEMPERATURE);
                        Light light = (Light) map.get(action);
                        this.setTemperature(light.getId(), light.getTemperature());
                        break;
                    default:
                }
            });
        }
    }

    @Override
    public ServerResponse lowBrightEvent(String id) {
        // 创建事件(使用LinkedHashMap保证操作顺序)
        Map<String, Object> actions = new LinkedHashMap<>();
        actions.put(CommonConstants.LIGHT_BRIGHT_LOW, id);
        // 发布事件到队列并执行
        lightHandle.eventTrigger(actions);
        return ServerResponse.createBySuccess();
    }

    @Override
    public ServerResponse lowBright(String id) {
        // 休眠模拟调用
        try {
            TimeUnit.MILLISECONDS.sleep(CommonConstants.SLEEP_TIME);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Light light = this.getById(id);
        if (null == light) {
            return ServerResponse.createByErrorMessage("id为" + id + "的数据不存在");
        }
        // 更新
        light.setBright(light.getBright() - 10);
        this.updateById(light);
        return ServerResponse.createBySuccess(light);
    }

    @Override
    public ServerResponse highBrightEvent(String id) {
        // 创建事件(使用LinkedHashMap保证操作顺序)
        Map<String, Object> actions = new LinkedHashMap<>();
        actions.put(CommonConstants.LIGHT_BRIGHT_HIGH, id);
        // 发布事件到队列并执行
        lightHandle.eventTrigger(actions);
        return ServerResponse.createBySuccess();
    }

    @Override
    public ServerResponse highBright(String id) {
        // 休眠模拟调用
        try {
            TimeUnit.MILLISECONDS.sleep(CommonConstants.SLEEP_TIME);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Light light = this.getById(id);
        if (null == light) {
            return ServerResponse.createByErrorMessage("id为" + id + "的数据不存在");
        }
        // 更新
        light.setBright(light.getBright() + 10);
        this.updateById(light);
        return ServerResponse.createBySuccess(light);
    }

    @Override
    public ServerResponse setTemperatureEvent(String id, Integer temperature) {
        // 创建事件(使用LinkedHashMap保证操作顺序)
        Map<String, Object> actions = new LinkedHashMap<>();
        Light light = new Light();
        light.setId(id);
        light.setTemperature(temperature);
        actions.put(CommonConstants.LIGHT_TEMPERATURE, light);
        // 发布事件到队列并执行
        lightHandle.eventTrigger(actions);
        return ServerResponse.createBySuccess();
    }

    @Override
    public ServerResponse setTemperature(String id, Integer temperature) {
        // 休眠模拟调用
        try {
            TimeUnit.MILLISECONDS.sleep(CommonConstants.SLEEP_TIME);
        } catch (InterruptedException e) {
            e.printStackTrace();
            e.printStackTrace();
        }
        Light light = this.getById(id);
        if (null == light) {
            return ServerResponse.createByErrorMessage("id为" + id + "的数据不存在");
        }
        // 更新
        light.setTemperature(temperature);
        this.updateById(light);
        return ServerResponse.createBySuccess(light);
    }
}
