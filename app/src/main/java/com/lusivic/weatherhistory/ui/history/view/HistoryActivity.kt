package com.lusivic.weatherhistory.ui.history.view

import android.os.Bundle
import com.lusivic.weatherhistory.R
import com.lusivic.weatherhistory.data.db.weatherReport.WeatherReport
import com.lusivic.weatherhistory.ui.base.view.BaseActivity
import com.lusivic.weatherhistory.ui.history.interactor.IHistoryInteractor
import com.lusivic.weatherhistory.ui.history.presenter.IHistoryPresenter
import dagger.android.AndroidInjection
import javax.inject.Inject

class HistoryActivity : BaseActivity(), IHistoryActivity {

    @Inject
    internal lateinit var mPresenter: IHistoryPresenter<IHistoryActivity, IHistoryInteractor>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_history)
        AndroidInjection.inject(this)
    }

    override fun updateHistoryAdapter(history: List<WeatherReport>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun setListEmpty() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun goBack() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}