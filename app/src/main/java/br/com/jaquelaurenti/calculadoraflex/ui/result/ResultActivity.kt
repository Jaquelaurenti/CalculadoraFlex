package br.com.jaquelaurenti.calculadoraflex.ui.result

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import br.com.jaquelaurenti.calculadoraflex.R
import br.com.jaquelaurenti.calculadoraflex.extensions.format
import kotlinx.android.synthetic.main.activity_result.*

class ResultActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)
        if (intent.extras == null) {
            Toast.makeText(this, "Não foi possível realizar a operação",
                Toast.LENGTH_SHORT).show()
        } else {
            calculate() }

        supportActionBar?.setDisplayHomeAsUpEnabled(true); // utilizado para o voltar da tela
        supportActionBar?.setDisplayShowHomeEnabled(true);
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed() // retornando o voltar
        return true
    }

    private fun calculate() {
        val gasPrice = intent.extras?.getDouble("GAS_PRICE", 0.0)
        val ethanolPrice = intent.extras?.getDouble("ETHANOL_PRICE", 0.0)

        val gasAverage = intent.extras?.getDouble("GAS_AVERAGE", 0.0)
        val ethanolAverage = intent.extras?.getDouble("ETHANOL_AVERAGE", 0.0)

        val performanceOfMyCar = ethanolAverage?.div(gasAverage!!)
        val priceOfFuelIndice = ethanolPrice?.div(gasPrice!!)

        if (priceOfFuelIndice != null) {

            if (priceOfFuelIndice <= performanceOfMyCar!!) {
                tvSuggestion.text = getString(R.string.ethanol)
            } else {
                tvSuggestion.text = getString(R.string.gasoline)
            }
        }

        // Resultado Ethanol
        if (ethanolPrice != null) {
            tvEthanolAverageResult.text = (ethanolPrice / ethanolAverage!!).format(2)
        }
        // Resultado Gasolina
        if (gasPrice != null) {
            tvGasAverageResult.text = (gasPrice / gasAverage!!).format(2)
        }
        // Resultado performance do carro
        if (performanceOfMyCar != null) {
            tvFuelRatio.text = getString(R.string.label_fuel_ratio, performanceOfMyCar.format(2))
        }

    }
}
