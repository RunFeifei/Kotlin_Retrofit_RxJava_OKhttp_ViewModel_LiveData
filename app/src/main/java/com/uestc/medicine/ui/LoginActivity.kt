package com.uestc.medicine.ui

import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.Observer
import com.uestc.medicine.BuildConfig
import com.uestc.medicine.R
import com.uestc.medicine.base.BaseActivity
import com.uestc.medicine.net.User
import com.uestc.medicine.util.Common
import com.uestc.medicine.util.click
import com.uestc.medicine.util.get
import com.uestc.medicine.viewmodel.LoginViewModel
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : BaseActivity<LoginViewModel>() {


    override fun layoutId(): Int {
        return R.layout.activity_login
    }

    override fun initViewModel(): LoginViewModel {
        return get(LoginViewModel::class.java)
    }

    override fun initPage(savedInstanceState: Bundle?) {
        title = "登录"
//        if (BuildConfig.DEBUG) {
//            edtName.setText("abc123")
//            edtPass.setText("1234567")
//        }
        textLogin.click {
            viewModel.loadUser("abc123", "1234567")
        }
        viewModel.user.observe(this, Observer {
            it?.let {
                onLogin(it)
            }
        })
    }

    private fun onLogin(user: User) {
        user.dcotor?.let {
            if (it.isNotEmpty()) {
                Common.UID = it[0].doctor_id
                startActivity(Intent(this, MenuActivity::class.java))
            } else {
                showToast("登录接口出错")
                Common.UID = "1"
                startActivity(Intent(this, MenuActivity::class.java))
            }
        }
    }


}
