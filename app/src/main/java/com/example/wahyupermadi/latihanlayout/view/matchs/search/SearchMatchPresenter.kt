package com.example.wahyupermadi.latihanlayout.view.matchs.search

import android.util.Log
import com.example.wahyupermadi.latihanlayout.api.ApiInterface
import com.example.wahyupermadi.latihanlayout.model.MatchResponse
import com.example.wahyupermadi.latihanlayout.model.SearchMatchResponse
import com.example.wahyupermadi.latihanlayout.utils.SchedulersProvider
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subscribers.ResourceSubscriber

class SearchMatchPresenter(private val mView : SearchMatchContract.View, private val apiInterface: ApiInterface, private val schedulers: SchedulersProvider):SearchMatchContract.Presenter{
    private val compositeDisposable = CompositeDisposable()
    override fun getMatchByName(name: String) {
        val disposable : Disposable
        disposable = apiInterface.getEventByName(name)
            .observeOn(schedulers.ui())
            .subscribeOn(schedulers.io())
            .subscribeWith(object : ResourceSubscriber<SearchMatchResponse>(){
                override fun onComplete() {
                    mView.hideProgress()
                }

                override fun onNext(t: SearchMatchResponse?) {
                    t?.event?.let { mView.showMatchResult(it) }
                    mView.hideProgress()
                }

                override fun onError(t: Throwable?) {
                    mView.hideProgress()
                }

            })
        compositeDisposable.addAll(disposable)
    }
}