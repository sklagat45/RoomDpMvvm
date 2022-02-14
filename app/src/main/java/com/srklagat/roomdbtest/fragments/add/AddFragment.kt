package com.srklagat.roomdbtest.fragments.add

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.srklagat.roomdbtest.R
import com.srklagat.roomdbtest.models.User
import com.srklagat.roomdbtest.viewModel.UserViewModel
import kotlinx.android.synthetic.main.fragment_add.*
import kotlinx.android.synthetic.main.fragment_add.view.*

class AddFragment : Fragment() {

    private lateinit var mUserViewModel: UserViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_add, container, false)


        mUserViewModel = ViewModelProvider(this).get(UserViewModel::class.java)
        view.addUserBtn.setOnClickListener() {

            insertDataToDatabase()

        }

        return view
    }

    private fun insertDataToDatabase() {
        val userName = usernameEditText.text.toString()
        val userEmail = emailEditText.text.toString()
        val age = ageEditText.text.toString()

        if (validate(userName, userEmail, age)) {
            val user = User(0, userName, userEmail, Integer.parseInt(age.toString()))
            mUserViewModel.addUser(user)
            Toast.makeText(requireContext(), "Added Successfully", Toast.LENGTH_SHORT).show()

            findNavController().navigate(R.id.action_addFragment_to_listFragment)
        } else {
            Toast.makeText(requireContext(), "Fill out all Fields", Toast.LENGTH_SHORT).show()
        }

    }

    private fun validate(username: String, userEmail: String, age: String): Boolean {
        return !(TextUtils.isEmpty(username) && TextUtils.isEmpty(userEmail) && TextUtils.isEmpty(
            age
        ))

    }

}