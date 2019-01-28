package com.lusivic.weatherhistory.ui.history.view

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.lusivic.weatherhistory.R
import com.lusivic.weatherhistory.data.db.weatherReport.WeatherReport
import com.lusivic.weatherhistory.ui.base.view.BaseActivity
import com.lusivic.weatherhistory.ui.history.interactor.IHistoryInteractor
import com.lusivic.weatherhistory.ui.history.presenter.IHistoryPresenter
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_history.*
import javax.inject.Inject

class HistoryActivity : BaseActivity(), IHistoryActivity {

    @Inject
    internal lateinit var mPresenter: IHistoryPresenter<IHistoryActivity, IHistoryInteractor>
    @Inject
    internal lateinit var mHistoryAdapter: HistoryAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_history)
        AndroidInjection.inject(this)
        mPresenter.onAttach(this)
    }

    override fun setUp() {
        rv_historyView.itemAnimator = DefaultItemAnimator()
        rv_historyView.adapter = mHistoryAdapter
        rv_historyView.layoutManager = LinearLayoutManager(this)
        mPresenter.onViewPrepared()
    }

    override fun updateHistoryAdapter(reports: List<WeatherReport>) {
        if (!rv_historyView.isVisible) setListVisible()
        mHistoryAdapter.addReportsToList(reports)
        Toast.makeText(this, "updateHistoryAdapter called", Toast.LENGTH_LONG).show()
    }

    override fun setListEmpty() {
        setEmptyVisible()
    }

    override fun goBack() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private fun setListVisible() {
        rv_historyView.visibility = View.VISIBLE
        tv_history_empty.visibility = View.GONE
    }

    private fun setEmptyVisible() {
        rv_historyView.visibility = View.GONE
        tv_history_empty.visibility = View.VISIBLE
    }
}