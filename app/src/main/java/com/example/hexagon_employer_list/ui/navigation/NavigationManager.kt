package br.com.flexpag.mainappneopay.ui.navigation

import androidx.navigation.NavHostController
import com.example.hexagon_employer_list.ui.navigation.NavigationEvent
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow

class NavigationManager {
    private val _navigationEventChannel = MutableSharedFlow<NavigationEvent>(
        extraBufferCapacity = 1,
        onBufferOverflow = BufferOverflow.DROP_OLDEST
    )
    val navigationEventChannel = _navigationEventChannel.asSharedFlow()

    fun navigate(event: NavigationEvent) {
        _navigationEventChannel.tryEmit(event)
    }

    fun handleEvent(
        event: NavigationEvent,
        navController: NavHostController
    ) {
        when (event) {
            NavigationEvent.NavigateBack -> navController.navigateUp()
            else -> {
                val routeWithParams = addParameters(
                    route = event.destination!!.route,
                    params = event.params
                )
                navController.navigate(routeWithParams)
            }
        }
    }

    private fun addParameters(route: String, params: Map<String, String>?): String {
        return route.plus("?").plus(
            params?.let { it.map { item -> "${item.key}=${item.value}".plus("?") } }
        )
    }
}