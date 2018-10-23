package com.example.wahyupermadi.latihanlayout.view.matchs.past

import android.content.ContentValues
import android.util.Log
import com.example.wahyupermadi.latihanlayout.api.ApiInterface
import com.example.wahyupermadi.latihanlayout.model.MatchResponse
import com.example.wahyupermadi.latihanlayout.utils.SchedulersProvider
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subscribers.ResourceSubscriber

class PastPresenter(val mView : PastContract.View, val apiService : ApiInterface, val schedulers : SchedulersProvider) : PastContract.Presenter{
    private val compositeDisposable = CompositeDisposable()
    override fun getMatch() {
        mView.showProgress()
        val disposable : Disposable
        disposable = apiService.getPastMatch()
                .observeOn(schedulers.ui())
                .subscribeOn(schedulers.io())
                .subscribeWith(object : ResourceSubscriber<MatchResponse>(){
                    override fun onComplete() {
                        mView.hideProgress()
                    }

                    override fun onNext(t: MatchResponse?) {
                        t?.events?.let { mView.showPastMatch(it) }
                        mView.hideProgress()
                    }

                    override fun onError(t: Throwable?) {
                        Log.d(ContentValues.TAG,"HELL YEAH ERROR"+t)
                        mView.hideProgress()
                    }

                })
        compositeDisposable.addAll(disposable)
    }
}