package com.littlefox;

import com.littlefox.xirr.Transaction;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Unit test for simple IRR.
 */
public class IRRTest {

    @Test
    public void XIRR() {
        List list = Arrays.asList(
                new Transaction(-10000,"2008-01-01"),
                new Transaction(3250,"2009-02-15"),
                new Transaction( 2750,"2009-04-01"),
                new Transaction(4250,"2008-10-30"),
                new Transaction(2750,"2008-03-01")
        );
        Assert.assertEquals("0.3733625335190808",IRRUtil.xirr(list,365).toString());
        Assert.assertEquals("0.3733625335188315",IRRUtil.xirr(list,365,0.1D).toString());
    }

    @Test
    public void IRR() {
        Double[] arrays= {-700000.0,120000.0,150000.0,180000.0,210000.0,260000.0};

        List<Transaction> list= Arrays.asList(arrays).stream().map(x ->{
            return new Transaction(x);
        }).collect(Collectors.toList());

        Assert.assertEquals("0.08663094803653153",IRRUtil.irr(list).toString());
        Assert.assertEquals("0.0866309480365316",IRRUtil.irr(list,0.3D).toString());
    }
}
