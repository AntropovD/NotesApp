package com.antropov.talk.demo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.antropov.talk.R
import com.antropov.talk.databinding.DemoFragmentBinding
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class DemoFragment : DaggerFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel by viewModels<DemoViewModel> { viewModelFactory }

    private lateinit var viewDataBinding: DemoFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.demo_fragment, container, false)
        viewDataBinding = DemoFragmentBinding.bind(root).apply {
            viewModel = this@DemoFragment.viewModel
        }
        viewDataBinding.lifecycleOwner = this.viewLifecycleOwner
        return viewDataBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        // TODO: Use the ViewModel
    }

}
