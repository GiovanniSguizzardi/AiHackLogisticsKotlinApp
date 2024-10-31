package br.com.challenge.aihacklogistics.activity.medicamentos

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import br.com.challenge.aihacklogistics.R
import br.com.challenge.aihacklogistics.databinding.ActivityInicialMedicamentosBinding

class PaginaMedicamentosActivity : Fragment() {

    private var _binding: ActivityInicialMedicamentosBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = ActivityInicialMedicamentosBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Alterna entre logo branca e logo padrão com base no tema (escuro ou claro)
        val nightModeFlags = resources.configuration.uiMode and android.content.res.Configuration.UI_MODE_NIGHT_MASK

        if (nightModeFlags == android.content.res.Configuration.UI_MODE_NIGHT_YES) {
            binding.logoImage.setImageDrawable(
                ContextCompat.getDrawable(requireContext(), R.drawable.logobranca)
            )
        } else {
            binding.logoImage.setImageDrawable(
                ContextCompat.getDrawable(requireContext(), R.drawable.logo)
            )
        }

        // Navegação para a página de SOLICITAÇÃO de medicamento
        binding.buttonSolicitarMedicamento.setOnClickListener {
            findNavController().navigate(R.id.action_PaginaMedicamentosActivity_to_PaginaSolicitacaoMedicamento)
        }

        // Navegação para a página de LISTAGEM de solicitações de medicamentos
        binding.buttonListarMedicamento.setOnClickListener {
            findNavController().navigate(R.id.action_PaginaMedicamentosActivity_to_ListarMedicamentos)
        }

       // Navegação para a página de CANCELAMENTO de solicitações de medicamentos
        binding.buttonCancelarMedicamento.setOnClickListener {
            findNavController().navigate(R.id.action_PaginaSolicitacaoMedicamentos_to_CancelarMedicamentos)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}