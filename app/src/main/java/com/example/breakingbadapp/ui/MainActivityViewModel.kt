package com.example.breakingbadapp.ui

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.breakingbadapp.util.AppearanceFilter

class MainActivityViewModel
@ViewModelInject
constructor(
) : ViewModel() {
    private val _appearanceFilterLiveData = MutableLiveData<ArrayList<AppearanceFilter>>()

    val appearanceFilterLiveData: LiveData<ArrayList<AppearanceFilter>>
        get() = _appearanceFilterLiveData

    private val _appearanceOneButtonLiveData = MutableLiveData<Boolean>()
    val appearanceOneButtonLiveData: LiveData<Boolean>
        get() = _appearanceOneButtonLiveData

    private val _appearanceTwoButtonLiveData = MutableLiveData<Boolean>()
    val appearanceTwoButtonLiveData: LiveData<Boolean>
        get() = _appearanceTwoButtonLiveData

    private val _appearanceThreeButtonLiveData = MutableLiveData<Boolean>()
    val appearanceThreeButtonLiveData: LiveData<Boolean>
        get() = _appearanceThreeButtonLiveData

    private val _appearanceFourButtonLiveData = MutableLiveData<Boolean>()
    val appearanceFourButtonLiveData: LiveData<Boolean>
        get() = _appearanceFourButtonLiveData

    private val _appearanceFiveButtonLiveData = MutableLiveData<Boolean>()
    val appearanceFiveButtonLiveData: LiveData<Boolean>
        get() = _appearanceFiveButtonLiveData

    fun addFilterLiveData(appearanceFilterList: ArrayList<AppearanceFilter>) {
        _appearanceFilterLiveData.value = appearanceFilterList
    }

    fun isAppearanceOneButtonChecked(checked: Boolean) {
        _appearanceOneButtonLiveData.value = checked
    }

    fun isAppearanceTwoButtonChecked(checked: Boolean) {
        _appearanceTwoButtonLiveData.value = checked
    }

    fun isAppearanceThreeButtonChecked(checked: Boolean) {
        _appearanceThreeButtonLiveData.value = checked
    }

    fun isAppearanceFourButtonChecked(checked: Boolean) {
        _appearanceFourButtonLiveData.value = checked
    }

    fun isAppearanceFiveButtonChecked(checked: Boolean) {
        _appearanceFiveButtonLiveData.value = checked
    }
}

