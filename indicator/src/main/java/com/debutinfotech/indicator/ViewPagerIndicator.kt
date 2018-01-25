package com.debutinfotech.indicator

import android.content.Context
import android.database.DataSetObserver
import android.graphics.Color
import android.graphics.LightingColorFilter
import android.support.annotation.AttrRes
import android.support.annotation.ColorInt
import android.support.annotation.Dimension
import android.support.annotation.DrawableRes
import android.support.v4.view.PagerAdapter
import android.support.v4.view.ViewPager
import android.util.AttributeSet
import android.util.TypedValue
import android.view.Gravity
import android.widget.ImageView
import android.widget.LinearLayout


/* Author: Lakhwinder Singh
 * Email: lakhwinder.singh@debutinfotech.com
 *
 *  Copyright (c) 2017 Lakhwinder Singh
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *  http://www.apache.org/licenses/LICENSE-2.0
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *
 */
class ViewPagerIndicator : LinearLayout, ViewPager.OnPageChangeListener, ViewPager.OnAdapterChangeListener {
    private var mContext: Context? = null
    private var mPager: ViewPager? = null

    @ColorInt
    private var mSelectedColor = -1
    @ColorInt
    private var mDeselectedColor = -1
    @DrawableRes
    private var mSelectedDrawable = -1
    @DrawableRes
    private var mDeselectedDrawable = -1
    @Dimension
    private var mIndicatorSpacing = 5
    // Page Count shown
    private var mPageCount = -1
    private val mDatasetObserver = object : DataSetObserver() {
        override fun onChanged() {
            super.onChanged()
            initializeIndicatorBar(mPageCount)
        }
    }


    constructor(context: Context) : super(context) {
        initializeViews(context, null)
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        initializeViews(context, attrs)
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        initializeViews(context, attrs)
    }

    /**
     * Set this after setting the adapter to the pager.
     *
     * @param pager the connected viewpager
     */
    fun setPager(pager: ViewPager) {
        if (mPager != null) {
            mPager!!.removeOnPageChangeListener(this)
            mPager!!.removeOnAdapterChangeListener(this)
            mPager = null
        }

        mPager = pager
        initializeIndicatorBar(mPageCount)
        mPager!!.addOnPageChangeListener(this)
        mPager!!.addOnAdapterChangeListener(this)
        mPager!!.adapter.registerDataSetObserver(mDatasetObserver)

    }

    private fun initializeViews(context: Context, attrs: AttributeSet?) {
        mContext = context

        if (attrs != null) {
            val a = context.obtainStyledAttributes(attrs, R.styleable.ViewPagerIndicator)

            mSelectedColor = a.getColor(R.styleable.ViewPagerIndicator_selectedColor, getThemeColor(context, R.attr.colorAccent))
            mDeselectedColor = a.getColor(R.styleable.ViewPagerIndicator_deselectedColor, Color.LTGRAY)
            mSelectedDrawable = a.getResourceId(R.styleable.ViewPagerIndicator_selectedDrawable, -1)
            mDeselectedDrawable = a.getResourceId(R.styleable.ViewPagerIndicator_deselectedDrawable, -1)
            mIndicatorSpacing = a.getDimension(R.styleable.ViewPagerIndicator_indicatorSpacing, 5f).toInt()
            mPageCount = a.getInteger(R.styleable.ViewPagerIndicator_pageCount, 1)
            a.recycle()
        }
    }

    @ColorInt
    private fun getThemeColor(context: Context, @AttrRes attributeColor: Int): Int {
        val value = TypedValue()
        context.theme.resolveAttribute(attributeColor, value, true)

        return value.data
    }

    /**
     * Set Page Count after initialization,
     * if pages are created on some response
     *
     * @param pageCount count
     */
    fun setPageCount(pageCount: Int) {
        mPageCount = pageCount
        initializeIndicatorBar(mPageCount)
    }

    private fun initializeIndicatorBar(num: Int) {
        removeAllViewsInLayout()

        for (i in 0 until num) {
            val img = ImageView(mContext)

            val lp = LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT)
            lp.setMargins(mIndicatorSpacing / 2, 0, mIndicatorSpacing / 2, 0)
            lp.gravity = Gravity.CENTER_VERTICAL or Gravity.CENTER_HORIZONTAL
            addView(img, lp)
        }

        setSelectedIndicator(mPager!!.currentItem % mPageCount)
    }

    private fun setSelectedIndicator(selected: Int) {
        val num = childCount

        for (i in  0 until num) {
            val img = getChildAt(i) as ImageView

            img.clearColorFilter()

            if (mDeselectedDrawable != -1) {
                img.setImageResource(mDeselectedDrawable)
            } else if (mSelectedDrawable != -1) {
                img.setImageResource(mSelectedDrawable)
                img.colorFilter = LightingColorFilter(0, mDeselectedColor)
            } else {
                img.setImageResource(R.drawable.rectangle_drawable)
                img.colorFilter = LightingColorFilter(0, mDeselectedColor)
            }
        }

        val selectedView = getChildAt(selected) as ImageView

        if (mSelectedDrawable != -1) {
            selectedView.clearColorFilter()
            selectedView.setImageResource(mSelectedDrawable)
        } else {
            selectedView.colorFilter = LightingColorFilter(0, mSelectedColor)
        }
    }

    override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {}

    override fun onPageSelected(position: Int) {
        val actualPos = position % mPageCount
        setSelectedIndicator(actualPos)
    }

    override fun onPageScrollStateChanged(state: Int) {}

    override fun onAdapterChanged(viewPager: ViewPager, oldAdapter: PagerAdapter?, newAdapter: PagerAdapter?) {
        oldAdapter?.unregisterDataSetObserver(mDatasetObserver)
        if (newAdapter != null) {
            initializeIndicatorBar(mPageCount)
            newAdapter.registerDataSetObserver(mDatasetObserver)
        }
    }
}