package com.liuhe.aiqiyidemo;

/**
 * 快速排序——JAVA实现（图文并茂）
 * https://blog.csdn.net/as02446418/article/details/47395867
 *
 * @author liuhe
 * @date 2018-04-09
 */
public class QuickSort {
    /**
     * 快排
     * quickSort(arr, 0, arr.length-1);
     *
     * @param arr
     * @param low
     * @param high
     */
    public static void quickSort(int[] arr, int low, int high) {
        int left, right, temp, t;
        if (low > high) {
            return;
        }
        left = low;
        right = high;
        // temp是基准位
        temp = arr[low];

        while (left < right) {
            // 先看右边，一次往左递减
            while (temp <= arr[right] && right < right) {
                right--;
            }
            // 再看左边，以此往右递增
            while (temp >= arr[left] && left < right) {
                left++;
            }
            // 如果满足条件则交换
            if (left < right) {
                t = arr[right];
                arr[right] = arr[left];
                arr[left] = t;
            }
        }

        //最后将基准位与i和j相等位置的数字交换
        arr[low] = arr[left];
        arr[left] = temp;
        // 递归调用左半边数组
        quickSort(arr, low, right - 1);
        //递归调用右半数组
        quickSort(arr, right + 1, high);
    }
}
