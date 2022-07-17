package com.arash.altafi.samplecleancode2.ui.profile

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.arash.altafi.samplecleancode2.R
import com.arash.altafi.samplecleancode2.databinding.FragmentProfileBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.arash.altafi.samplecleancode2.models.UserModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProfileFragment : Fragment(R.layout.fragment_profile) {
    private lateinit var binding: FragmentProfileBinding
    private val viewModel: ProfileViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentProfileBinding.bind(view)


        // observe the user data model
        viewModel.profileDataResponse
            .observe(viewLifecycleOwner) {
                if (it.first != null) {
                    val list = it.first
                    list?.let { it1 -> setData(it1) }
                } else {
                    Toast.makeText(context, "Error : ${it.second?.message}", Toast.LENGTH_LONG)
                        .show()
                }
            }


    }

    private fun setData(model: UserModel) {
        binding.name.text = model.name
        binding.bio.text = "Bio : ${model.bio}"
        binding.username.text = "@${model.login}"
        binding.publicGistsCountTv.text = model.public_gists.toString()
        binding.publicRepoCountTv.text = model.public_repos.toString()

        Glide.with(this)
            .load(model.avatar_url.toString())
            .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
            .into(binding.profilePic)
    }
}