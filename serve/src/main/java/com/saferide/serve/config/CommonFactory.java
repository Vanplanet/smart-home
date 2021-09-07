package com.saferide.serve.config;

import com.saferide.serve.event.EventHandle;
import com.saferide.serve.service.AirConditionerService;
import com.saferide.serve.service.LightService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.remoting.caucho.HessianServiceExporter;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class CommonFactory {

    private final LightService lightService;
    private final AirConditionerService airConditionerService;

    //向外暴露lightService服务
    @Bean(name = "/lightService")
    public HessianServiceExporter lightServiceExporter() {
        // 使用Spring的HessianServie做代理
        HessianServiceExporter hessianServiceExporter = new HessianServiceExporter();
        hessianServiceExporter.setService(lightService);
        hessianServiceExporter.setServiceInterface(LightService.class);
        return hessianServiceExporter;
    }

    //向外暴露airConditionerService服务
    @Bean(name = "/airConditionerService")
    public HessianServiceExporter airConditionerServiceExporter() {
        // 使用Spring的HessianServie做代理
        HessianServiceExporter hessianServiceExporter = new HessianServiceExporter();
        hessianServiceExporter.setService(airConditionerService);
        hessianServiceExporter.setServiceInterface(AirConditionerService.class);
        return hessianServiceExporter;
    }
}
