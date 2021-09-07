package com.saferide.client.config;

import com.saferide.client.service.AirConditionerService;
import com.saferide.client.service.LightService;
import com.saferide.client.utils.HessianProxyFactoryUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class ClientFactory {

    @Value("${hessian.baseUrl}")
    private String baseUrl;

    //关联服务
    @Bean
    public LightService lightServiceClient() {
        return HessianProxyFactoryUtil.getHessianClientBean(LightService.class, baseUrl + "/lightService");
    }

    //关联服务
    @Bean
    public AirConditionerService airConditionerServiceClient() {
        return HessianProxyFactoryUtil.getHessianClientBean(AirConditionerService.class, baseUrl + "/airConditionerService");
    }
}
