package com.rookies4.MySpringbootLab.Component;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class MyPropRunner implements ApplicationRunner {
    private static final Logger logger = LoggerFactory.getLogger(MyPropRunner.class);

    @Value("${myprop.username}")
    private String userName;
    @Value("${myprop.port}")
    private int port;

    private final MyPropProperties props;
    private final MyEnvironment env;

    public MyPropRunner(MyPropProperties props, MyEnvironment env) {
        this.props = props;
        this.env = env;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        logger.info("username: {} ", userName);
        logger.debug("port: {} ", port);
        logger.info("Environment: {}", env.getMode());
    }
}
