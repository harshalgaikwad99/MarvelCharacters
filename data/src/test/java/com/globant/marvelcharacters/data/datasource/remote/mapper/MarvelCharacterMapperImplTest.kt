package com.globant.marvelcharacters.data.datasource.remote.mapper

import com.globant.marvelcharacters.data.entity.CharacterListEntity
import com.globant.marvelcharacters.data.entity.DataEntity
import com.globant.marvelcharacters.data.entity.ResultEntity
import com.globant.marvelcharacters.data.entity.ThumbnailEntity
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class MarvelCharacterMapperImplTest {

    private lateinit var marvelCharacterMapper: MarvelCharacterMapper

    @Before
    fun setup() {
        marvelCharacterMapper = MarvelCharacterMapperImpl()
    }

    @Test
    fun testToCharacterListModel() {
        val mockCharList = listOf(
            ResultEntity(
                description = "TestDescription",
                id = 1,
                name = "TestName",
                thumbnail = ThumbnailEntity(extension = "jpg", path = "TestPath")
            )
        )

        val mockData = DataEntity(results = mockCharList)
        val mockCharResponse = CharacterListEntity(mockData)

        val result = marvelCharacterMapper.toCharacterListModel(mockCharResponse)
        assert(result.isNotEmpty())
        Assert.assertEquals("TestName", result.first().name)
        Assert.assertEquals(1, result.first().id)
        Assert.assertEquals("TestPath.jpg", result.first().thumbnailUrl)
    }

    @Test
    fun testToCharacterListModelEmptyList() {
        val mockData = DataEntity(results = null)
        val mockCharResponse = CharacterListEntity(mockData)

        val result = marvelCharacterMapper.toCharacterListModel(mockCharResponse)
        assert(result.isEmpty())
    }

    @Test
    fun testToCharacterDetailModel() {
        val mockCharList = listOf(
            ResultEntity(
                description = "TestDescription",
                id = 1,
                name = "TestName",
                thumbnail = ThumbnailEntity(extension = "jpg", path = "TestPath")
            )
        )

        val mockData = DataEntity(results = mockCharList)
        val mockCharResponse = CharacterListEntity(mockData)

        val result = marvelCharacterMapper.toCharacterDetailModel(mockCharResponse)
        Assert.assertEquals("TestName", result.name)
        Assert.assertEquals("TestDescription", result.description)
        Assert.assertEquals(1, result.id)
        Assert.assertEquals("TestPath.jpg", result.thumbnailUrl)
    }
}