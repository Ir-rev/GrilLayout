package com.example.grillayout.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    /* шина для общения вьюмодели и фрагмента */
    val actions = MutableSharedFlow<Actions>()

    // предоставляет мокнутые данные для списка
    fun getMockData(): List<Cat> {
        val result = mutableListOf<Cat>()
        result.add(Cat("Гоша", 1))
        result.add(Cat("Дима", 2))
        result.add(Cat("Костя", 4))
        result.add(Cat("Антон", 6))
        result.add(Cat("Слава", 7))
        result.add(Cat("Мурзик", 8))
        result.add(Cat("Эдуард", 9))
        result.add(Cat("Павел", 12))
        result.add(Cat("Грибоед", 15))
        result.add(Cat("Хвост", 18))
        result.add(Cat("Барнаул", 25))
        result.add(Cat("Гена", 22))
        result.add(Cat("Степа", 32))
        result.add(Cat("Мышка", 54))
        result.add(Cat("Автобус", 65))
        result.add(Cat("Круасан", 52))
        result.add(Cat("Желудь", 77))

        // добавление onClick для cat экземпляров
        result.forEach {
            it.onClick = { onItemClick(it) }
        }

        return result
    }

    private fun onItemClick(cat: Cat) {
        viewModelScope.launch {
            actions.emit(Actions.OpenNewFragment(cat))
        }
    }
}

sealed class Actions {
    data class OpenNewFragment(val cat: Cat) : Actions()
}

data class Cat(
    val name: String,
    val age: Int
){
    var onClick: () -> Unit = {}
}