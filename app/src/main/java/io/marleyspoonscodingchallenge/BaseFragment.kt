package io.marleyspoonscodingchallenge

import android.os.Bundle
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import io.marleyspoonscodingchallenge.extensions.showToast
import io.marleyspoonscodingchallenge.presentation.BaseViewModel
import org.koin.androidx.viewmodel.ext.android.getViewModel
import org.koin.core.parameter.parametersOf
import java.lang.reflect.ParameterizedType
import kotlin.reflect.KClass

abstract class BaseFragment<BINDING : ViewDataBinding, VIEW_MODEL : BaseViewModel>(
  private val layoutId: Int,
  parameterName: String? = null
) : Fragment() {

  private var _binding: BINDING? = null
  val binding get() = _binding!!

  private val argument: String? by lazy { arguments?.getString(parameterName) }
  val viewModel: VIEW_MODEL by lazy { getViewModel(clazz = viewModelClass()) { parametersOf(argument) } }

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    _binding = DataBindingUtil.inflate(inflater, layoutId, container, false)
    _binding.lifecycleOwner = viewLifecycleOwner

    setupBinding()

    return binding.root
  }

  abstract fun setupBinding()

  @Suppress("UNCHECKED_CAST")
  private fun viewModelClass(): KClass<VIEW_MODEL> {
    // https://stackoverflow.com/a/1901275/719212
    return ((javaClass.genericSuperclass as ParameterizedType)
      .actualTypeArguments[1] as Class<VIEW_MODEL>).kotlin
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)

    lifecycle.addObserver(viewModel)

    subscribeToNoInternetLiveData()
    subscribeToGenericErrorLiveDataLiveData()
    subscribeToShowMessageLiveData()
  }
  
  override fun onDestroyView() {
    _binding=null
    super.onDestroyView()
  }

  private fun subscribeToNoInternetLiveData() {
    viewModel.internetErrorLiveData.observe(viewLifecycleOwner, {
      context?.showToast(R.string.error_internet)
    })
  }

  private fun subscribeToGenericErrorLiveDataLiveData() {
    viewModel.genericErrorLiveData.observe(viewLifecycleOwner, {
      context?.showToast(R.string.error_generic)
    })
  }

  private fun subscribeToShowMessageLiveData() {
    viewModel.showMessageResId.observe(viewLifecycleOwner, { stringId -> stringId?.let { context?.showToast(it) } })
  }
}
