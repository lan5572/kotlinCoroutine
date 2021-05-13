package com.carman.kotlin.coroutine.request

import androidx.activity.ComponentActivity
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.carman.kotlin.coroutine.extensions.createViewModel
import com.carman.kotlin.coroutine.request.repository.MainRepository
import com.carman.kotlin.coroutine.request.viewmodel.MainViewModel
import java.lang.NullPointerException
import java.lang.RuntimeException
import java.lang.reflect.ParameterizedType

object ViewModelUtils {

    /**
     * 直接创建
     */
    fun <VM : ViewModel> createViewModel(
        activity: ComponentActivity,
        factory: ViewModelProvider.Factory? = null,
        position: Int
    ): VM {
        return factory?.let {
            createViewModelByFactory(activity, it, position)
        } ?: let {
            val vbClass =
                (activity.javaClass.genericSuperclass as ParameterizedType).actualTypeArguments.filterIsInstance<Class<*>>()
            val viewModel = vbClass[position] as Class<VM>
            ViewModelProvider(activity).get(viewModel)
        }
    }

    /**
     * 通过构建工厂的方式创建
     */
    private fun <VM : ViewModel> createViewModelByFactory(
        activity: ComponentActivity,
        factory: ViewModelProvider.Factory,
        position: Int
    ): VM {
        val vbClass =
            (activity.javaClass.genericSuperclass as ParameterizedType).actualTypeArguments.filterIsInstance<Class<*>>()
        val viewModel = vbClass[position] as Class<VM>
        return when {
            viewModel as? Class<MainViewModel> != null -> {
                ViewModelProvider(
                    activity,
                    factory
                ).get(viewModel) as VM
            }
            else -> {
                throw RuntimeException(" Cannot create an instance of $viewModel")
            }
        }
    }

    /**
     * 直接创建
     */
    fun <VM : ViewModel> createActivityViewModel(
        fragment: Fragment,
        factory: ViewModelProvider.Factory? = null,
        position: Int
    ): VM {
        return factory?.let {
            createActivityViewModelByFactory(fragment, it, position)
        } ?: let {
            val vbClass =
                (fragment.javaClass.genericSuperclass as ParameterizedType).actualTypeArguments.filterIsInstance<Class<*>>()
            val viewModel = vbClass[position] as Class<VM>
            ViewModelProvider(fragment.requireActivity()).get(viewModel)
        }
    }

    /**
     * 通过构建工厂的方式创建
     */
    private fun <VM : ViewModel> createActivityViewModelByFactory(
        fragment: Fragment,
        factory: ViewModelProvider.Factory,
        position: Int
    ): VM {
        val vbClass =
            (fragment.javaClass.genericSuperclass as ParameterizedType).actualTypeArguments.filterIsInstance<Class<*>>()
        val viewModel = vbClass[position] as Class<VM>
        return when {
            viewModel as? Class<MainViewModel> != null -> {
                ViewModelProvider(
                    fragment.requireActivity(),
                    factory
                ).get(viewModel) as VM
            }
            else -> {
                throw RuntimeException(" Cannot create an instance of $viewModel")
            }
        }
    }


    /**
     * 直接创建
     */
    fun <VM : ViewModel> createViewModel(
        fragment: Fragment,
        factory: ViewModelProvider.Factory? = null,
        position: Int
    ): VM {
        return factory?.let {
            createViewModelByFactory(fragment, it, position)
        } ?: let {
            val vbClass =
                (fragment.javaClass.genericSuperclass as ParameterizedType).actualTypeArguments.filterIsInstance<Class<*>>()
            val viewModel = vbClass[position] as Class<VM>
            ViewModelProvider(fragment).get(viewModel)
        }
    }

    /**
     * 通过构建工厂的方式创建
     */
    private fun <VM : ViewModel> createViewModelByFactory(
        fragment: Fragment,
        factory: ViewModelProvider.Factory,
        position: Int
    ): VM {
        val vbClass =
            (fragment.javaClass.genericSuperclass as ParameterizedType).actualTypeArguments.filterIsInstance<Class<*>>()
        val viewModel = vbClass[position] as Class<VM>
        return when {
            viewModel as? Class<MainViewModel> != null -> {
                ViewModelProvider(
                    fragment,
                    factory
                ).get(viewModel) as VM
            }
            else -> {
                throw RuntimeException(" Cannot create an instance of $viewModel")
            }
        }
    }

}
