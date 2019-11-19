package com.uestc.medicine.ui

import android.animation.Animator
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.animation.AccelerateDecelerateInterpolator
import com.uestc.medicine.R
import com.uestc.medicine.base.BaseActivity
import com.uestc.medicine.util.get
import com.uestc.medicine.util.visible
import com.uestc.medicine.viewmodel.MenuViewModel
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import kotlinx.android.synthetic.main.activity_menu2.*


class MenuActivity : BaseActivity<MenuViewModel>() {


    override fun layoutId(): Int {
        return R.layout.activity_menu2
    }

    override fun initViewModel(): MenuViewModel {
        return get(MenuViewModel::class.java)
    }

    override fun initPage(savedInstanceState: Bundle?) {
        title = "主菜单"
        viewModel.loadMenu("1", "0")
        menu1.setOnClickListener(this::onMenuClick)
        menu2.setOnClickListener(this::onMenuClick)
        menu3.setOnClickListener(this::onMenuClick)
        menu4.setOnClickListener(this::onMenuClick)
        menu5.setOnClickListener(this::onMenuClick)
    }


    private fun onMenuClick(img: View) {
        getMenuId(img)?.let {
            var intent = Intent(this, MenuDetailsActivity::class.java)
            intent.putExtra("column_id", it)
            startActivity(intent)
        }
    }

    private fun getMenuId(img: View): String? {
        viewModel.menu.value?.let { menu ->
            menu.column?.let { datas ->
                if (img.id == menu1.id) {
                    return datas[0].column_id
                }
                if (img.id == menu2.id) {
                    return datas[1].column_id
                }
                if (img.id == menu3.id) {
                    return datas[2].column_id
                }
                if (img.id == menu4.id) {
                    return datas[3].column_id
                }
                return datas[4].column_id
            }
        }
        return null
    }

    private fun doAnimate(): Disposable {
        return Observable.just(getAnimate(menu1))
            .concatMap {
                it.start()
                return@concatMap Observable.just(getAnimate(menu2))
            }
            .concatMap {
                it.start()
                return@concatMap Observable.just(getAnimate(menu3))
            }
            .concatMap {
                it.start()
                return@concatMap Observable.just(getAnimate(menu4))
            }
            .concatMap {
                it.start()
                return@concatMap Observable.just(getAnimate(menu5))
            }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(AndroidSchedulers.mainThread())
            .subscribe {
                it.start()
            }

    }

    private fun getAnimate(view: View): AnimatorSet {
        val alpha = ObjectAnimator.ofFloat(view, "alpha", 0f, 1f)
        val move = ObjectAnimator.ofFloat(view, "translationY", -80f, 0f)
        val animSet = AnimatorSet()
        animSet.playTogether(alpha, move)
        animSet.duration = 400
        animSet.interpolator = AccelerateDecelerateInterpolator()
        animSet.addListener(object : Animator.AnimatorListener {

            override fun onAnimationRepeat(p0: Animator?) {
            }

            override fun onAnimationEnd(p0: Animator?) {
            }

            override fun onAnimationCancel(p0: Animator?) {
            }

            override fun onAnimationStart(p0: Animator?) {
                view.visible()
            }
        })
        return animSet
    }


}
