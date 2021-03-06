package com.example.wahyupermadi.latihanlayout.view.matchs.next

import android.content.ContentValues.TAG
import android.util.Log
import com.example.wahyupermadi.latihanlayout.api.ApiInterface
import com.example.wahyupermadi.latihanlayout.model.LigaResponse
import com.example.wahyupermadi.latihanlayout.model.MatchResponse
import com.example.wahyupermadi.latihanlayout.utils.SchedulersProvider
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.subscribers.ResourceSubscriber

class NextPresenter(val mView : NextContract.View, val apiService : ApiInterface, val schedulers : SchedulersProvider) : NextContract.Presenter{
    private val compositeDisposable = CompositeDisposable()

    override fun getMatch(id :String) {
        mView.showProgress()
        val disposable : Disposable
        disposable = apiService.getNextMatch(id)
                .observeOn(schedulers.ui())
                .subscribeOn(schedulers.io())
                .subscribeWith(object : ResourceSubscriber<MatchResponse>(){
                    override fun onComplete() {
                        mView.hideProgress()
                    }

                    override fun onNext(t: MatchResponse?) {
                        t?.events?.let { mView.showNextMatch(it) }
                        mView.hideProgress()

                    }

                    override fun onError(t: Throwable?) {
                        Log.d(TAG,"HELL YEAH ERROR"+t)
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