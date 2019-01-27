package com.lusivic.weatherhistory.ui.base.presenter

import com.lusivic.weatherhistory.ui.base.interactor.MVPInteractor
import com.lusivic.weatherhistory.ui.base.view.MVPView
import com.lusivic.weatherhistory.utils.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable

abstract class BasePresenter<V : MVPView, I : MVPInteractor> internal constructor(protected var interactor: I?, protected val schedulerProvider: SchedulerProvider, protected val compositeDisposable: CompositeDisposable) :
    MVPPresenter<V, I> {

    private var view: V? = null
    private val isViewAttached: Boolean get() = view != null

    override fun onAttach(view: V?) {
        this.view = view
    }

    override fun onDetach() {
        view = null
        interactor = null
    }

    override fun getView(): V? = view
}