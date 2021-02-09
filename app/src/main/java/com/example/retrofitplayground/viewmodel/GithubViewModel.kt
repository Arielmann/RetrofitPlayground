package com.example.retrofitplayground.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.retrofitplayground.model.Repo
import com.example.retrofitplayground.repository.GithubRepository
import com.example.retrofitplayground.view.MainFragment
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GithubViewModel @Inject constructor(private val repository: GithubRepository) : ViewModel() {

    companion object {
        private val TAG: String? = MainFragment::class.simpleName
    }

    var githubRepos: MutableLiveData<List<Repo>> = MutableLiveData()

    fun requestRepos() {


        viewModelScope.launch(Dispatchers.IO) {
            repository.downloadFile()
            val result = repository.getRepos()
            githubRepos.postValue(result)
            result.forEach {
                Log.d(TAG, it.name)
            }
        }
    }
}