package com.bar.common.service.impl;

import com.bar.common.bean.Secret;
import com.bar.common.config.SecretConstant;
import com.bar.common.custom.constant.ApplicationConfig;
import com.bar.common.mapper.SecretMapper;
import com.bar.common.service.SecretService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.annotation.PostConstruct;
import java.util.List;

@Lazy(false)
@Service
@Transactional
public class SecretServiceImpl implements SecretService {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private SecretMapper secretMapper;
    @Autowired
    private ApplicationConfig config;



    @PostConstruct
    @Override
    public void refreshSecret() {
        List<Secret> secrets = secretMapper.findAllByReceiver(config.systemName);
        for (Secret secret : secrets) {
            SecretConstant.secret.put(secret.getSecret(),secret.getTransmi());
        }
        logger.info("秘钥初始化完毕");
    }
}
