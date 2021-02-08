package com.example.breakingbadapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.breakingbadapp.R
import com.example.breakingbadapp.util.AppearanceFilter
import com.google.android.material.checkbox.MaterialCheckBox
import kotlinx.android.synthetic.main.fragment_sort.*

class SortFragment() : Fragment() {
    private lateinit var appearanceOne: MaterialCheckBox
    private lateinit var appearanceTwo: MaterialCheckBox
    private lateinit var appearanceThree: MaterialCheckBox
    private lateinit var appearanceFour: MaterialCheckBox
    private lateinit var appearanceFive: MaterialCheckBox
    private lateinit var navController: NavController
    private val appearanceList = ArrayList<AppearanceFilter>()
    private val mainActivityViewModel: MainActivityViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_sort, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        appearanceOne = view.findViewById(R.id.appearance_one)
        appearanceTwo = view.findViewById(R.id.appearance_two)
        appearanceThree = view.findViewById(R.id.appearance_three)
        appearanceFour = view.findViewById(R.id.appearance_four)
        appearanceFive = view.findViewById(R.id.appearance_five)
        navController = Navigation.findNavController(requireView())
        isCheckboxClicked()
        next_btn.setOnClickListener {
            setAppearanceFilter()
            mainActivityViewModel.addFilterLiveData(appearanceList)
            navController.navigate(R.id.action_sortFragment_to_mainFragment)
        }
    }

    private fun isCheckboxClicked() {
        mainActivityViewModel.appearanceOneButtonLiveData.observe(viewLifecycleOwner, { checked ->
            appearanceOne.isChecked = checked
        })
        mainActivityViewModel.appearanceTwoButtonLiveData.observe(viewLifecycleOwner, { isChecked ->
            appearanceTwo.isChecked = isChecked
        })
        mainActivityViewModel.appearanceThreeButtonLiveData.observe(viewLifecycleOwner, { isChecked ->
            appearanceThree.isChecked = isChecked
        })
        mainActivityViewModel.appearanceFourButtonLiveData.observe(viewLifecycleOwner, { isChecked ->
            appearanceFour.isChecked = isChecked
        })
        mainActivityViewModel.appearanceFiveButtonLiveData.observe(viewLifecycleOwner, { isChecked ->
            appearanceFive.isChecked = isChecked
        })
    }

    private fun setAppearanceFilter(): ArrayList<AppearanceFilter> {
        if (appearanceOne.isChecked) {
            mainActivityViewModel.isAppearanceOneButtonChecked(true)
            appearanceList.add(AppearanceFilter.APPEARANCE_1)
        } else {
            mainActivityViewModel.isAppearanceOneButtonChecked(false)
        }
        if (appearanceTwo.isChecked) {
            mainActivityViewModel.isAppearanceTwoButtonChecked(true)
            appearanceList.add(AppearanceFilter.APPEARANCE_2)
        } else {
            mainActivityViewModel.isAppearanceTwoButtonChecked(false)
        }
        if (appearanceThree.isChecked) {
            mainActivityViewModel.isAppearanceThreeButtonChecked(true)
            appearanceList.add(AppearanceFilter.APPEARANCE_3)
        } else {
            mainActivityViewModel.isAppearanceThreeButtonChecked(false)
        }
        if (appearanceFour.isChecked) {
            mainActivityViewModel.isAppearanceFourButtonChecked(true)

            appearanceList.add(AppearanceFilter.APPEARANCE_4)
        } else {
            mainActivityViewModel.isAppearanceFourButtonChecked(false)
        }
        if (appearanceFive.isChecked) {
            mainActivityViewModel.isAppearanceFiveButtonChecked(true)
            appearanceList.add(AppearanceFilter.APPEARANCE_5)
        } else {
            mainActivityViewModel.isAppearanceFiveButtonChecked(false)
        }
        return appearanceList
    }
}