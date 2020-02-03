package com.piotrk.sampleproject.features.news

import com.nhaarman.mockitokotlin2.*
import com.piotrk.sampleproject.data.NewsRepository
import com.piotrk.sampleproject.data.model.News
import com.piotrk.sampleproject.util.StringProvider
import io.reactivex.Single
import org.junit.Before
import org.junit.Test

class GetNewsUseCaseTest {

    private lateinit var getNewsUseCase: GetNewsUseCase
    private lateinit var newsRepository: NewsRepository
    private lateinit var stringProvider: StringProvider

    @Before
    fun setup() {
        newsRepository = mock()
        stringProvider = mock {
            on { getString(any()) }.doReturn("")
        }
        getNewsUseCase = GetNewsUseCaseImpl(newsRepository, stringProvider)
    }

    @Test
    fun useCaseSingleCallsRepository() {
        stubRepositoryGetNews(Single.just(listOf()))
        getNewsUseCase.getNews()
        verify(newsRepository).getNews()
    }

    @Test
    fun useCaseSingleCompletes() {
        stubRepositoryGetNews(Single.just(listOf()))
        val testObserver = getNewsUseCase.getNews().test()
        testObserver.assertComplete()
    }

    @Test
    fun useCaseSingleConvertsData() {
        val repoList = NewsFactory.makeNewsList(2)
        stubRepositoryGetNews(Single.just(repoList))
        val testObserver = getNewsUseCase.getNews().test()
        testObserver.assertValue { list -> list.map { it.title } == repoList.map { it.title }}
    }

    private fun stubRepositoryGetNews(single: Single<List<News>>) {
        whenever(newsRepository.getNews())
            .thenReturn(single)
    }
}

class NewsFactory {
    companion object Factory {

        fun makeNewsList(count: Int): List<News> {
            return (0..count).map {
                makeRepo()
            }
        }

        fun makeRepo(): News {
            return News(
                randomUuid(),
                randomUuid(),
                "2020-02-06T11:30:00Z",
                randomUuid(),
                randomUuid()
            )
        }
    }
}

fun randomUuid(): String {
    return java.util.UUID.randomUUID().toString()
}
