package com.himorithm.ignitespring.client.configuration;

import lombok.extern.slf4j.Slf4j;
import org.apache.ignite.IgniteException;
import org.apache.ignite.lifecycle.LifecycleBean;
import org.apache.ignite.lifecycle.LifecycleEventType;

@Slf4j
public class ClientLifeCycleBean implements LifecycleBean {
    @Override
    public void onLifecycleEvent(LifecycleEventType evt) throws IgniteException {
        switch (evt) {
            case AFTER_NODE_START:
                log.info("Node Started");
                break;
            case AFTER_NODE_STOP:
                log.info("Node Stopped");
                break;
            default:
        }
    }
}
