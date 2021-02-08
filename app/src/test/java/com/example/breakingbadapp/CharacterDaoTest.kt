package com.example.breakingbadapp

import android.os.Build
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.breakingbadapp.models.CharacterData
import com.example.breakingbadapp.room.CacheMapper
import com.example.breakingbadapp.room.CharacterDB
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import java.io.IOException

@RunWith(RobolectricTestRunner::class)
@ExperimentalCoroutinesApi
open class CharacterDaoTest  {
    private lateinit var appDatabase: CharacterDB

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()
    private val cacheMapper = CacheMapper()

    private val character = CharacterData(
        charId = 1,
        appearance = listOf(1, 2, 3),
        betterCallSaulAppearance = listOf(1, 2),
        birthday = "11-10-2000",
        category = "Breaking Bad",
        img = "https://images.amcnetworks.com/amc.com/wp-content/uploads/2015/04/cast_bb_700x1000_walter-white-lg.jpg",
        name = "Walter",
        nickname = "Walter",
        occupation = listOf(
            "High School Chemistry Teacher",
            "Meth King Pin"
        ),
        portrayed = "Bryan Cranston",
        status = "Presumed dead"
    )

    @Before
    fun initDb() {
        appDatabase = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            CharacterDB::class.java
        )
            .allowMainThreadQueries()
            .build()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        appDatabase.close()
    }

    @Test
    fun insertQuoteTest() = runBlockingTest {
        appDatabase.characterDao().insert(cacheMapper.mapToEntity(character))
        val characterSize = appDatabase.characterDao().get().size
        assertEquals(characterSize, 1)
    }

    @Test
    fun getQuoteAsLiveDataTest()= runBlockingTest {
        appDatabase.characterDao().insert(cacheMapper.mapToEntity(character))
        val characterDataLiveDataValue = appDatabase.characterDao().get()
        assertEquals(characterDataLiveDataValue.size, 1)
    }
}