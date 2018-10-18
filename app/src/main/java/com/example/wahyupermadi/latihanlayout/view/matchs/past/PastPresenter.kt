package com.example.wahyupermadi.latihanlayout.view.matchs.past

import android.content.ContentValues
import android.util.Log
import com.example.wahyupermadi.latihanlayout.api.ApiInterface
import com.example.wahyupermadi.latihanlayout.model.MatchResponse
import com.example.wahyupermadi.latihanlayout.view.matchs.next.NextContract
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subscribers.ResourceSubscriber

class PastPresenter(val mView : PastContract.View, val apiService : ApiInterface) : PastContract.Presenter{
    val compositeDisposable = CompositeDisposable()
    override fun getMatch() {
        val disposable : Disposable
        disposable = apiService.getPastMatch()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribeWith(object : ResourceSubscriber<MatchResponse>(){
                    override fun onComplete() {
                    }

                    override fun onNext(t: MatchResponse?) {
                        Log.d(ContentValues.TAG,"HELL YEAH"+t)
                        t?.events?.let { mView.showPastMatch(it) }
                    }

                    override fun onError(t: Throwable?) {
                        Log.d(ContentValues.TAG,"HELL YEAH ERROR"+t)
                    }

                })
        compositeDisposable.addAll(disposable)
    }
}