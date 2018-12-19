package com.bar.common.custom.config;

import com.bar.common.custom.constant.ApplicationConfig;
import com.bar.common.redis.RedisUtil;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import redis.clients.jedis.JedisPoolConfig;

import javax.annotation.PostConstruct;

@Configuration
public class ApplicationConfiguration {

    @Autowired
    private ApplicationConfig config;

    /**
     * JedisPoolConfig 连接池
     * @return
     */
    @Bean
    public JedisPoolConfig jedisPoolConfig() {
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        // 最大空闲数
        jedisPoolConfig.setMaxIdle(config.maxIdle);
        // 连接池的最大数据库连接数
        jedisPoolConfig.setMaxTotal(config.maxTotal);
        // 最大建立连接等待时间
        jedisPoolConfig.setMaxWaitMillis(config.maxWaitMillis);
        // 逐出连接的最小空闲时间 默认1800000毫秒(30分钟)
        jedisPoolConfig.setMinEvictableIdleTimeMillis(config.minEvictableIdleTimeMillis);
        // 每次逐出检查时 逐出的最大数目 如果为负数就是 : 1/abs(n), 默认3
        jedisPoolConfig.setNumTestsPerEvictionRun(config.numTestsPerEvictionRun);
        // 逐出扫描的时间间隔(毫秒) 如果为负数,则不运行逐出线程, 默认-1
        jedisPoolConfig.setTimeBetweenEvictionRunsMillis(config.timeBetweenEvictionRunsMillis);
        // 是否在从池中取出连接前进行检验,如果检验失败,则从池中去除连接并尝试取出另一个
        jedisPoolConfig.setTestOnBorrow(config.testOnBorrow);
        // 在空闲时检查有效性, 默认false
        jedisPoolConfig.setTestWhileIdle(config.testWhileIdle);
        return jedisPoolConfig;
    }
    /**
     * 单机版配置
     * @Title: JedisConnectionFactory
     * @param @param jedisPoolConfig
     * @param @return
     * @return JedisConnectionFactory
     * @autor lpl
     * @date 2018年2月24日
     * @throws
     */
    @Bean
    public JedisConnectionFactory JedisConnectionFactory(JedisPoolConfig jedisPoolConfig){
        JedisConnectionFactory JedisConnectionFactory = new JedisConnectionFactory(jedisPoolConfig);
        //连接池
        JedisConnectionFactory.setPoolConfig(jedisPoolConfig);
        //IP地址
        JedisConnectionFactory.setHostName(config.redisUrl);
        //端口号
        JedisConnectionFactory.setPort(config.redisPort);
        //如果Redis设置有密码
        //JedisConnectionFactory.setPassword(password);
        //客户端超时时间单位是毫秒
        JedisConnectionFactory.setTimeout(config.timeOut);
        return JedisConnectionFactory;
    }

    /**
     * 实例化 RedisTemplate 对象
     *
     * @return
     */
    @Bean
    public RedisTemplate<String, Object> functionDomainRedisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        initDomainRedisTemplate(redisTemplate, redisConnectionFactory);
        return redisTemplate;
    }

    /**
     * 设置数据存入 redis 的序列化方式,并开启事务
     *
     * @param redisTemplate
     * @param factory
     */
    private void initDomainRedisTemplate(RedisTemplate<String, Object> redisTemplate, RedisConnectionFactory factory) {
        //如果不配置Serializer，那么存储的时候缺省使用String，如果用User类型存储，那么会提示错误User can't cast to String！
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashValueSerializer(new GenericJackson2JsonRedisSerializer());
        redisTemplate.setValueSerializer(new GenericJackson2JsonRedisSerializer());
        // 开启事务
        redisTemplate.setEnableTransactionSupport(true);
        redisTemplate.setConnectionFactory(factory);
    }
    /**
     * 注入封装RedisTemplate
     * @Title: redisUtil
     * @return RedisUtil
     * @autor lpl
     * @date 2017年12月21日
     * @throws
     */
    @Bean(name = "redisUtil")
    public RedisUtil redisUtil(RedisTemplate<String, Object> redisTemplate) {
        RedisUtil redisUtil = new RedisUtil();
        redisUtil.setRedisTemplate(redisTemplate);
        return redisUtil;
    }

    /**
     * redis 集群配置
     */

//    /**
//     * Redis集群的配置
//     * @return RedisClusterConfiguration
//     * @autor lpl
//     * @date 2017年12月22日
//     * @throws
//     */
//    @Bean
//    public RedisClusterConfiguration redisClusterConfiguration(){
//        RedisClusterConfiguration redisClusterConfiguration = new RedisClusterConfiguration();
//        //Set<RedisNode> clusterNodes
//        String[] serverArray = config.clusterNodes.split(",");
//
//        Set<RedisNode> nodes = new HashSet<RedisNode>();
//
//        for(String ipPort:serverArray){
//            String[] ipAndPort = ipPort.split(":");
//            nodes.add(new RedisNode(ipAndPort[0].trim(),Integer.valueOf(ipAndPort[1])));
//        }
//
//        redisClusterConfiguration.setClusterNodes(nodes);
//        redisClusterConfiguration.setMaxRedirects(config.mmaxRedirectsac);
//
//        return redisClusterConfiguration;
//    }
//    /**
//     * 配置工厂
//     * @Title: JedisConnectionFactory
//     * @param @param jedisPoolConfig
//     * @param @return
//     * @return JedisConnectionFactory
//     * @autor lpl
//     * @date 2017年12月22日
//     * @throws
//     */
//    @Bean
//    public JedisConnectionFactory JedisConnectionFactory(JedisPoolConfig jedisPoolConfig,RedisClusterConfiguration redisClusterConfiguration){
//        JedisConnectionFactory JedisConnectionFactory = new JedisConnectionFactory(redisClusterConfiguration,jedisPoolConfig);
//
//        return JedisConnectionFactory;
//    }

}
