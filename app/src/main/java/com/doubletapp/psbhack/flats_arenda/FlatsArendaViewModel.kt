package com.doubletapp.psbhack.flats_arenda

import androidx.lifecycle.LiveData
import com.alex66rus.basearchkotlin.base.ExtendedMutableLiveData
import com.doubletapp.psbhack.base.BaseViewModel

class FlatsArendaViewModel : BaseViewModel() {

    private val repo = FlatsRepo()

    var flats: LiveData<List<FlatModel>> = ExtendedMutableLiveData(repo.getFlats())
}