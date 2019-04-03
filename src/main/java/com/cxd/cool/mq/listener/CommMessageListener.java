package com.cxd.cool.mq.listener;

import org.springframework.stereotype.Component;

import com.cxd.cool.domain.Address;

@Component
public class CommMessageListener extends AbstractMessageLIstener<Void, Address> {

    @Override
    public Void process(Address message) {
        return null;
    }

}
