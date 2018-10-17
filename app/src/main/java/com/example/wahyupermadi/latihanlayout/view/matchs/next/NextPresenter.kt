package com.example.wahyupermadi.latihanlayout.view.matchs.next

import android.content.ContentValues.TAG
import android.util.Log
import com.example.wahyupermadi.latihanlayout.api.ApiInterface
import com.example.wahyupermadi.latihanlayout.model.MatchResponse
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.disposables.Disposables
import io.reactivex.schedulers.Schedulers
import io.reactivex.subscribers.ResourceSubscriber

class NextPresenter(val mView : NextContract.View, val apiService : ApiInterface) : NextContract.Presenter{
    val compositeDisposable = CompositeDisposable()
    override fun getMatch() {
        mView.showDialog()
        val disposable : Disposable
        disposable = apiService.getNextMatch()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribeWith(object : ResourceSubscriber<MatchResponse>(){
                    override fun onComplete() {
                        mView.hideDialog()
                    }

                    override fun onNext(t: MatchResponse?) {
                        Log.d(TAG,"HELL YEAH"+t)
                        t?.events?.let { mView.showNextMatch(it) }
                    }

                    override fun onError(t: Throwable?) {
                        Log.d(TAG,"HELL YEAH ERROR"+t)
                        mView.hideDialog()
                    }

                })
        compositeDisposable.addAll(disposable)
    }
}