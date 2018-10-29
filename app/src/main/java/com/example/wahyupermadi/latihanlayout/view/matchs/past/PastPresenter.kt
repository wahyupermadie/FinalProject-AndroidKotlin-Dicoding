package com.example.wahyupermadi.latihanlayout.view.matchs.past

import android.content.ContentValues
import android.util.Log
import com.example.wahyupermadi.latihanlayout.api.ApiInterface
import com.example.wahyupermadi.latihanlayout.model.LigaResponse
import com.example.wahyupermadi.latihanlayout.model.MatchResponse
import com.example.wahyupermadi.latihanlayout.utils.SchedulersProvider
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.subscribers.ResourceSubscriber

class PastPresenter(val mView : PastContract.View, val apiService : ApiInterface, val schedulers : SchedulersProvider) : PastContract.Presenter{
    private val compositeDisposable = CompositeDisposable()

    override fun getMatch(id : String) {
        mView.showProgress()
        val disposable : Disposable
        disposable = apiService.getPastMatch(id)
                .observeOn(schedulers.ui())
                .subscribeOn(schedulers.io())
                .subscribeWith(object : ResourceSubscriber<MatchResponse>(){
                    override fun onComplete() {
                        mView.hideProgress()
                    }

                    override fun onNext(t: MatchResponse?) {
                        mView.hideProgress()
                        t?.events?.let { mView.showPastMatch(it) }

                    }

                    override fun onError(t: Throwable?) {
                        Log.d(ContentValues.TAG,"HELL YEAH ERROR"+t)
                        mView.hideProgress()
                    }

                })
        compositeDisposable.addAll(disposable)
    }
    override fun getLiga() {
        val disposables : Disposable
        disposables = apiService.getLiga()
            .observeOn(schedulers.ui())
            .subscribeOn(schedulers.io())
            .subscribeWith(object : ResourceSubscriber<LigaResponse>(){
                override fun onComplete() {

                }

                override fun onNext(t: LigaResponse?) {
                    t?.leagues?.let { mView.showLiga(it) }
                }

                override fun onError(t: Throwable?) {

                }

            })
        compositeDisposable.addAll(disposables)
    }
}