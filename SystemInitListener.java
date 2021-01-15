package org.jeecg.modules.init;

import lombok.extern.slf4j.Slf4j;
import org.jeecg.common.constant.CacheConstant;
import org.jeecg.modules.system.service.ISysGatewayRouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.core.Ordered;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @desc: 启动程序，初始化路由配置
 * @author: flyme
 */
@Slf4j
@Component
public class SystemInitListener implements ApplicationListener<ApplicationReadyEvent>, Ordered {

    @Autowired
    Environment environment;

    private String getPort(){
        return environment.getProperty("local.server.port");
    }

    @Autowired
    private ISysGatewayRouteService sysGatewayRouteService;

    @Override
    public void onApplicationEvent(ApplicationReadyEvent applicationReadyEvent) {

        log.info(" 服务已启动，准备初始化路由配置 ###################");
        if (applicationReadyEvent.getApplicationContext().getDisplayName().indexOf("AnnotationConfigServletWebServerApplicationContext") > -1) {
            sysGatewayRouteService.addRoute2Redis(CacheConstant.GATEWAY_ROUTES);
        }
        Runtime run = Runtime.getRuntime();
        if (getPort().equals("7001")){
            // 执行命令行命令kill 7002上的服务
            try {
                log.info("执行命令行命令kill端口7002上的服务");
                run.exec("./kill-old-server.sh 7002");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else if (getPort().equals("7002")){
            // 执行命令行命令kill 7001上的服务
            try {
                log.info("执行命令行命令kill端口7001上的服务");
                run.exec("./kill-old-server.sh 7001");
            } catch (IOException e) {
                e.printStackTrace();
            };
        }
    }

    @Override
    public int getOrder() {
        return 1;
    }
}
