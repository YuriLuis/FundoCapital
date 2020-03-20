package com.example.fundodecapital.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.SeekBar
import android.widget.TextView
import android.widget.Toast
import com.example.fundodecapital.R
import com.example.fundodecapital.model.ValorFuturo
import com.google.android.material.textfield.TextInputEditText

class MainActivity : AppCompatActivity() {

    private lateinit var inputTaxaDeJuros : TextInputEditText
    private lateinit var inputCapitalInicial : TextInputEditText
    private lateinit var seekBarMeses : SeekBar
    private lateinit var textViewMeses : TextView
    private lateinit var resultadoTextView : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        instanciaLayoutComXml()
        seekBarChangeListiner()
    }

    fun instanciaLayoutComXml() {
        inputTaxaDeJuros = findViewById(R.id.inputTextTaxaDeJuros)
        inputCapitalInicial = findViewById(R.id.inputTextCapitalInicial)
        seekBarMeses = findViewById(R.id.seekBar)
        textViewMeses = findViewById(R.id.textViewQuantidadeMeses)
        resultadoTextView = findViewById(R.id.resultadoTextView)

        textViewMeses.setText(seekBarMeses.progress.toString())

    }

    private fun seekBarChangeListiner(){

        seekBarMeses.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{

            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {

                if (validaCampos()){
                    var valorFuture : ValorFuturo = ValorFuturo(inputTaxaDeJuros.text.toString().toDouble(),
                                                    inputCapitalInicial.text.toString().toDouble(),
                                                    progress)

                    var resultado : String = String.format("R$: %.2f", +valorFuture.calculaValorFuturo())
                    textViewMeses.text = progress.toString()
                    resultadoTextView.text = resultado.toString()
                }
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {

            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {

            }

        })
        /*

        seekBarMeses.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                if (validaCampos()){
                    var valorFuture: ValorFuturo = ValorFuturo(inputTaxaDeJuros.text.toString().toDouble(),
                        inputCapitalInicial.text.toString().toDouble(),
                        progress)

                    var resultado : Double = valorFuture.calculaValorFuturo()
                    textViewMeses.text = progress.toString()
                    resultadoTextView.text = resultado.toString()
                }
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {

            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {

            }

        })

         */
    }

    fun validaCampos(): Boolean{
        var validou = true

        if (inputCapitalInicial.text.isNullOrEmpty()){
            inputCapitalInicial.error = "Campo Obrigatório"
            validou = false
        }
        if (inputTaxaDeJuros.text.isNullOrEmpty()){
            inputTaxaDeJuros.error = "Campo Obrigatório"
            validou = false
        }

        if (seekBarMeses.progress <= 0){
            Toast.makeText(this, "Informe quantidade de meses!", Toast.LENGTH_SHORT).show()
            validou = false
        }

        return validou
    }
}
