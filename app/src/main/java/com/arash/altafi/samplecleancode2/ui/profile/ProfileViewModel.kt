package com.arash.altafi.samplecleancode2.ui.profile

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.arash.altafi.samplecleancode2.models.ErrorResponse
import com.arash.altafi.samplecleancode2.models.UserModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    repository: ProfileRepository
) : ViewModel() {

    private val repos = repository
    val profileDataResponse: MutableLiveData<Pair<UserModel?, ErrorResponse?>> = MutableLiveData()

    init {
        getProfile()
    }

    private fun getProfile() {

        CoroutineScope(Dispatchers.IO).launch {
            val reponse = repos.getProfile()
            withContext(Dispatchers.Main) {
                profileDataResponse.value = reponse
            }
        }
    }
}