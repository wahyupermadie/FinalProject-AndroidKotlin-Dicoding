package com.example.wahyupermadi.latihanlayout.view.teams

import com.example.wahyupermadi.latihanlayout.api.ApiInterface
import com.example.wahyupermadi.latihanlayout.model.LigaResponse
import com.example.wahyupermadi.latihanlayout.model.TeamResponse
import com.example.wahyupermadi.latihanlayout.utils.SchedulersProvider
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.disposables.Disposables
import io.reactivex.subscribers.ResourceSubscriber

class TeamPresenter(private val mView : TeamContract.View, private val apiInterface: ApiInterface, private val scheduler : SchedulersProvider):TeamContract.Presenter{
    private val compositeDisposable = CompositeDisposable()
    override fun getLiga() {
        val disposables : Disposable
        disposables = apiInterface.getLiga()
            .observeOn(scheduler.ui())
            .subscribeOn(scheduler.io())
            .subscribeWith(object : ResourceSubscriber<LigaResponse>(){
                override fun onComplete() {

                }

                override fun onNext(t: LigaResponse?) {
                    t?.leagues?.let { mView.showLigas(it) }
                }

                override fun onError(t: Throwable?) {

                }
            })
        compositeDisposable.addAll(disposables)
    }

    override fun getTeam(id: String) {
        val disposable : Disposable
        mView.showProgress()
        disposable = apiInterface.getTeam(id)
            .observeOn(scheduler.ui())
            .subscribeOn(scheduler.io())
            .subscribeWith(object : ResourceSubscriber<TeamResponse>(){
                override fun onComplete() {
                    mView.hidePrgress()
                }

                override fun onNext(t: TeamResponse?) {
                    mView.hidePrgress()
                    t?.teams?.let { mView.showTeam(it) }
                }

                override fun onError(t: Throwable?) {
                    mView.hidePrgress()
                }

            })
        compositeDisposable.addAll(disposable)
    }
}