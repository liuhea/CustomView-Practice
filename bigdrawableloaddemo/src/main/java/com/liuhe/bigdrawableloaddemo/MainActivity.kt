package com.liuhe.bigdrawableloaddemo

import android.content.res.Resources
import android.os.Bundle
import android.graphics.drawable.Drawable
import android.util.Log
import java.io.InputStream
import android.graphics.BitmapFactory
import com.liuhe.kotlinutilslib.log
import android.graphics.Bitmap
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*


/**
 * 加载14M大图
 * http://blog.csdn.net/guolin_blog/article/details/9316683
 * http://blog.csdn.net/lmj623565791/article/details/49300989/
 * http://blog.csdn.net/smileiam/article/details/68946182
 *
 * 1. 查看图片尺寸
 * 2. 预估一下加载整张图片所需占用的内存。
 * 3. 为了加载一张图片愿意提供多少内存。
 * 4. 用于展示这张图片控件的实际大小
 * 5. 当前设备的屏幕尺寸和分辨率
 */
class MainActivity : AppCompatActivity() {

    private val TAG = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val maxMemory = (Runtime.getRuntime().maxMemory() / (1024 * 1024)).toInt()
        txt_main_maxsize.text = "MaxMemorySize=$maxMemory M"

        checkImg()
        checkImageView()
//        img_main_horse.setImageBitmap(
//                decodeSampledBitmapFromResource(getResources(), R.id.myimage, 100, 100));

        btn_main_load_origin.setOnClickListener({
            loadImgFromAssets()
        })
        btn_main_load_decoder.setOnClickListener({
            loadImgBitmapRegionDecoder()
        })
    }

    /**
     *[1]查看图片尺寸
     *
     * BitmapFactory这个类提供了多个解析方法(decodeByteArray, decodeFile, decodeResource等)
     * 用于创建Bitmap对象，我们应该根据图片的来源选择合适的方法。
     *
     * BitmapFactory.Options中inJustDecodeBounds属性设置为true时，可以让解析方法禁止为bitmap分配内存，
     * 返回值也不再是一个Bitmap对象，而是null。虽然Bitmap是null，但是BitmapFactory.Options的outWidth、outHeight和outMimeType属性都会被赋值。
     */
    private fun checkImg() {

        val ims = assets.open("horse.jpg") as InputStream
        val options = BitmapFactory.Options()
        options.inJustDecodeBounds = true
        BitmapFactory.decodeStream(ims, null, options)
        val imageHeight = options.outHeight
        val imageWidth = options.outWidth
        val imageType = options.outMimeType

        "[1].图片尺寸 imageWidth=$imageWidth,imageHeight=$imageHeight,imageType=$imageType".log()
//        imageWidth=11821,imageHeight=1378,imageType=image/jpeg
    }

    /**
     * [2] 控件尺寸及屏幕分辨率
     * 在onCreate()获取view的width和height会得到0.view.getWidth()和view.getHeight()为0的根本原因是
     * 控件还没有完成绘制，你必须等待系统将绘制完View时，才能获得。
     * 这种情况当你需要使用动态布局（使用wrap_content或match_parent）就会出现。
     * 一般来讲在Activity.onCreate(...)、onResume()方法中都没有办法获取到View的实际宽高。
     */
    private fun checkImageView() {
        var imageWidth = 0
        var imageHeight = 0
        img_main_horse.post {
            Runnable {
                "[2].控件尺寸 imageViewWidth=$imageWidth,imageViewHeight=$imageHeight".log()
            }
        }
    }

    private fun loadImgBitmapRegionDecoder() {
        val inputStream = assets.open("horse.jpg") as InputStream
        img_main_horse.setInputStream(inputStream)
    }

    private fun loadImgFromAssets() {
        try {
            // get input stream
            val ims = assets.open("horse.jpg") as InputStream
            // load image as Drawable
            val d = Drawable.createFromStream(ims, null) as Drawable
            // set image to ImageView
//            img_main_horse.setImageDrawable(d)
            ims.close()
        } catch (ex: Exception) {
            Log.e(TAG, ex.message)
            // java.lang.OutOfMemoryError: Failed to allocate a 65157364 byte allocation with 51456400 free bytes and 54MB until OOM
        }
    }

    private fun calculateInSampleSize(options: BitmapFactory.Options,
                                      reqWidth: Int, reqHeight: Int): Int {
        // 源图片的高度和宽度
        val height = options.outHeight
        val width = options.outWidth
        var inSampleSize = 1
        if (height > reqHeight || width > reqWidth) {
            // 计算出实际宽高和目标宽高的比率
            val heightRatio = Math.round(height.toFloat() / reqHeight.toFloat())
            val widthRatio = Math.round(width.toFloat() / reqWidth.toFloat())
            // 选择宽和高中最小的比率作为inSampleSize的值，这样可以保证最终图片的宽和高
            // 一定都会大于等于目标的宽和高。
            inSampleSize = if (heightRatio < widthRatio) heightRatio else widthRatio
        }
        return inSampleSize
    }

    private fun decodeSampledBitmapFromResource(res: Resources, resId: Int,
                                                reqWidth: Int, reqHeight: Int): Bitmap {
        // 第一次解析将inJustDecodeBounds设置为true，来获取图片大小
        val options = BitmapFactory.Options()
        options.inJustDecodeBounds = true
        // 调用上面定义的方法计算inSampleSize值
        options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight)
        // 使用获取到的inSampleSize值再次解析图片
        options.inJustDecodeBounds = false
        return BitmapFactory.decodeResource(res, resId, options)
    }
}

