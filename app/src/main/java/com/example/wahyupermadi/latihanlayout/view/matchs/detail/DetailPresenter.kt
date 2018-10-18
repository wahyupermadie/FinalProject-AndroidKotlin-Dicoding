package com.example.wahyupermadi.latihanlayout.view.matchs.detail

import com.example.wahyupermadi.latihanlayout.api.ApiInterface
import com.example.wahyupermadi.latihanlayout.model.TeamResponse
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import io.reactivex.subscribers.ResourceSubscriber

class DetailPresenter (val mView : DetailContract.View, val apiService : ApiInterface) :
    DetailContract.Presenter {
    override fun getHomeBadge(id: String) {
        apiService.getTeamDetail(id)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe(object : ResourceSubscriber<TeamResponse>(){
                override fun onComplete() {

                }

                override fun onNext(t: TeamResponse?) {
                    t?.teams?.let {
                        mView.showHomeBadge(it.get(0).strTeamBadge.toString())
                    }

                }

                override fun onError(t: Throwable?) {
                    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                }

            })
    }

    override fun getAwayBadge(id: String) {
        apiService.getTeamDetail(id)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe(object : ResourceSubscriber<TeamResponse>(){
                override fun onComplete() {

                }

                override fun onNext(t: TeamResponse?) {
                    t?.teams?.let {
                        mView.showAwayBadge(it.get(0).strTeamBadge.toString())
                    }

                }

                override fun onError(t: Throwable?) {
                    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                }

            })
    }

}