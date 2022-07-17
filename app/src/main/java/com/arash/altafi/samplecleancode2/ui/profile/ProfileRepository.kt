package com.arash.altafi.samplecleancode2.ui.profile

import com.haroldadmin.cnradapter.NetworkResponse
import com.arash.altafi.samplecleancode2.models.ErrorResponse
import com.arash.altafi.samplecleancode2.models.UserModel
import com.arash.altafi.samplecleancode2.networking.ApiInterface
import com.arash.altafi.samplecleancode2.utils.Const

class ProfileRepository(private val api: ApiInterface) {

    suspend fun getProfile(user: String = Const.PROFILE_USERNAME): Pair<UserModel?, ErrorResponse?> {

        return when (val response = api.getUser(user)) {
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