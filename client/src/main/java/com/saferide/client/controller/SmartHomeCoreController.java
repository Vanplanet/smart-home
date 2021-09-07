package com.saferide.client.controller;

import com.saferide.client.service.AirConditionerService;
import com.saferide.client.service.LightService;
import com.saferide.common.ServerResponse;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/smart-home")
public class SmartHomeCoreController {

    private final LightService lightService;

    private final AirConditionerService airConditionerService;

    //降低亮度
    @PutMapping("/lowBright/{id}")
    public ServerResponse lowBright(@PathVariable("id") String id) {
        return lightService.lowBrightEvent(id);
    }

    //提高亮度
    @PutMapping("/highBright/{id}")
    public ServerResponse highBright(@PathVariable("id") String id) {
        return lightService.highBrightEvent(id);
    }

    //调节色温
    @PutMapping("/setTemperature")
    public ServerResponse setTemperature(@RequestParam("id") String id, @RequestParam("temperature") Integer temperature) {
        return lightService.setTemperatureEvent(id,temperature);
    }

    //降低温度
    @PutMapping("/lowTemperature/{id}")
    public ServerResponse lowTemperature(@PathVariable("id") String id) {
        return airConditionerService.lowTemperatureEvent(id);
    }

    //提高温度
    @PutMapping("/highTemperature/{id}")
    public ServerResponse highTemperature(@PathVariable("id") String id) {
        return airConditionerService.highTemperatureEvent(id);
    }

    //降低风速
    @PutMapping("/lowSpeed/{id}")
    public ServerResponse lowSpeed(@PathVariable("id") String id) {
        return airConditionerService.lowSpeedEvent(id);
    }

    //提高风速
    @PutMapping("/highSpeed/{id}")
    public ServerResponse highSpeed(@PathVariable("id") String id) {
        return airConditionerService.highSpeedEvent(id);
    }

    //切换模式
    @PutMapping("/setType")
    public ServerResponse setType(@RequestParam("id") String id, @RequestParam("type") String type) {
        return airConditionerService.setTypeEvent(id, type);
    }

    //查询灯的状态
    @GetMapping("/light/{id}")
    public ServerResponse listLight(@PathVariable("id") String id) {
        return ServerResponse.createBySuccess(lightService.getById(id));
    }

    //查询空调的状态
    @GetMapping("/AirConditioner/{id}")
    public ServerResponse listAirConditioner(@PathVariable("id") String id) {
        return ServerResponse.createBySuccess(airConditionerService.getById(id));
    }
}
