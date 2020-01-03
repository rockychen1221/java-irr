# java-irr

java版的IRR和XIRR，实现内部收益率的计算

XIRR与IRR都是可以反映一组现金流的内部收益率的，但两者还是有一些区别：

1、适用范围不同：
  XIRR适用间隔相等现金流内部收益率计算，这个现金流不一定定期发生。XIRR法适用于不定期的投入方式。因为这种方法把每期投入的现金流动首先按投入日期与第一期的日期天数差，折算到”等时间间隔为1天“，然后再进一步折算到”年化收益率“。所以，XIRR法计算出的直接就是年化收益率了

  IRR适用间隔相等或不相等的现金流内部收益率计算，现金流必须定期出现。IRR法仅仅适用于”定期“投入的方式，时间间隔如果不平均，就会产生错误，IRR得到的是期内部收益率，这里的期可以指年，半年，季，月，周，日

2、计算参数不同：XIRR数值是必须的，日期也是必须的。IRR可以输入预估值，日期可以不输入。

3、准确性不同：XIRR将日期计算在内，准确性会高一些。

## 参考

[IRR MIRR XIRR的区别](https://zhidao.baidu.com/question/1541287445672685307.html)

## 更新
引入牛顿算法并简单封装
https://github.com/RayDeCampo/java-xirr

加入gurss 、猜测值

## 测试数据全部来源于Microsoft官方

[Office IRR](https://support.office.com/zh-cn/article/IRR-%E5%87%BD%E6%95%B0-64925eaa-9988-495b-b290-3ad0c163c1bc)

[Office XIRR](https://support.office.com/zh-cn/article/XIRR-%E5%87%BD%E6%95%B0-de1242ec-6477-445b-b11b-a303ad9adc9d)

