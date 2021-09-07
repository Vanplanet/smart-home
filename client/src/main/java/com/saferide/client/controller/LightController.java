package com.saferide.client.controller;

import com.saferide.client.service.LightService;
import com.saferide.common.ServerResponse;
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
@RequestMapping("/light")
public class LightController {

    private final LightService lightService;

    @PutMapping("/lowBright/{id}")
    public ServerResponse lowBright(@PathVariable("id") String id) {
        return lightService.lowBrightEvent(id);
    }

    @PutMapping("/highBright/{id}")
    public ServerResponse highBright(@PathVariable("id") String id) {
        return lightService.highBrightEvent(id);
    }

    @PutMapping("/setTemperature")
    public ServerResponse setTemperature(@RequestParam("id") String id, @RequestParam("temperature") Integer temperature) {
        return lightService.setTemperatureEvent(id,temperature);
    }

    @GetMapping("/light/{id}")
    public ServerResponse listLight(@PathVariable("id") String id) {
        return ServerResponse.createBySuccess(lightService.getById(id));
    }
}
