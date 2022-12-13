package com.example.grillayout.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    /* шина для общения вьюмодели и фрагмента */
    val actions = MutableSharedFlow<Actions>()

    val catListLiveData = MutableLiveData<List<Cat>>()
    val listCatAgeLiveData = MutableLiveData<List<CatAge>>()

    private val selectedAge = mutableListOf<Int>()

    private fun updateCatList() {
        catListLiveData.value = getCatList()
    }

    init {
        updateCatList()
    }

    // предоставляет мокнутые данные для списка
    private fun getCatList(): List<Cat> {
        val catList =
            getMockCatList() // представим что getMockCatList возвращает нам список из интернета
                  // если список выбраных элементов не пустой, производим сортировку
                .filter { if (selectedAge.isNotEmpty()) selectedAge.contains(it.age) else true }

        // добавление onClick для cat экземпляров
        catList.forEach {
            it.onClick = { onCatItemClick(it) }
        }

        if (listCatAgeLiveData.value.isNullOrEmpty()) {
            val setOfAge = catList.map { it.age }.toSet().toMutableList().sorted()
            listCatAgeLiveData.value = setOfAge.map { age ->

                // заполняем список моделей для выбора возраста котиков
                val isSelectedMutableLiveData = MutableLiveData(false)
                CatAge(
                    age = age,
                    isSelected = isSelectedMutableLiveData,
                    onClick = {
                        if (selectedAge.contains(age)) {
                            selectedAge.remove(age)
                            isSelectedMutableLiveData.value = false
                        } else {
                            selectedAge.add(age)
                            isSelectedMutableLiveData.value = true
                        }
                        updateCatList()
                    }
                )
            }
        }


        return catList
    }

    private fun onCatItemClick(cat: Cat) {
        viewModelScope.launch {
            actions.emit(Actions.OpenNewFragment(cat))
        }
    }

    private fun getMockCatList(): List<Cat> {
        val catList = mutableListOf<Cat>()
        catList.add(Cat("Гоша", 1))
        catList.add(Cat("Дима", 1))
        catList.add(Cat("Костя", 6))
        catList.add(Cat("Антон", 6))
        catList.add(Cat("Слава", 7))
        catList.add(Cat("Мурзик", 9))
        catList.add(Cat("Эдуард", 9))
        catList.add(Cat("Павел", 12))
        catList.add(Cat("Грибоед", 12))
        catList.add(Cat("Хвост", 25))
        catList.add(Cat("Барнаул", 25))
        catList.add(Cat("Гена", 22))
        catList.add(Cat("Степа", 32))
        catList.add(Cat("Мышка", 54))
        catList.add(Cat("Автобус", 54))
        catList.add(Cat("Круасан", 54))
        catList.add(Cat("Желудь", 54))
        return catList
    }
}

sealed class Actions {
    data class OpenNewFragment(val cat: Cat) : Actions()
}

data class Cat(
    val name: String,
    val age: Int
) {
    var onClick: () -> Unit = {}
}

data class CatAge(
    val age: Int,
    val isSelected: MutableLiveData<Boolean>,
    var onClick: () -> Unit = {}
)