package com.cxd.cool.action;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cxd.cool.domain.Address;

@RestController
@RequestMapping(value = "/member22")
public class TestAction2 {
    
    @RequestMapping(value = "/test")
    public Address testok() {
        Address aa = new Address();
        aa.setAddname("nj");
        aa.setCity("cc");

        try {
            Thread.sleep(10 * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return aa;
    }
    
    
    @RequestMapping(value = "/address")
    public Address address() {
        Address aa = new Address();
        aa.setAddname("add1");
        aa.setCity("add2");
        try {
            Thread.sleep(10 * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return aa;
    }

}
