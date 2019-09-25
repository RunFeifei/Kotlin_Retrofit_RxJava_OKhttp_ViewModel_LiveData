package com.uestc.medicine

import android.os.Bundle
import androidx.lifecycle.Observer
import com.uestc.medicine.base.BaseActivity
import com.uestc.medicine.util.click
import com.uestc.medicine.viewmodel.LoginViewModel
import com.uestc.medicine.viewmodel.get
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : BaseActivity<LoginViewModel>() {


    override fun layoutId(): Int {
        return R.layout.activity_login
    }

    override fun initViewModel(): LoginViewModel {
        return get(LoginViewModel::class.java)
    }

    override fun initPage(savedInstanceState: Bundle?) {
        if (BuildConfig.DEBUG) {
            edtName.setText("abc123")
            edtPass.setText("1234567")
        }
        textLogin.click {
            viewModel.loadUser("abc123","1234567")
        }
        viewModel.user.observe(this, Observer {
            it.dcotor
        })

    }


}
