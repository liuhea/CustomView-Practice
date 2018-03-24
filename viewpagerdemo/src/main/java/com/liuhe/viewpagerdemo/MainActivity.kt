package com.liuhe.viewpagerdemo

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.app.FragmentStatePagerAdapter
import android.support.v4.view.PagerAdapter
import android.support.v4.view.ViewPager
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.liuhe.kotlinutilslib.log
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    val name by lazy {
        "liuhe"
    }
    var imageList = mutableListOf<ImageView>()
    var fragments = mutableListOf<Fragment>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        for (i in 0 until 3) {
            val img = ImageView(this).apply { setImageResource(R.mipmap.ic_launcher) }
            imageList.add(img)
        }

        for (i in 0 until 3) {
            val fragment = LazyFragment(i)
            fragments.add(fragment)
        }

        name.log()
//        vp_main_container.adapter = MyAdapter(imageList)
//        vp_main_container.adapter = MyFragAdapter(supportFragmentManager, fragments)
        vp_main_container.adapter = MyFragStateAdapter(supportFragmentManager, fragments)
    }
}

class MyAdapter(private val images: List<ImageView>) : PagerAdapter() {
    override fun isViewFromObject(view: View?, `object`: Any?): Boolean {
        return view == `object`
    }

    override fun getCount(): Int {
        return images.size
    }

    // 添加item
    override fun instantiateItem(container: ViewGroup?, position: Int): Any {
        (container as ViewPager)?.addView(images[position])
        "add Item $position".log()
        return images[position]
    }

    // 销毁item
    override fun destroyItem(container: ViewGroup?, position: Int, `object`: Any?) {
        "remove  Item $position".log()
        container?.removeView(`object` as View?)
    }
}

/**
 * Framgment Adapter
 * 预加载三个
 */
class MyFragAdapter(fm: FragmentManager, var lists: List<Fragment>) : FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment = lists[position]

    override fun getCount(): Int = lists.size

    override fun instantiateItem(container: ViewGroup?, position: Int): Any {
        "add Item $position".log()
        return super.instantiateItem(container, position)
    }

    override fun destroyItem(container: ViewGroup?, position: Int, `object`: Any?) {
        "remove  Item $position".log()
        super.destroyItem(container, position, `object`)
    }
}

class MyFragStateAdapter(fm: FragmentManager, var lists: List<Fragment>) : FragmentStatePagerAdapter(fm) {

    override fun getItem(position: Int): Fragment = lists[position]

    override fun getCount(): Int = lists.size

    override fun instantiateItem(container: ViewGroup?, position: Int): Any {
        "add Item $position".log()
        return super.instantiateItem(container, position)
    }

    override fun destroyItem(container: ViewGroup?, position: Int, `object`: Any?) {
        "remove  Item $position".log()
        super.destroyItem(container, position, `object`)
    }
}