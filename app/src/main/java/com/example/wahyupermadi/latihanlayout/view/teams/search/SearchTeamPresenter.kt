package com.example.wahyupermadi.latihanlayout.view.teams.search

import com.example.wahyupermadi.latihanlayout.api.ApiInterface
import com.example.wahyupermadi.latihanlayout.model.TeamResponse
import com.example.wahyupermadi.latihanlayout.utils.SchedulersProvider
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.subscribers.ResourceSubscriber

class SearchTeamPresenter(private val mView : SearchTeamContract.View, private val apiInterface: ApiInterface, private val schedulers: SchedulersProvider):
    SearchTeamContract.Presenter {
    private val compositeDisposable = CompositeDisposable()
    override fun getTeamByName(name: String) {
        val disposable : Disposable
        disposable = apiInterface.getTeamByName(name)
            .observeOn(schedulers.ui())
            .subscribeOn(schedulers.io())
            .subscribeWith(object : ResourceSubscriber<TeamResponse>(){
                override fun onComplete() {
                    mView.hideProgress()
                }

                override fun onNext(t: TeamResponse?) {
                    mView.hideProgress()
                    t?.teams?.let { mView.showTeamResult(it) }
                }

                override fun onError(t: Throwable?) {
                    mView.hideProgress()
                }

            })
        compositeDisposable.addAll(disposable)
    }
}