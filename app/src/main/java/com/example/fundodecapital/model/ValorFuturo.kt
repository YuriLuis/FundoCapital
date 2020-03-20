package com.example.fundodecapital.model

class ValorFuturo( taxaDeJuro: Double, capitalInicial: Double, meses: Int) {

    private var taxaDeJuro : Double = taxaDeJuro
    private var capitalAtual : Double = capitalInicial
    private var meses : Int  = meses


    fun calculaValorFuturo() = capitalAtual * Math.pow(1 + taxaDeJuro / 100 , meses.toDouble())

}