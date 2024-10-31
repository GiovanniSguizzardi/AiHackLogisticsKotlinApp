package br.com.challenge.aihacklogistics.activity.agendamentos

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import br.com.challenge.aihacklogistics.R
import br.com.challenge.aihacklogistics.databinding.PaginaInicialBinding

class PaginaInicialActivity : Fragment() {

    private var _binding: PaginaInicialBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = PaginaInicialBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Alterna entre logobranca e logo com base no tema (escuro ou claro)
        val nightModeFlags = resources.configuration.uiMode and android.content.res.Configuration.UI_MODE_NIGHT_MASK

        if (nightModeFlags == android.content.res.Configuration.UI_MODE_NIGHT_YES) {
            // Modo escuro
            binding.logoImage.setImageDrawable(ContextCompat.getDrawable(requireContext(),
                R.drawable.logobranca
            ))
        } else {
            // Modo claro
            binding.logoImage.setImageDrawable(ContextCompat.getDrawable(requireContext(),
                R.drawable.logo
            ))
        }

        // Navegação para a página de CADASTRO
        binding.buttonFirst.setOnClickListener {
            findNavController().navigate(R.id.action_PaginaInicial_to_PaginaAgendamento)
        }

        // Navegação para a página de LISTAGEM de clientes
        binding.buttonListar.setOnClickListener {
            findNavController().navigate(R.id.action_PaginaInicial_to_ListarClientesActivity)
        }

        // Navegação para a página de EXCLUSÃO de clientes
        binding.buttonExcluir.setOnClickListener {
            findNavController().navigate(R.id.action_PaginaInicial_to_PaginaExcluirActivity)
        }

        // Navegação para a página de SOLICITAÇÃO DE MEDICAMENTOS via FAB
        binding.fabSolicitarRemedio.setOnClickListener {
            findNavController().navigate(R.id.action_PaginaInicial_to_PaginaMedicamentosActivity)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}