package com.liuhe.aiqiyidemo.bindview;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 自定义注解
 * 参考：https://www.cnblogs.com/Fndroid/p/5354644.html
 * <p>
 * -@Target的意思是我们注解作用的目标，这里是ElementType.FIELD，也就是作用于字段的
 * ElementType的类型有以下几种：
 * 1.CONSTRUCTOR:用于描述构造器
 * 　2.FIELD:用于描述字段
 * 　3.LOCAL_VARIABLE:用于描述局部变量
 * 　4.METHOD:用于描述方法
 * 　5.PACKAGE:用于描述包
 * 　6.PARAMETER:用于描述参数
 * 　7.TYPE:用于描述类、接口(包括注解类型) 或enum声明
 * -@Retention的意思是注解的运行级别 RetentionPolicy的类型有以下几种：
 * 1.SOURCE:在源文件中有效（即源文件保留）
 * 　2.CLASS:在class文件中有效（即class保留）
 * 　3.RUNTIME:在运行时有效（即运行时保留）
 * -@interface则是表明这个类是一个注解，@号不能漏掉，否则变成了接口了
 *
 * @author liuhe
 * @create 2018-04-08
 */
@Target(ElementType.FIELD) // 注解目标为域
@Retention(RetentionPolicy.RUNTIME) // 运行时注解
public @interface BindView {
    int value() default 0;//当前注解只需要一个值，所以我们用value 默认值为0
}

// 如果注解中的值不是value，那么在进行注解是时候，需要给出对应的值的名字，否则，会报npe异常，
// 假如我们在注解中做了如下定义：
// @Target(ElementType.FIELD)
//@Retention(RetentionPolicy.RUNTIME)
//public @interface  ViewInject{
//    int id();
//}
// 那么在注解中，x需要声明
//@ViewInject(id = R.id.buy)
//private Button buy;


