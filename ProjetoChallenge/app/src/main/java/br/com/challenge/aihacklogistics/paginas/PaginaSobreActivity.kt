package br.com.challenge.aihacklogistics.paginas

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.com.challenge.aihacklogistics.databinding.PaginaSobreBinding

class PaginaSobreActivity : AppCompatActivity() {

    private lateinit var binding: PaginaSobreBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = PaginaSobreBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}