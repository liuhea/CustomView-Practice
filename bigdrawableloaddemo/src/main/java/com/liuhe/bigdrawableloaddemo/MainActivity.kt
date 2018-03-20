package com.liuhe.bigdrawableloaddemo

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import android.graphics.drawable.Drawable
import android.util.Log
import java.io.InputStream
import android.graphics.BitmapFactory
import com.liuhe.kotlinutilslib.log

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
        var imageWidth=0
        var imageHeight=0

        img_main_horse.post {
            Runnable {
                "[2].控件尺寸 imageViewWidth=$imageWidth,imageViewHeight=$imageHeight".log()
            }
        }
    }

    private fun loadImgBitmapRegionDecoder() {

    }

    private fun loadImgFromAssets() {
        try {
            // get input stream
            val ims = assets.open("horse.jpg") as InputStream
            // load image as Drawable
            val d = Drawable.createFromStream(ims, null) as Drawable
            // set image to ImageView
            img_main_horse.setImageDrawable(d)
            ims.close()
        } catch (ex: Exception) {
            Log.e(TAG, ex.message)
            // java.lang.OutOfMemoryError: Failed to allocate a 65157364 byte allocation with 51456400 free bytes and 54MB until OOM
        }
    }


}

