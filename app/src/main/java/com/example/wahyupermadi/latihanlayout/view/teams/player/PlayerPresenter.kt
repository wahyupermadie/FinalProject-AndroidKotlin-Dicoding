package com.example.wahyupermadi.latihanlayout.view.teams.player

import android.content.ContentValues.TAG
import android.util.Log
import com.example.wahyupermadi.latihanlayout.api.ApiInterface
import com.example.wahyupermadi.latihanlayout.model.PlayerResponse
import com.example.wahyupermadi.latihanlayout.utils.SchedulersProvider
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.subscribers.ResourceSubscriber

class PlayerPresenter(private val mView : PlayerContract.View, private val scheduler : SchedulersProvider, private val apiInterface: ApiInterface):PlayerContract.Presenter{
    private val compositeDisposable = CompositeDisposable()
    override fun getPlayer(id: String) {
        mView.showProgress()
        val disposable : Disposable
        disposable = apiInterface.getPlayer(id)
            .observeOn(scheduler.ui())
            .subscribeOn(scheduler.io())
            .subscribeWith(object : ResourceSubscriber<PlayerResponse>(){
                override fun onComplete() {
                    mView.hideProgress()
                }

                override fun onNext(t: PlayerResponse?) {
                    mView.hideProgress()
                    Log.d(TAG,"ERROR GUYS"+t.toString())
                    t?.player?.let { mView.showPlayer(it)}
                }

                override fun onError(t: Throwable?) {
                    Log.d(TAG,"ERROR GUYS"+t.toString())
                    mView.hideProgress()
                }

            })
        compositeDisposable.addAll(disposable)
    }
}