package dev.thiaago.finn.features.home.ui.states

sealed class CreateReleaseState {
    data object CreateReleaseInitialState : CreateReleaseState()
    data object CreateReleaseSuccess : CreateReleaseState()
    data object CreateReleaseLoading : CreateReleaseState()
    data object CreateReleaseError : CreateReleaseState()
}