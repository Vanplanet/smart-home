package com.saferide.serve.service.impl;

import com.saferide.common.ServerResponse;
import com.saferide.entity.AirConditioner;
import com.saferide.entity.Light;
import com.saferide.serve.common.CommonConstants;
import com.saferide.serve.event.EventHandle;
import com.saferide.serve.mapper.AirConditionerMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.saferide.serve.service.AirConditionerService;
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
public class AirConditionerServiceImpl extends ServiceImpl<AirConditionerMapper, AirConditioner> implements AirConditionerService {

    private final EventHandle airConditionerHandle;

    public AirConditionerServiceImpl() {
        this.airConditionerHandle = new EventHandle();
        this.airConditionerHandle.setEventHandler(this);
    }

    @Override
    public void consumerEvent(ConcurrentLinkedQueue<Map<String, Object>> queue) {
        // 处理全部事件
        while (!queue.isEmpty()) {
            //获取队列首元素并删除
            Map<String, Object> map = queue.remove();
            //获取map集合所有键名
            Set<String> set = map.keySet();
            //遍历操作
            set.forEach(action -> {
                switch (action) {
                    case CommonConstants
                            .AIR_CONDITIONER_TEMPERATURE_LOW:
                        System.out.println("consumer " + CommonConstants.AIR_CONDITIONER_TEMPERATURE_LOW);
                        this.lowTemperature((String) map.get(action));
                        break;
                    case CommonConstants
                            .AIR_CONDITIONER_TEMPERATURE_HIGH:
                        System.out.println("consumer " + CommonConstants.AIR_CONDITIONER_TEMPERATURE_HIGH);
                        this.highTemperature((String) map.get(action));
                        break;
                    case CommonConstants
                            .AIR_CONDITIONER_SPEED_LOW:
                        System.out.println("consumer " + CommonConstants.AIR_CONDITIONER_SPEED_LOW);
                        this.lowSpeed((String) map.get(action));
                        break;
                    case CommonConstants
                            .AIR_CONDITIONER_SPEED_HIGH:
                        System.out.println("consumer " + CommonConstants.AIR_CONDITIONER_SPEED_HIGH);
                        this.highSpeed((String) map.get(action));
                        break;
                    case CommonConstants
                            .AIR_CONDITIONER_MODE:
                        System.out.println("consumer " + CommonConstants.AIR_CONDITIONER_MODE);
                        AirConditioner airConditioner = (AirConditioner) map.get(action);
                        this.setType(airConditioner.getId(), airConditioner.getType());
                        break;
                    default:
                }
            });
        }
    }


    @Override
    public ServerResponse lowTemperatureEvent(String id) {
        // 创建事件(使用LinkedHashMap保证操作顺序)
        Map<String, Object> actions = new LinkedHashMap<>();
        actions.put(CommonConstants.AIR_CONDITIONER_TEMPERATURE_LOW, id);
        // 发布事件到队列并执行
        airConditionerHandle.eventTrigger(actions);
        return ServerResponse.createBySuccess();
    }
    @Override
    public ServerResponse lowTemperature(String id) {
        // 休眠模拟调用
        try {
            TimeUnit.MILLISECONDS.sleep(CommonConstants.SLEEP_TIME);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        AirConditioner airConditioner = this.getById(id);
        if (null == airConditioner) {
            return ServerResponse.createByErrorMessage("id为" + id + "的数据不存在");
        }
        // 更新
        airConditioner.setTemperature(airConditioner.getTemperature() - 1);
        this.updateById(airConditioner);
        return ServerResponse.createBySuccess(airConditioner);
    }

    @Override
    public ServerResponse highTemperatureEvent(String id) {
        // 创建事件(使用LinkedHashMap保证操作顺序)
        Map<String, Object> actions = new LinkedHashMap<>();
        actions.put(CommonConstants.AIR_CONDITIONER_TEMPERATURE_HIGH, id);
        // 发布事件到队列并执行
        airConditionerHandle.eventTrigger(actions);
        return ServerResponse.createBySuccess();
    }

    @Override
    public ServerResponse highTemperature(String id) {
        // 休眠模拟调用
        try {
            TimeUnit.MILLISECONDS.sleep(CommonConstants.SLEEP_TIME);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        AirConditioner airConditioner = this.getById(id);
        if (null == airConditioner) {
            return ServerResponse.createByErrorMessage("id为" + id + "的数据不存在");
        }
        // 更新
        airConditioner.setTemperature(airConditioner.getTemperature() + 1);
        this.updateById(airConditioner);
        return ServerResponse.createBySuccess(airConditioner);
    }

    @Override
    public ServerResponse lowSpeedEvent(String id) {
        // 创建事件(使用LinkedHashMap保证操作顺序)
        Map<String, Object> actions = new LinkedHashMap<>();
        actions.put(CommonConstants.AIR_CONDITIONER_SPEED_LOW, id);
        // 发布事件到队列并执行
        airConditionerHandle.eventTrigger(actions);
        return ServerResponse.createBySuccess();
    }

    @Override
    public ServerResponse lowSpeed(String id) {
        // 休眠模拟调用
        try {
            TimeUnit.MILLISECONDS.sleep(CommonConstants.SLEEP_TIME);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        AirConditioner airConditioner = this.getById(id);
        if (null == airConditioner) {
            return ServerResponse.createByErrorMessage("id为" + id + "的数据不存在");
        }
        // 更新
        airConditioner.setSpeed(airConditioner.getSpeed() - 1);
        this.updateById(airConditioner);
        return ServerResponse.createBySuccess(airConditioner);
    }

    @Override
    public ServerResponse highSpeedEvent(String id) {
        // 创建事件(使用LinkedHashMap保证操作顺序)
        Map<String, Object> actions = new LinkedHashMap<>();
        actions.put(CommonConstants.AIR_CONDITIONER_SPEED_HIGH, id);
        // 发布事件到队列并执行
        airConditionerHandle.eventTrigger(actions);
        return ServerResponse.createBySuccess();
    }

    @Override
    public ServerResponse highSpeed(String id) {
        // 休眠模拟调用
        try {
            TimeUnit.MILLISECONDS.sleep(CommonConstants.SLEEP_TIME);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        AirConditioner airConditioner = this.getById(id);
        if (null == airConditioner) {
            return ServerResponse.createByErrorMessage("id为" + id + "的数据不存在");
        }
        // 更新
        airConditioner.setSpeed(airConditioner.getSpeed() + 1);
        this.updateById(airConditioner);
        return ServerResponse.createBySuccess(airConditioner);
    }

    @Override
    public ServerResponse setTypeEvent(String id, String type) {
        // 创建事件(使用LinkedHashMap保证操作顺序)
        Map<String, Object> actions = new LinkedHashMap<>();
        AirConditioner airConditioner = new AirConditioner();
        airConditioner.setId(id);
        airConditioner.setType(type);
        actions.put(CommonConstants.AIR_CONDITIONER_MODE, airConditioner);
        // 发布事件到队列并执行
        airConditionerHandle.eventTrigger(actions);
        return ServerResponse.createBySuccess();
    }

    @Override
    public ServerResponse setType(String id, String type) {
        // 休眠模拟调用
        try {
            TimeUnit.MILLISECONDS.sleep(CommonConstants.SLEEP_TIME);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        AirConditioner airConditioner = this.getById(id);
        if (null == airConditioner) {
            return ServerResponse.createByErrorMessage("id为" + id + "的数据不存在");
        }
        // 更新
        airConditioner.setType(type);
        this.updateById(airConditioner);
        return ServerResponse.createBySuccess(airConditioner);
    }
}
