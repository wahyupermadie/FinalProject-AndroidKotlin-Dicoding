package com.example.wahyupermadi.latihanlayout.view.matchs.next

import android.content.ContentValues.TAG
import android.util.Log
import com.example.wahyupermadi.latihanlayout.api.ApiInterface
import com.example.wahyupermadi.latihanlayout.model.MatchResponse
import com.example.wahyupermadi.latihanlayout.utils.SchedulersProvider
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subscribers.ResourceSubscriber

class NextPresenter(val mView : NextContract.View, val apiService : ApiInterface, val schedulers : SchedulersProvider) : NextContract.Presenter{
    private val compositeDisposable = CompositeDisposable()
    override fun getMatch() {
        mView.showProgress()
        val disposable : Disposable
        disposable = apiService.getNextMatch()
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
}