package com.bar.common.custom.constant;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ApplicationConfig {

    @Value("${system.name}")
    public String systemName;

    @Value("${jms.mq.queue}")
    public String queueName;

    @Value("${jms.mq.topic}")
    public String topicName;

    @Value("${jms.mq.username}")
    public String mqUserName;

    @Value("${jms.mq.password}")
    public String mqPassword;

    @Value("${jms.mq.connect}")
    public String mqConnect;

    @Value("${redis.url}")
    public String redisUrl;

    @Value("${redis.port}")
    public Integer redisPort;

    @Value("${redis.timeout}")
    public Integer timeOut;

    @Value("${redis.maxIdle}")
    public Integer maxIdle;

    @Value("${redis.maxTotal}")
    public Integer maxTotal;

    @Value("${redis.maxWaitMillis}")
    public Integer maxWaitMillis;

    @Value("${redis.minEvictableIdleTimeMillis}")
    public Integer minEvictableIdleTimeMillis;

    @Value("${redis.numTestsPerEvictionRun}")
    public Integer numTestsPerEvictionRun;

    @Value("${redis.timeBetweenEvictionRunsMillis}")
    public long timeBetweenEvictionRunsMillis;

    @Value("${redis.testOnBorrow}")
    public boolean testOnBorrow;

    @Value("${redis.testWhileIdle}")
    public boolean testWhileIdle;


    @Value("${spring.redis.cluster.nodes}")
    public String clusterNodes;

    @Value("${spring.redis.cluster.max-redirects}")
    public Integer mmaxRedirectsac;

}
