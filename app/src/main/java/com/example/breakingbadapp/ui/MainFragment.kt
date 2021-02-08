package com.example.breakingbadapp.ui

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.breakingbadapp.R
import com.example.breakingbadapp.models.CharacterData
import com.example.breakingbadapp.util.AppearanceFilter
import com.example.breakingbadapp.util.DataState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.main_fragment.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import java.util.*
import kotlin.collections.ArrayList

@ExperimentalCoroutinesApi
@AndroidEntryPoint
class MainFragment :
    Fragment(R.layout.main_fragment),
    SearchView.OnQueryTextListener,
    MenuItem.OnActionExpandListener,
    CharacterRecyclerViewAdapter.CharacterItemClickListener {

    private val charactersList = ArrayList<CharacterData>()
    private val filteredCharacterList = ArrayList<CharacterData>()
    private val appearanceFilterList = ArrayList<AppearanceFilter>()
    private val viewModel: MainFragmentViewModel by viewModels()
    private val mainActivityViewModel: MainActivityViewModel by activityViewModels()
    private lateinit var navController: NavController
    private lateinit var characterRecyclerViewAdapter: CharacterRecyclerViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(requireView())
        subscribeAppearanceFilterObserver()
        subscribeDataStateObservers()
        if (charactersList.isEmpty()) {
            viewModel.setStateEvent()
        }
    }

    private fun subscribeAppearanceFilterObserver() {
        mainActivityViewModel.appearanceFilterLiveData.observe(
            viewLifecycleOwner, { appearanceData ->
                for (appearance in appearanceData) {
                    appearanceFilterList.add(appearance)
                }
            })
    }

    private fun subscribeDataStateObservers() {
        viewModel.dataStateLiveData.observe(viewLifecycleOwner, Observer { dataState ->
            when (dataState) {
                is DataState.Success<List<CharacterData>> -> {
                    initRecyclerView(this)
                    filteredCharacterList.clear()
                    addDataSet(dataState.data as ArrayList<CharacterData>, appearanceFilterList)
                    if (charactersList.isEmpty()) {
                        for (character in setCharacterAppearanceFilter(
                            appearanceFilterList,
                            dataState.data
                        )) {
                            if (!charactersList.contains(character)) {
                                charactersList.add(character)
                            }
                        }
                    }
                    displayProgressBar(false)
                }
                is DataState.Error -> {
                    displayProgressBar(false)
                }
                is DataState.Loading -> {
                    displayProgressBar(true)
                }
            }
        })
    }

    private fun addDataSet(
        charactersDataList: ArrayList<CharacterData>,
        appearanceFilterList: ArrayList<AppearanceFilter>
    ) {
        characterRecyclerViewAdapter.submitList(
            setCharacterAppearanceFilter(appearanceFilterList, charactersDataList) as ArrayList<CharacterData>
        )
    }

    private fun displayProgressBar(isDisplayed: Boolean) {
        progress_bar.visibility = if (isDisplayed) View.VISIBLE else View.GONE
    }

    private fun initRecyclerView(supportItemClickListener: CharacterRecyclerViewAdapter.CharacterItemClickListener) {
        recycler_view.apply {
            layoutManager = LinearLayoutManager(requireContext())
            val topSpacingDecoration = TopSpacingDecoration(64)
            addItemDecoration(topSpacingDecoration)
            hasFixedSize()
            characterRecyclerViewAdapter = CharacterRecyclerViewAdapter(supportItemClickListener)
            adapter = characterRecyclerViewAdapter
        }
    }

    override fun onCharacterClick(position: Int) {
        val character: CharacterData = if (filteredCharacterList.size == 0) {
            charactersList[position]
        } else {
            filteredCharacterList[position]
        }
        val bundle = bundleOf("characterData" to character)
        navController.navigate(R.id.action_mainFragment_to_characterInfoFragment, bundle)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.appbar_menu, menu)
        val searchItem = menu.findItem(R.id.action_search)
        val searchView = searchItem.actionView as SearchView
        searchView.setOnQueryTextListener(this)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        return true
    }

    override fun onQueryTextChange(name: String?): Boolean {
        if (name == null || name.trim().isEmpty()) {
            filteredCharacterList.clear()
            characterRecyclerViewAdapter.setFilter(charactersList)
            return false
        }
        filteredCharacterList.clear()
        for (character in charactersList) {
            val characterName = character.name.toLowerCase(Locale.getDefault())
            if (characterName.contains(name)) {
                filteredCharacterList.add(character)
            }
        }
        characterRecyclerViewAdapter.setFilter(filteredCharacterList)
        return true
    }

    override fun onMenuItemActionExpand(item: MenuItem?): Boolean {
        return true
    }

    override fun onMenuItemActionCollapse(item: MenuItem?): Boolean {
        characterRecyclerViewAdapter.setFilter(charactersList)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_filter -> {
                navController.navigate(R.id.action_mainFragment_to_sortFragment)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun setCharacterAppearanceFilter(
        characterAppearanceList: ArrayList<AppearanceFilter>,
        characterDataList: ArrayList<CharacterData>
    ): List<CharacterData> {

        var characterTemporaryAppearanceList: ArrayList<CharacterData>
        val sortedList = ArrayList<CharacterData>()

        if (characterAppearanceList.isEmpty()) {
            return characterDataList
        }
        for (character in characterDataList) {
            if (character.appearance != null) {
                sortedList.add(character)
            }
        }
        if (characterAppearanceList.contains(AppearanceFilter.APPEARANCE_1)) {
            characterTemporaryAppearanceList = sortedList.filter { it.appearance!!.contains(1) }
                    as ArrayList<CharacterData>
            filteredCharacterList.addAll(characterTemporaryAppearanceList)
        }
        if (characterAppearanceList.contains(AppearanceFilter.APPEARANCE_2)) {
            characterTemporaryAppearanceList = sortedList.filter { it.appearance!!.contains(2) }
                    as ArrayList<CharacterData>
            filteredCharacterList.addAll(characterTemporaryAppearanceList)
        }
        if (characterAppearanceList.contains(AppearanceFilter.APPEARANCE_3)) {
            characterTemporaryAppearanceList = sortedList.filter { it.appearance!!.contains(3) }
                    as ArrayList<CharacterData>

            filteredCharacterList.addAll(characterTemporaryAppearanceList)
        }
        if (characterAppearanceList.contains(AppearanceFilter.APPEARANCE_4)) {
            characterTemporaryAppearanceList = sortedList.filter { it.appearance!!.contains(4) }
                    as ArrayList<CharacterData>
            filteredCharacterList.addAll(characterTemporaryAppearanceList)
        }
        if (characterAppearanceList.contains(AppearanceFilter.APPEARANCE_5)) {
            characterTemporaryAppearanceList = sortedList.filter { it.appearance!!.contains(5) }
                    as ArrayList<CharacterData>
            filteredCharacterList.addAll(characterTemporaryAppearanceList)
        }
        return filteredCharacterList.toSet().toList()
    }
}






