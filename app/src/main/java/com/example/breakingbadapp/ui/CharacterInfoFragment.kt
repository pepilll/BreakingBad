package com.example.breakingbadapp.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.breakingbadapp.R
import com.example.breakingbadapp.models.CharacterData
import kotlinx.android.synthetic.main.fragment_character_info.*

class CharacterInfoFragment : Fragment(R.layout.fragment_character_info) {

    private lateinit var characterData: CharacterData

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        characterData = requireArguments().getParcelable("characterData")!!
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Glide.with(requireContext())
            .asBitmap()
            .load(characterData.img)
            .into(characterInfoImageView)
        setCharacterInfo(characterData)
        characterNameTextView.visibility = View.VISIBLE
        characterNameTextView.text = characterData.name
    }

    @SuppressLint("SetTextI18n")
    private fun setCharacterInfo(characterData: CharacterData) {

        if (!characterData.birthday!!.contains("Unknown") && characterData.birthday!!.isNotEmpty()) {
            birthdayTextView.visibility = View.VISIBLE
            birthdayTextView.text = "birthday:" + " " + characterData.birthday
        }
        if (characterData.category!!.isNotEmpty()) {
            categoryTextView.visibility = View.VISIBLE
            categoryTextView.text = getString(R.string.category) + " " + characterData.category
        }
        if (characterData.nickname!!.isNotEmpty()) {
            nicknameTextView.visibility = View.VISIBLE
            nicknameTextView.text = getString(R.string.nickname) + " " + characterData.nickname
        }
        if (characterData.portrayed!!.isNotEmpty()) {
            portrayedTextView.visibility = View.VISIBLE
            portrayedTextView.text =
                getString(R.string.portrayed) + " " + characterData.portrayed
        }
        if (!characterData.status!!.contains("Unknown") && characterData.status!!.isNotEmpty()) {
            statusTextView.visibility = View.VISIBLE
            statusTextView.text = getString(R.string.status) + " " + characterData.status
        }
        if (characterData.occupation!!.isNotEmpty()) {
            occupationTextView.visibility = View.VISIBLE
            occupationTextView.text =
                getString(R.string.occupation) + " " + characterData.occupation?.joinToString(
                    ", "
                )
        }
        if (characterData.betterCallSaulAppearance!!.isNotEmpty()) {
            betterCallSoulTextView.visibility = View.VISIBLE
            betterCallSoulTextView.text =
                getString(R.string.betterCallSoul) + " " + characterData.betterCallSaulAppearance?.joinToString(
                    ", "
                )
        }
        if (characterData.appearance!!.isNotEmpty()) {
            appearanceTextView.visibility = View.VISIBLE
            appearanceTextView.text =
                getString(R.string.appearance) + " " + characterData.appearance?.joinToString(
                    ", "
                )
        }
    }

    override fun onResume() {
        super.onResume()
        (activity as AppCompatActivity?)!!.supportActionBar!!.hide()
    }

    override fun onStop() {
        super.onStop()
        (activity as AppCompatActivity?)!!.supportActionBar!!.show()
    }
}

