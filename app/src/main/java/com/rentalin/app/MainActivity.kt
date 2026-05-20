package com.rentalin.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.FilterList
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Inventory2
import androidx.compose.material.icons.outlined.MoreVert
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material.icons.outlined.Work
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.rentalin.core.designsystem.theme.RentalInOnSurfaceVariant
import com.rentalin.core.designsystem.theme.RentalInPrimary
import com.rentalin.core.designsystem.theme.RentalInSurfaceContainerLowest
import com.rentalin.core.designsystem.theme.RentalInTheme
import com.rentalin.feature.customers.CustomersScreen
import com.rentalin.feature.dashboard.DashboardAddRentalButton
import com.rentalin.feature.dashboard.DashboardScreen
import com.rentalin.feature.inventory.InventoryScreen
import com.rentalin.feature.rentals.RentalsScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            RentalInTheme {
                RentalInApp()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RentalInApp() {
    val navController = rememberNavController()
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = backStackEntry?.destination
    val currentRoute = currentDestination?.route ?: MainDestination.Dashboard.route

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = MaterialTheme.colorScheme.background,
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = stringResource(titleForRoute(currentRoute)),
                        style = MaterialTheme.typography.headlineMedium,
                    )
                },
                actions = {
                    HeaderActions(currentRoute)
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = RentalInSurfaceContainerLowest,
                    titleContentColor = RentalInPrimary,
                    actionIconContentColor = RentalInOnSurfaceVariant,
                ),
            )
        },
        bottomBar = {
            RentalInBottomBar(
                currentDestination = currentDestination,
                onDestinationClick = { destination ->
                    navController.navigate(destination.route) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
            )
        },
        floatingActionButton = {
            if (currentRoute == MainDestination.Dashboard.route) {
                DashboardAddRentalButton()
            }
        },
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = MainDestination.Dashboard.route,
            modifier = Modifier.padding(innerPadding),
        ) {
            composable(MainDestination.Dashboard.route) {
                DashboardScreen()
            }
            composable(MainDestination.Rentals.route) {
                RentalsScreen()
            }
            composable(MainDestination.Inventory.route) {
                InventoryScreen()
            }
            composable(MainDestination.Customers.route) {
                CustomersScreen()
            }
        }
    }
}

@Composable
private fun HeaderActions(route: String) {
    IconButton(onClick = {}) {
        Icon(
            imageVector = Icons.Outlined.Search,
            contentDescription = stringResource(R.string.action_search),
        )
    }
    IconButton(onClick = {}) {
        Icon(
            imageVector = if (route == MainDestination.Dashboard.route) {
                Icons.Outlined.MoreVert
            } else {
                Icons.Outlined.FilterList
            },
            contentDescription = if (route == MainDestination.Dashboard.route) {
                stringResource(R.string.action_menu)
            } else {
                stringResource(R.string.action_filter)
            },
        )
    }
}

@Composable
private fun RentalInBottomBar(
    currentDestination: NavDestination?,
    onDestinationClick: (MainDestination) -> Unit,
) {
    NavigationBar(containerColor = RentalInSurfaceContainerLowest) {
        MainDestination.entries.forEach { destination ->
            val selected = currentDestination?.hierarchy?.any { it.route == destination.route } == true
            NavigationBarItem(
                selected = selected,
                onClick = { onDestinationClick(destination) },
                icon = {
                    Icon(
                        imageVector = destination.icon,
                        contentDescription = stringResource(destination.labelRes),
                    )
                },
                label = {
                    Text(text = stringResource(destination.labelRes))
                },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = RentalInPrimary,
                    selectedTextColor = RentalInPrimary,
                    unselectedIconColor = RentalInOnSurfaceVariant,
                    unselectedTextColor = RentalInOnSurfaceVariant,
                    indicatorColor = RentalInSurfaceContainerLowest,
                ),
            )
        }
    }
}

private enum class MainDestination(
    val route: String,
    val labelRes: Int,
    val icon: ImageVector,
) {
    Dashboard(
        route = "dashboard",
        labelRes = R.string.tab_dashboard,
        icon = Icons.Outlined.Home,
    ),
    Rentals(
        route = "rentals",
        labelRes = R.string.tab_rentals,
        icon = Icons.Outlined.Work,
    ),
    Inventory(
        route = "inventory",
        labelRes = R.string.tab_inventory,
        icon = Icons.Outlined.Inventory2,
    ),
    Customers(
        route = "customers",
        labelRes = R.string.tab_customers,
        icon = Icons.Outlined.Person,
    ),
}

private fun titleForRoute(route: String) = when (route) {
    MainDestination.Rentals.route -> R.string.tab_rentals
    MainDestination.Inventory.route -> R.string.tab_inventory
    MainDestination.Customers.route -> R.string.tab_customers
    else -> R.string.app_name
}
