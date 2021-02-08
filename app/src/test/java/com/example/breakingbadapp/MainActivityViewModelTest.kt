package com.example.breakingbadapp

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.example.breakingbadapp.ui.MainActivityViewModel
import com.example.breakingbadapp.util.AppearanceFilter
import com.nhaarman.mockitokotlin2.spy
import junit.framework.Assert.*
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class MainActivityViewModelTest {

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var viewModel: MainActivityViewModel

    @Mock
    private lateinit var appearanceOneButtonLiveData: LiveData<Boolean>

    @Mock
    private lateinit var appearanceTwoButtonLiveData: LiveData<Boolean>

    @Mock
    private lateinit var appearanceThreeButtonLiveData: LiveData<Boolean>

    @Mock
    private lateinit var appearanceFourButtonLiveData: LiveData<Boolean>

    @Mock
    private lateinit var appearanceFiveButtonLiveData: LiveData<Boolean>

    @Mock
    private lateinit var appearanceFilterLiveData: LiveData<ArrayList<AppearanceFilter>>

    @Mock
    private lateinit var appearanceObserver: Observer<in Boolean>

    @Mock
    private lateinit var appearanceFilterObserver: Observer<ArrayList<AppearanceFilter>>

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        viewModel = spy(MainActivityViewModel())
        appearanceOneButtonLiveData = viewModel.appearanceOneButtonLiveData
        appearanceTwoButtonLiveData = viewModel.appearanceTwoButtonLiveData
        appearanceThreeButtonLiveData = viewModel.appearanceThreeButtonLiveData
        appearanceFourButtonLiveData = viewModel.appearanceFourButtonLiveData
        appearanceFiveButtonLiveData = viewModel.appearanceFiveButtonLiveData
        appearanceFilterLiveData = viewModel.appearanceFilterLiveData

        viewModel.appearanceOneButtonLiveData.observeForever(appearanceObserver)
        viewModel.appearanceTwoButtonLiveData.observeForever(appearanceObserver)
        viewModel.appearanceThreeButtonLiveData.observeForever(appearanceObserver)
        viewModel.appearanceFourButtonLiveData.observeForever(appearanceObserver)
        viewModel.appearanceFiveButtonLiveData.observeForever(appearanceObserver)
        viewModel.appearanceFilterLiveData.observeForever(appearanceFilterObserver)
    }

    @After
    fun tearDown() {
        viewModel.appearanceFiveButtonLiveData.removeObserver(appearanceObserver)
        viewModel.appearanceTwoButtonLiveData.removeObserver(appearanceObserver)
        viewModel.appearanceThreeButtonLiveData.removeObserver(appearanceObserver)
        viewModel.appearanceFourButtonLiveData.removeObserver(appearanceObserver)
        viewModel.appearanceFiveButtonLiveData.removeObserver(appearanceObserver)
        viewModel.appearanceFilterLiveData.removeObserver(appearanceFilterObserver)
    }

    @Test
    fun `Verify appearanceFilter liveData values changes on event`() {
        viewModel.addFilterLiveData(
            arrayListOf(
                AppearanceFilter.APPEARANCE_1,
                AppearanceFilter.APPEARANCE_5
            )
        )
        appearanceFilterLiveData.value?.let {
            assertEquals(
                it,
                arrayListOf(AppearanceFilter.APPEARANCE_1, AppearanceFilter.APPEARANCE_5)
            )
        }

    }

    @Test
    fun `Verify appearanceOneButton liveData values changes on event`() {
        viewModel.isAppearanceOneButtonChecked(true)
        appearanceOneButtonLiveData.value?.let { assertTrue(it) }
        viewModel.isAppearanceOneButtonChecked(false)
        appearanceOneButtonLiveData.value?.let { assertFalse(it) }
    }

    @Test
    fun `Verify appearanceTwoButton liveData values changes on event`() {
        viewModel.isAppearanceTwoButtonChecked(true)
        appearanceTwoButtonLiveData.value?.let { assertTrue(it) }
        viewModel.isAppearanceTwoButtonChecked(false)
        appearanceTwoButtonLiveData.value?.let { assertFalse(it) }
    }

    @Test
    fun `Verify appearanceThreeButton liveData values changes on event`() {
        viewModel.isAppearanceThreeButtonChecked(true)
        appearanceThreeButtonLiveData.value?.let { assertTrue(it) }
        viewModel.isAppearanceThreeButtonChecked(false)
        appearanceThreeButtonLiveData.value?.let { assertFalse(it) }
    }

    @Test
    fun `Verify appearanceFourButton liveData values changes on event`() {
        viewModel.isAppearanceFourButtonChecked(true)
        appearanceFourButtonLiveData.value?.let { assertTrue(it) }
        viewModel.isAppearanceFourButtonChecked(false)
        appearanceFourButtonLiveData.value?.let { assertFalse(it) }
    }

    @Test
    fun `Verify appearanceFiveButton liveData values changes on event`() {
        viewModel.isAppearanceFiveButtonChecked(true)
        appearanceFiveButtonLiveData.value?.let { assertTrue(it) }
        viewModel.isAppearanceFiveButtonChecked(false)
        appearanceFiveButtonLiveData.value?.let { assertFalse(it) }
    }
}