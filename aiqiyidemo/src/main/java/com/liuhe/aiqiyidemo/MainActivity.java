package com.liuhe.aiqiyidemo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.liuhe.aiqiyidemo.bindview.BindView;
import com.liuhe.aiqiyidemo.bindview.BindViewUtils;

/**
 * @author liuhe
 */
public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    @BindView(R.id.txt_main_hello)
    TextView txt;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BindViewUtils.bindView(this);

        txt.setText("BindView Success");

        int[] array = new int[]{3, 7, 2, 9, 1, 4, 6, 8, 10, 5};

//        bubbleSort(array);
        selectSort(array);

        for (int i : array) {
            Log.e(TAG, String.valueOf(i));
        }
    }

    /**
     * 冒泡排序：效率低、实现简单
     * 每一趟将排序序列中最大元素移动最后,剩下的为新的待排序列,重复上述步骤。
     *
     * @param array
     */
    private static void bubbleSort(int[] array) {
        int t = 0;
        for (int i = 0; i < array.length - 1; i++) {
            for (int j = i + 1; j < array.length - i - 1; j++) {
                if (array[j] > array[j + 1]) {
                    t = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = t;
                }
            }
        }
    }

    /**
     * 选择排序：效率低、容易实现
     * 每一趟从待排序列中选择最小的元素放在已排序列的末尾。
     *
     * @param array
     */
    private static void selectSort(int[] array) {
        int t = 0;
        for (int i = 0; i < array.length - 1; i++) {
            for (int j = i + 1; j < array.length; j++) {
                if (array[i] > array[j]) {
                    t = array[i];
                    array[i] = array[j];
                    array[j] = t;
                }
            }
        }
    }

    /**
     * 快速排序
     * <p>
     * 从左边开始，将每个数与基准值比较，直到找到比基准值大的
     *
     * @param array
     */
    private static void quickSort(int[] array) {

    }
}
