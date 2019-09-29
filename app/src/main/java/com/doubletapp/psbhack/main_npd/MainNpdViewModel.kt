package com.doubletapp.psbhack.main_npd

import androidx.lifecycle.LiveData
import com.alex66rus.basearchkotlin.base.ExtendedMutableLiveData
import com.doubletapp.psbhack.base.BaseViewModel
import com.doubletapp.psbhack.flats_arenda.FlatModel
import com.doubletapp.psbhack.flats_arenda.FlatsRepo

class MainNpdViewModel : BaseViewModel() {

    private val repo = ProfileRepo()

    val npdProfile: LiveData<NpdProfile> = ExtendedMutableLiveData(repo.getProfile())

    val operations: LiveData<List<OperationModel>> = ExtendedMutableLiveData(listOf(
            OperationModel(
                    name = "Разработка Django-приложения",
                    date = "12.10.19",
                    sum = "900",
                    withFiz = true
            ),
            OperationModel(
                    name = "Разработка Django-приложения",
                    date = "20.10.19",
                    sum = "1100",
                    withFiz = false
            ),
            OperationModel(
                    name = "Уплата налогов",
                    date = "25.10.19",
                    sum = "-100",
                    withFiz = true
            )
    ))
}