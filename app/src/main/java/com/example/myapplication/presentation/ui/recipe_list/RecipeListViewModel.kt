package com.example.myapplication.presentation.ui.recipe_list

import androidx.lifecycle.ViewModel
import dagger.hilt.android.scopes.ViewModelScoped


class RecipeListViewModel

@ViewModelScoped
constructor(
    private val randomString: String

):ViewModel(){
    init {
        println("ViewModel ${randomString}")
    }
}