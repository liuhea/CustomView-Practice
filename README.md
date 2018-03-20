# 自定义控件 CustomView

## 流程
 1. 准备阶段-加载阶段
 2. 测量阶段-规划大小
 3. 布局阶段-绘制位置
 4. 绘制阶段-画
 
## 加载阶段
自定义view构造函数:用于初始化加载数据，属于自定义view第一个阶段。

``` 
    //用于创建自定义控件实例
    public TextView(Context context) {
         this(context, null);
     }
    //用于将当前自定义控件声明在布局文件中
    public TextView(Context context, @Nullable AttributeSet attrs) {
     this(context, attrs, com.android.internal.R.attr.textViewStyle);
    }
    //用于将当前自定义控件声明在布局文件中，并且加入样式style
    public TextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
     this(context, attrs, defStyleAttr, 0);
    }
    public TextView( Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(); 
    }
    
    init(){
    //初始化数据
    }
```



## 其它问题
1. 自定义控件中的onMeasure和onLayout执行两次？

```
    03-21 01:15:49.693 23928-23928/com.liuhe.customviewpractice D/liuhe->d: onResume()执行完毕
    03-21 01:15:49.737 23928-23928/com.liuhe.customviewpractice D/liuhe->d: onMeasure
    03-21 01:15:49.771 23928-23928/com.liuhe.customviewpractice D/liuhe->d: onLayout
    03-21 01:15:49.832 23928-23928/com.liuhe.customviewpractice D/liuhe->d: onMeasure
    03-21 01:15:49.833 23928-23928/com.liuhe.customviewpractice D/liuhe->d: onLayout
    03-21 01:15:49.836 23928-23928/com.liuhe.customviewpractice D/liuhe->d: onDraw
```
测量的时候父控件的onMeasure方法会遍历他所有的子控件，挨个调用子控件的measure方法，measure方法会调用onMeasure，
然后会调用setMeasureDimension方法保存测量的大小，一次遍历下来，第一个子控件以及这个子控件中的所有子控件都会完成
测量工作；然后开始测量第二个子控件…；最后父控件所有的子控件都完成测量以后会调用setMeasureDimension方法保存自己的
测量大小。值得注意的是，这个过程不只执行一次，也就是说有可能重复执行，因为有的时候，一轮测量下来，父控件发现某一个子控
件的尺寸不符合要求，就会重新测量一遍。