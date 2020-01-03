package com.littlefox;

import com.littlefox.xirr.Transaction;
import com.littlefox.xirr.Xirr;

import java.util.List;

public class IRRUtil {

    /**
     * 默认猜测值
     */
    private static final double X0 = 0.5D;

    /**
     *  计算 XIRR
     *  【参考列表】
     *  IRR Excel:https://support.office.com/zh-cn/article/IRR-%E5%87%BD%E6%95%B0-64925eaa-9988-495b-b290-3ad0c163c1bc
     *  XIRR Excel:https://support.office.com/zh-cn/article/XIRR-%E5%87%BD%E6%95%B0-de1242ec-6477-445b-b11b-a303ad9adc9d
     * @param list 现金流
     * @param days 计算周期
     * @return
     */
    public static Double xirr(List<Transaction> list, double days) {
        return xirr(list,days,X0);
    }

    /**
     * XIRR
     * @param list 现金流
     * @param days 计算周期
     * @param guess 猜测值
     * @return
     */
    public static Double xirr(List<Transaction> list, double days, double guess) {
        return new Xirr(list,days,guess).xirr();
    }

    /**
     * IRR
     * @param list
     * @return
     */
    public static Double irr(List<Transaction> list) {
        return irr(list,X0);
    }

    /**
     * 计算IRR
     * 【参考列表】
     *  IRR Excel:https://support.office.com/zh-cn/article/IRR-%E5%87%BD%E6%95%B0-64925eaa-9988-495b-b290-3ad0c163c1bc
     *  XIRR Excel:https://support.office.com/zh-cn/article/XIRR-%E5%87%BD%E6%95%B0-de1242ec-6477-445b-b11b-a303ad9adc9d
     *  @param list 现金流
     *  @param guess 猜测值
     */
    public static Double irr(List<Transaction> list, double guess) {
        Double irrResult = 0.0D / 0.0D;
        if (!list.isEmpty()) {
            int maxIterationCount = 20;
            double absoluteAccuracy = 1.0E-7D;
            double fValue, fDerivative, x1;
            for (int i = 0; i < maxIterationCount; ++i) {
                fValue = 0.0D;
                fDerivative = 0.0D;
                for (int k = 0; k < list.size(); ++k) {
                    fValue += list.get(k).getAmount() / Math.pow(1.0D + guess, (double) k);
                    fDerivative += (double) (-k) * list.get(k).getAmount() / Math.pow(1.0D + guess, (double) (k + 1));
                }
                x1 = guess - fValue / fDerivative;
                if (Math.abs(x1 - guess) <= absoluteAccuracy) {
                    irrResult = x1;
                    break;
                }
                guess = x1;
            }
        }
        return irrResult;
    }

}
