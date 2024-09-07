package com.example.smarttask.ui

import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import com.example.smarttask.R
import com.example.smarttask.base.BaseFragment
import com.example.smarttask.databinding.FragmentLoginBinding
import com.example.smarttask.networking.DataResult
import kotlinx.android.synthetic.main.tool_bar.*
import kotlinx.android.synthetic.main.view_loading_circular.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class LoginFragment : BaseFragment<FragmentLoginBinding>() {

    @ExperimentalCoroutinesApi
    private val initialViewModel by sharedViewModel<InitialViewModel>()



    // for injecting adapter
//    val needsAdapter by inject<NeedsAdapter>()


    override fun getLayoutRes() = R.layout.fragment_login

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        binding.initialViewModel = initialViewModel
        binding.loginFragment = this
        setToolbar()

    }

    private fun setToolbar() {

            toolbar_title.text = resources.getString(R.string.login)

            toolBarGeneric.setNavigationIcon(R.drawable.ic_arrow_back_white_24dp)

            toolBarGeneric.navigationIcon?.setTint(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.white
                )
            )
            toolBarGeneric.setNavigationOnClickListener {
                requireActivity().onBackPressed()
            }

    }


    fun sendOTP() {
        observeLogin()

    }

    fun observeLogin() {
        initialViewModel.sendOTP().observe(viewLifecycleOwner, Observer {

            when (it) {
                is DataResult.Loading -> {
                    showLoading()

                }
                is DataResult.Success -> {
                    showData()
//                    initialViewModel.registerUserData.value!!.otp = it.data.data.otp.toString()
//                    // navigate to enter otp screen
//                    if (findNavController().currentDestination?.id == R.id.loginFragment) {
//                        findNavController().navigate(R.id.action_loginFragment_to_otpFragment)
//                    }

                }
                is DataResult.Failure -> {
                    showData()
                  //  Toasty.error(requireContext(), it.message!!, Toast.LENGTH_SHORT, true).show()
                }
            }
        })
    }




    fun showLoading() {
        tv_loading.setText(getString(R.string.validating_user))
        cl_loading.visibility = View.VISIBLE
    }

    fun showData() {
        cl_loading.visibility = View.GONE
    }
}
