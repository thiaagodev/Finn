package dev.thiaago.finn.features.home.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.thiaago.finn.features.home.domain.entities.ReleaseEntity
import dev.thiaago.finn.features.home.domain.usecases.CreateReleaseUseCase
import dev.thiaago.finn.features.home.ui.states.CreateReleaseState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ReleaseViewModel @Inject constructor(
    private val createReleaseUseCase: CreateReleaseUseCase
) : ViewModel() {
    private val _createReleaseState =
        MutableStateFlow<CreateReleaseState>(CreateReleaseState.CreateReleaseInitialState)
    val createReleaseState = _createReleaseState.asStateFlow()

    fun createRelease(release: ReleaseEntity) {
        _createReleaseState.value = CreateReleaseState.CreateReleaseLoading
        viewModelScope.launch {
            val result = createReleaseUseCase(release)
            result.fold(
                onFailure = {
                    _createReleaseState.value = CreateReleaseState.CreateReleaseError
                },
                onSuccess = {
                    _createReleaseState.value = CreateReleaseState.CreateReleaseSuccess
                }
            )
        }
    }

}