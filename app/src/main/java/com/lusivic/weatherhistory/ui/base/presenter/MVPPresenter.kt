package com.lusivic.weatherhistory.ui.base.presenter

import com.lusivic.weatherhistory.ui.base.interactor.MVPInteractor
import com.lusivic.weatherhistory.ui.base.view.MVPView

interface MVPPresenter<V : MVPView, I : MVPInteractor> {
    fun onAttach(view: V?)
    fun onDetach()
    fun getView(): V?

}