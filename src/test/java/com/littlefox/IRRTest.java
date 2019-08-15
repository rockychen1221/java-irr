package com.littlefox;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Unit test for simple IRR.
 */
public class IRRTest {

    @Test
    public void IRR() {
        Double [] arrays= {-700000.0,120000.0,150000.0,180000.0,210000.0,260000.0};

        List<CashFlow> list= Arrays.asList(arrays).stream().map(x ->{
            return new CashFlow(x,null);
        }).collect(Collectors.toList());

        System.out.println(new IRR(list).irr(0.5D));
    }

    @Test
    public void XIRR() {
        List<CashFlow> list =new ArrayList(){{
            add(new CashFlow(-10000,"2018-01-01"));
            add(new CashFlow(2750,"2018-03-01"));
            add(new CashFlow(4250,"2018-10-30"));
            add(new CashFlow(3250,"2009-02-15"));
            add(new CashFlow(2750,"2009-04-01"));
        }};
        System.out.println(new XIRR(list).xirr(0.5D));
    }
}
