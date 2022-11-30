package com.example.grillayout.ui.main

import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {

    // предоставляет мокнутые данные для списка
    fun getMockData():List<Cat>{
        val result = mutableListOf<Cat>()
        result.add(Cat("Гоша",1))
        result.add(Cat("Дима",2))
        result.add(Cat("Костя",4))
        result.add(Cat("Антон",6))
        result.add(Cat("Слава",7))
        result.add(Cat("Мурзик",8))
        result.add(Cat("Эдуард",9))
        result.add(Cat("Павел",12))
        result.add(Cat("Грибоед",15))
        result.add(Cat("Хвост",18))
        result.add(Cat("Барнаул",25))
        result.add(Cat("Гена",22))
        result.add(Cat("Степа",32))
        result.add(Cat("Мышка",54))
        result.add(Cat("Автобус",65))
        result.add(Cat("Круасан",52))
        result.add(Cat("Желудь",77))
        return result
    }
}

data class Cat(
    val name:String,
    val age:Int
)