package com.liuhe.aiqiyidemo.bindview;

import android.app.Activity;

import com.liuhe.aiqiyidemo.R;

import java.lang.reflect.Field;

/**
 * 属性注解在使用的时候是需要通过反射获取属性实例并赋值的，我们在给View赋值的时候每次都需要做反射等操作。
 * <p>
 * 思路：通过反射拿到Activity中属性，判断是否使用了注解，如果使用，取出注解value并复制给view
 * 常用反射方法：
 * getMethod：获取类中的public方法
 * getDeclaredMethod：获取类中的所有方法
 * getField：获取类中的public字段（属性）
 * getDeclaredField：获取类中的所有字段
 *
 * @author liuhe
 * @create 2018-04-08
 */
public class BindViewUtils {

    public static void bindView(Activity activity) {
        try {
            // 通过getClass拿到activity实例
            Class<? extends Activity> clazz = activity.getClass();

            // 通过反射拿到类属性
            Field[] fields = clazz.getDeclaredFields();

            // 循环取出属性
            for (Field field : fields) {
                // 判断属性是否使用注解
                if (field.isAnnotationPresent(BindView.class)) {
                    // 如果使用就获取该类上的注解
                    BindView inject = field.getAnnotation(BindView.class);
                    int id = inject.value();
                    if (0 != id) {
                        // 提高发射速度
                        field.setAccessible(true);
                        // 给被注解属性赋值
                        field.set(activity, activity.findViewById(id));
                    }
                }
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
