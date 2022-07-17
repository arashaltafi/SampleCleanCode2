package com.arash.altafi.samplecleancode2.ui.commitList

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.arash.altafi.samplecleancode2.models.CommitModel
import com.arash.altafi.samplecleancode2.models.ErrorResponse
import com.arash.altafi.samplecleancode2.utils.Const
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

@HiltViewModel
class CommitViewModel @Inject constructor(
    repository: CommitListRepository
) : ViewModel() {

    private val repos = repository

    fun getCommitList(
        ownerName: String,
        repoName: String,
        per_page: Int = Const.PER_PAGE // defaulting to 10 as only 10 commit needed to be seen
    ): LiveData<Pair<List<CommitModel>?, ErrorResponse?>> {

        return liveData(Dispatchers.Main) {
            val response = repos.getCommitList(ownerName, repoName, per_page)
            emit(response)
        }


    }

}