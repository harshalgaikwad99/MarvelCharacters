package com.globant.marvelcharacters.data.datasource.remote.mapper

import com.globant.marvelcharacters.data.datasource.remote.MarvelCharacterRemoteSource
import com.globant.marvelcharacters.data.datasource.remote.MarvelCharacterRemoteSourceImpl
import com.globant.marvelcharacters.data.datasource.remote.apiservice.MarvelCharacterApi
import com.globant.marvelcharacters.data.entity.CharacterListEntity
import com.globant.marvelcharacters.data.entity.DataEntity
import com.globant.marvelcharacters.data.entity.ResultEntity
import com.globant.marvelcharacters.data.entity.ThumbnailEntity
import com.globant.marvelcharacters.domain.model.Character
import com.globant.marvelcharacters.domain.model.CharacterDetails
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import retrofit2.Response

@RunWith(MockitoJUnitRunner::class)
@ExperimentalCoroutinesApi
class MarvelCharacterRemoteSourceImplTest {

    @Mock
    private lateinit var marvelCharacterApi: MarvelCharacterApi

    @Mock
    private lateinit var marvelCharacterMapper: MarvelCharacterMapper

    private lateinit var marvelCharacterRemoteSource: MarvelCharacterRemoteSource

    @Before
    fun setup() {
        marvelCharacterRemoteSource =
            MarvelCharacterRemoteSourceImpl(marvelCharacterApi, marvelCharacterMapper)
    }

    @Test
    fun testGetCharactersWithSuccess() {
        runBlockingTest {
            val mockCharList = listOf(
                ResultEntity("TestDescription", 1, "TestName", ThumbnailEntity("jpg", "TestPath"))
            )

            val mockData = DataEntity(results = mockCharList)
            val mockCharResponse = Response.success(CharacterListEntity(mockData))

            whenever(marvelCharacterApi.getCharacters()).thenReturn(mockCharResponse)
            whenever(marvelCharacterMapper.toCharacterListModel(any())).thenReturn(
                listOf(
                    Character(
                        id = 1,
                        name = "TestName",
                        thumbnailUrl = "TestPath.jpg"
                    )
                )
            )

            val result = marvelCharacterRemoteSource.getCharacters()

            assert(result.isSuccess)
            assert(result.getSuccessData.data.isNotEmpty())
            Assert.assertEquals("TestName", result.getSuccessData.data.first().name)
            Assert.assertEquals(1, result.getSuccessData.data.first().id)
            Assert.assertEquals("TestPath.jpg", result.getSuccessData.data.first().thumbnailUrl)
        }
    }

    @Test
    fun testGetCharactersWithError() {
        runBlockingTest {
            val mockCharResponse = Response.error<CharacterListEntity>(400, "".toResponseBody())

            whenever(marvelCharacterApi.getCharacters()).thenReturn(mockCharResponse)

            val result = marvelCharacterRemoteSource.getCharacters()

            assert(!result.isSuccess)
        }
    }

    @Test
    fun testGetCharacterDetailsWithSuccess() {
        runBlockingTest {
            val mockCharList = listOf(
                ResultEntity("TestDescription", 1, "TestName", ThumbnailEntity("jpg", "TestPath"))
            )

            val mockData = DataEntity(results = mockCharList)
            val mockCharResponse = Response.success(CharacterListEntity(mockData))

            whenever(marvelCharacterApi.getCharacterDetails(any())).thenReturn(mockCharResponse)
            whenever(marvelCharacterMapper.toCharacterDetailModel(any())).thenReturn(
                CharacterDetails(
                    id = 1,
                    name = "TestName",
                    thumbnailUrl = "TestPath.jpg",
                    description = "TestDescription"
                )
            )

            val result = marvelCharacterRemoteSource.getCharacterDetails("100")

            assert(result.isSuccess)
            Assert.assertEquals("TestName", result.getSuccessData.data.name)
            Assert.assertEquals(1, result.getSuccessData.data.id)
            Assert.assertEquals("TestPath.jpg", result.getSuccessData.data.thumbnailUrl)
            Assert.assertEquals("TestDescription", result.getSuccessData.data.description)
        }
    }

    @Test
    fun testGetCharacterDetails() {
        runBlockingTest {
            val mockCharList = listOf(
                ResultEntity("TestDescription", 1, "TestName", ThumbnailEntity("jpg", "TestPath"))
            )

            val mockData = DataEntity(results = mockCharList)
            val mockCharResponse = Response.success(CharacterListEntity(mockData))

            whenever(marvelCharacterApi.getCharacterDetails(any())).thenReturn(mockCharResponse)
            whenever(marvelCharacterMapper.toCharacterDetailModel(any())).thenReturn(
                CharacterDetails(
                    id = 1,
                    name = "TestName",
                    thumbnailUrl = "TestPath.jpg",
                    description = "TestDescription"
                )
            )

            val result = marvelCharacterRemoteSource.getCharacterDetails("100")

            assert(result.isSuccess)
            Assert.assertEquals("TestName", result.getSuccessData.data.name)
            Assert.assertEquals(1, result.getSuccessData.data.id)
            Assert.assertEquals("TestPath.jpg", result.getSuccessData.data.thumbnailUrl)
            Assert.assertEquals("TestDescription", result.getSuccessData.data.description)
        }
    }
}