package com.arash.altafi.samplecleancode2.ui.commitList

import com.haroldadmin.cnradapter.NetworkResponse
import com.arash.altafi.samplecleancode2.models.CommitModel
import com.arash.altafi.samplecleancode2.models.ErrorResponse
import com.arash.altafi.samplecleancode2.networking.ApiInterface

class CommitListRepository(private val api: ApiInterface) {


    suspend fun getCommitList(
        ownerName: String,
        repoName: String,
        per_page: Int
    ): Pair<List<CommitModel>?, ErrorResponse?> {

        return when (val response = api.getCommits(ownerName, repoName, per_page)) {
            is NetworkResponse.Success -> {
                // Handle successful response

                Pair(response.body, null)
            }

            is NetworkResponse.ServerError -> {
                // Handle server error
                Pair(null, ErrorResponse(400, "Server Error"))
            }
            is NetworkResponse.NetworkError -> {
                // Handle network error
                Pair(null, ErrorResponse(400, "Network Error"))
            }
            is NetworkResponse.UnknownError -> {
                // Handle other errors
                Pair(null, ErrorResponse(400, "Unknown Error"))
            }
        }

    }


}