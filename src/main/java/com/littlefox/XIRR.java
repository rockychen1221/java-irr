package com.littlefox;

import java.math.BigDecimal;
import java.math.MathContext;
import java.time.LocalDate;
import java.util.List;

import static java.time.temporal.ChronoUnit.DAYS;

public class XIRR{

    //此处可调整 (365/366)
    private final int cycle =365;

    private List<CashFlow> cashFlowList;

    public XIRR(List<CashFlow> cashFlowList){
        this.cashFlowList=cashFlowList;
    }

    /**
     *  计算 XIRR
     *  【参考列表】
     *  IRR Excel:https://support.office.com/zh-cn/article/IRR-%E5%87%BD%E6%95%B0-64925eaa-9988-495b-b290-3ad0c163c1bc
     *  XIRR Excel:https://support.office.com/zh-cn/article/XIRR-%E5%87%BD%E6%95%B0-de1242ec-6477-445b-b11b-a303ad9adc9d
     * @param guess 猜测值
     * @return
     */
    public String xirr(double guess) {
        BigDecimal d = BigDecimal.valueOf(guess);
        BigDecimal tempAmont = null;
        if (!cashFlowList.isEmpty() && cashFlowList.get(0).getTime() != null) {
            int z = 0;
            int stepLimit = 0;
            BigDecimal step = BigDecimal.valueOf(0.1);
            MathContext mathContext = new MathContext(18);
            //最小时间
            LocalDate startTime = LocalDate.parse(cashFlowList.get(0).getTime());

            while (true) {
                //第一笔金额
                tempAmont = new BigDecimal(cashFlowList.get(0).getMoney());
                for (int i = 1; i < cashFlowList.size(); i++) {
                    //当前记录金额
                    BigDecimal money = BigDecimal.valueOf(cashFlowList.get(i).getMoney());
                    //当前记录时间
                    LocalDate time = LocalDate.parse(cashFlowList.get(i).getTime());
                    //计算相隔多少天
                    long tian=DAYS.between(startTime, time);

                    BigDecimal days = BigDecimal.valueOf(tian) ;  /// (24 * 60 * 60 * 1000))
                    //根据天数计算年
                    double year = days.divide(BigDecimal.valueOf(cycle), mathContext).doubleValue();
                    //计算本次
                    BigDecimal a = BigDecimal.valueOf(Math.pow(d.add(BigDecimal.valueOf(1)).doubleValue(), year));
                    BigDecimal b = money.divide(a, mathContext);
                    tempAmont = tempAmont.add(b);
                }

                if (tempAmont.compareTo(BigDecimal.ZERO) > 0 && z == 0) {
                    step = step.divide(BigDecimal.valueOf(2), mathContext);
                    z = 1;
                }
                if (tempAmont.compareTo(BigDecimal.ZERO) < 0 && z == 1) {
                    step = step.divide(BigDecimal.valueOf(2), mathContext);
                    z = 0;
                }
                if (z == 0) {
                    d = d.subtract(step);
                } else {
                    d = d.add(step);
                }
                //统计计算次数，超过无结果则返回
                stepLimit = stepLimit + 1;
                if (stepLimit == 10000) {
                    return "NAN";
                }
                //结果趋近于0则返回结果
                if (Math.round(tempAmont.doubleValue()) == 0) {
                    break;
                }
            }
        }
        return d.toString();
    }
}