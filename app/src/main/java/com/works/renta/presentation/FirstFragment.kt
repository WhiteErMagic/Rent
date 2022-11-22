package com.works.renta.presentation

import android.content.Context
import android.os.Bundle
import android.view.*
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentResultListener
import androidx.lifecycle.ViewModelProvider
import com.works.renta.TheApp
import com.works.renta.databinding.FragmentFirstBinding
import com.works.renta.databinding.FragmentTariffsBinding
import com.works.renta.domain.DataRepository
import org.json.JSONObject
import java.lang.RuntimeException
import javax.inject.Inject

class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null
    private val binding: FragmentFirstBinding
    get() = _binding?: throw RuntimeException("FragmentFirstBinding = null")

    @Inject
    lateinit var repository: DataRepository

    private val component by lazy {
        (requireActivity().application as TheApp).component
            .fragmentComponentFactory().create()
    }

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val viewModel by lazy{
        ViewModelProvider(this, viewModelFactory)[FirstFragmentViewModel::class.java]
    }

    override fun onAttach(context: Context) {
        component.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFirstBinding.inflate(layoutInflater)

        viewModel.dataOfPeriod.observe(viewLifecycleOwner){ dataModel ->
            dataModel?.let {
                binding.dataModel = dataModel
            }
        }

        viewModel.periodStr.observe(viewLifecycleOwner){ period ->
            period?.let {
                binding.txtPeriod.text = period
            }
        }

        viewModel.summa.observe(viewLifecycleOwner){ summa ->
            summa?.let {
                binding.summa.text = summa
            }
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.txtPeriod.setOnClickListener {
            val monthYearPickerDialog = MonthYearPickerDialog(viewModel.getPeriodDate())
            activity?.let {
                monthYearPickerDialog.show(
                    requireActivity().supportFragmentManager,
                    MonthYearPickerDialog.TAG
                )
            }
        }
        setupMonthYearPickerDialogListener()

        binding.tarifs.setOnClickListener{
            toTariffs()
        }

        binding.calculate.setOnClickListener{
            doCalculate()
        }

        binding.clear.setOnClickListener{
            doClear()
        }
    }

    private fun doClear() {
        viewModel.doClear()
    }

    private fun setupMonthYearPickerDialogListener(){
        activity?.let{
            requireActivity().supportFragmentManager.setFragmentResultListener(MonthYearPickerDialog.REQUEST_KEY, requireActivity(),
                FragmentResultListener{_, result ->
                    viewModel.setPeriodDate(result.getLong(MonthYearPickerDialog.KEY_PERIOD))
                })
        }
    }

    private fun toTariffs() {
        val fragment = TariffsFragment.newInstance(viewModel.getPeriodDate())
        navigator().goToNext(fragment)
    }

    private fun doCalculate() {
        viewModel.doCalculate(binding.dataModel!!)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}