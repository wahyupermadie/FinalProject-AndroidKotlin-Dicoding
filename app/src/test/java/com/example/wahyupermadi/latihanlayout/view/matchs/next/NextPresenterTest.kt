package com.example.wahyupermadi.latihanlayout.view.matchs.next

import com.example.wahyupermadi.latihanlayout.api.ApiInterface
import com.example.wahyupermadi.latihanlayout.model.MatchItem
import com.example.wahyupermadi.latihanlayout.model.MatchResponse
import com.example.wahyupermadi.latihanlayout.utils.SchedulersProvider
import com.example.wahyupermadi.latihanlayout.utils.TestSchedulersProvider
import io.reactivex.Flowable
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations

class NextPresenterTest {
    private lateinit var presenter : NextPresenter
    private var matchs = mutableListOf<MatchItem>()
    private lateinit var match : MatchResponse
    private lateinit var matchResponse: Flowable<MatchResponse>

    @Mock
    private lateinit var view : NextContract.View

    @Mock
    private lateinit var apiInterface: ApiInterface

    @Mock
    private lateinit var scheduler: SchedulersProvider

    @Before
    fun setup(){
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun getMatch() {
        match = MatchResponse(matchs)
        matchResponse = Flowable.just(match)
        scheduler = TestSchedulersProvider()
        presenter = NextPresenter(view,apiInterface, scheduler)
        `when`(apiInterface.getNextMatch("4328")).thenReturn(matchResponse)

        presenter.getMatch("4328")
        verify(view).showProgress()
        verify(view).showNextMatch(matchs)
    }
}