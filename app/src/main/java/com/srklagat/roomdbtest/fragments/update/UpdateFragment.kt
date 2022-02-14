package com.srklagat.roomdbtest.fragments.update

import android.app.AlertDialog
import android.os.Bundle
import android.text.TextUtils
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.srklagat.roomdbtest.R
import com.srklagat.roomdbtest.models.User
import com.srklagat.roomdbtest.viewModel.UserViewModel
import kotlinx.android.synthetic.main.fragment_update.*
import kotlinx.android.synthetic.main.fragment_update.view.*


class UpdateFragment : Fragment() {
    private val args by navArgs<UpdateFragmentArgs>()
    private lateinit var mUserViewModel: UserViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val mView = inflater.inflate(R.layout.fragment_update, container, false)

        mUserViewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        mView.usernameEditTextUpdate.setText(args.currentUser.userName)
        mView.emailEditTextUpdate.setText(args.currentUser.userEmail)
        mView.ageEditTextUpdate.setText(args.currentUser.age.toString())

        mView.updateUserBtn.setOnClickListener {

            updateUserDetails()
        }
        setHasOptionsMenu(true)

        return mView
    }

    private fun updateUserDetails() {
        val userName = usernameEditTextUpdate.text.toString()
        val userEmail = emailEditTextUpdate.text.toString()
        val userAge = Integer.parseInt(ageEditTextUpdate.text.toString())

        if (inputCheck(userName, userEmail, userAge.toString())) {
            val updatedUser = User(args.currentUser.id, userName, userEmail, userAge)
            mUserViewModel.updateUser(updatedUser)

            Toast.makeText(requireContext(), "user updated successfully", Toast.LENGTH_SHORT).show()

            findNavController().navigate((R.id.action_updateFragment_to_listFragment))
        } else {
            Toast.makeText(
                requireContext(),
                "Update fail: please fill all the details",
                Toast.LENGTH_SHORT
            ).show()
        }

    }


    private fun inputCheck(userName: String, userEmail: String, age: String): Boolean {
        return !(TextUtils.isEmpty(userName) && TextUtils.isEmpty(userEmail) && TextUtils.isEmpty(
            age
        ))

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.delete_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menu_delete) {
            deleteUser()
        }

        return super.onOptionsItemSelected(item)
    }

    private fun deleteUser() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("Yes") { _, _ ->
            mUserViewModel.deleteUser(args.currentUser)
            Toast.makeText(requireContext(),"Successfully removed member -> ${args.currentUser.userName} From the registered members",
                Toast.LENGTH_SHORT).show()

            findNavController().navigate((R.id.action_updateFragment_to_listFragment))

        }
        builder.setNegativeButton("No") { _, _ -> }
        builder.setTitle("Delete${args.currentUser.userName}?")
        builder.setMessage("Are you sure you want to delete member ${args.currentUser.userName}")
        builder.create().show()
    }

}
