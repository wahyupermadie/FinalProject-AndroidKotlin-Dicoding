package com.example.wahyupermadi.latihanlayout.view.matchs.past

import com.example.wahyupermadi.latihanlayout.api.ApiInterface
import com.example.wahyupermadi.latihanlayout.model.MatchItem
import com.example.wahyupermadi.latihanlayout.model.MatchResponse
import com.example.wahyupermadi.latihanlayout.utils.SchedulersProvider
import com.example.wahyupermadi.latihanlayout.utils.TestSchedulersProvider
import io.reactivex.Flowable
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

class PastPresenterTest {
    private var matchs = mutableListOf<MatchItem>()
    private lateinit var presenter : PastPresenter
    private lateinit var match : MatchResponse
    lateinit var matchsResponse: Flowable<MatchResponse>

    @Mock
    private lateinit var view : PastContract.View

    @Mock
    private lateinit var scheduler: SchedulersProvider

    @Mock
    private lateinit var apiInterface: ApiInterface

    @Before
    fun setup(){
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun getMatch() {
        match = MatchResponse(matchs)
        matchsResponse = Flowable.just(match)
        scheduler = TestSchedulersProvider()
        presenter = PastPresenter(view,apiInterface,scheduler)
        `when`(apiInterface.getPastMatch("4328")).thenReturn(matchsResponse)

        presenter.getMatch("4328")
        Mockito.verify(view).showProgress()
        Mockito.verify(view).showPastMatch(matchs)
    }
}