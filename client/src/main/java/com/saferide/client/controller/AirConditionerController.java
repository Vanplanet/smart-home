package com.saferide.client.controller;


import com.saferide.client.service.AirConditionerService;
import com.saferide.common.ServerResponse;
import com.saferide.entity.AirConditioner;
import com.saferide.entity.AirConditioner;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author saferide
 * @since 2021-04-30
 */
@RestController
@AllArgsConstructor
@RequestMapping("/air-conditioner")
public class AirConditionerController {

    private final AirConditionerService airConditionerService;

    @PutMapping("/lowTemperature/{id}")
    public ServerResponse lowTemperature(@PathVariable("id") String id) {
        return airConditionerService.lowTemperatureEvent(id);
    }

    @PutMapping("/highTemperature/{id}")
    public ServerResponse highTemperature(@PathVariable("id") String id) {
        return airConditionerService.highTemperatureEvent(id);
    }

    @PutMapping("/lowSpeed/{id}")
    public ServerResponse lowSpeed(@PathVariable("id") String id) {
        return airConditionerService.lowSpeedEvent(id);
    }

    @PutMapping("/highSpeed/{id}")
    public ServerResponse highSpeed(@PathVariable("id") String id) {
        return airConditionerService.highSpeedEvent(id);
    }

    @PutMapping("/setType")
    public ServerResponse setType(@RequestParam("id") String id, @RequestParam("type") String type) {
        return airConditionerService.setTypeEvent(id, type);
    }

    @GetMapping("/AirConditioner/{id}")
    public ServerResponse listAirConditioner(@PathVariable("id") String id) {
        return ServerResponse.createBySuccess(airConditionerService.getById(id));
    }
}
