package com.works.renta.presentation

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.works.renta.TheApp
import com.works.renta.databinding.FragmentTariffsBinding
import com.works.renta.domain.DataRepository
import java.lang.RuntimeException
import javax.inject.Inject

class TariffsFragment : Fragment() {

    private var _binding: FragmentTariffsBinding? = null
    private val binding: FragmentTariffsBinding
    get() = _binding?: throw RuntimeException("FragmentTarifsBinding = null")
    private val KEY_PERIOD = "PERIOD"

    @Inject
    lateinit var repository: DataRepository

    val component by lazy {
        (requireActivity().application as TheApp).component
            .fragmentComponentFactory().create()
    }

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val viewModel by lazy{
        ViewModelProvider(this, viewModelFactory)[TariffsFragmentViewModel::class.java]
    }

    override fun onAttach(context: Context) {
        component.inject(this)
        super.onAttach(context)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            if (it.containsKey(KEY_PERIOD))
                viewModel.setPeriodDate(it.getLong(KEY_PERIOD))
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentTariffsBinding.inflate(layoutInflater)

        viewModel.periodStr.observe(viewLifecycleOwner){ period ->
            period?.let {
                binding.txtPeriod.text = period
            }
        }

        viewModel.tariffsOfPeriod.observe(viewLifecycleOwner){ tariffs ->
            tariffs?.let {
                binding.tariffsModel = tariffs
            }
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.save.setOnClickListener{
            saveTariffs()
            parentFragmentManager
                .popBackStack()
        }
    }

    private fun saveTariffs() {
        viewModel.saveTariffs(binding.tariffsModel!!)
    }

    companion object {
        @JvmStatic
        fun newInstance(arg:Long): TariffsFragment{
            return  TariffsFragment().apply {
                arguments = Bundle().apply {
                    putLong(KEY_PERIOD, arg)
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}