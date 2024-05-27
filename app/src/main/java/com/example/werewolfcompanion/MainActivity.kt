package com.example.werewolfcompanion

import android.os.Bundle
import android.widget.GridView
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyGridScope
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.werewolfcompanion.ui.theme.WerewolfCompanionTheme
import com.example.werewolfcompanion.Role

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            WerewolfCompanionTheme {
                val navController = rememberNavController()
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    NavHost(navController = navController, startDestination = "main") {
                        composable("main") {
                            MainScreen(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .padding(innerPadding)
                                    .background(Color.Gray),
                                navController = navController
                            )
                        }
                        composable("settings") {
                            SettingsScreen(navController = navController)
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun MainScreen(modifier: Modifier = Modifier, navController: NavHostController) {
    var selectedRoles by remember { mutableStateOf<List<Role>>(emptyList()) }

    Box(
        modifier = modifier

    ) {
        Text(
            text = "Selected Roles",
            modifier = Modifier.align(Alignment.TopCenter)
        )
        Box(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .fillMaxWidth(0.9f)
                .fillMaxHeight(0.15f)
                .padding(32.dp)
                .background(Color.LightGray)
        ){

            LazyRow {
                items(selectedRoles) { role ->
                    Box(
                        modifier = Modifier
                            .padding(horizontal = 4.dp)
                            .aspectRatio(1f)
                            .background(Color.White)
                            .align(alignment = Alignment.BottomCenter)// Set background color
                    ) {
                        RoleItem(role) { _, _ ->}

                    }
                }
            }
        }

        // Middle rectangle to hold icons
        Box(
            modifier = Modifier
                .align(Alignment.Center)
                .fillMaxWidth(0.8f)
                .fillMaxHeight(0.6f)
                .background(Color.LightGray)
        ) {
            LazyVerticalGrid(columns = GridCells.Adaptive(minSize = 64.dp)
            ) {

                var roles: List<Role> = listOf(
                    Role(1, "Werewolf"),
                    Role(2, "Villager"),
                    Role(3, "Seer"),
                    Role(1, "Role"),
                    Role(1, "Role"),
                    Role(1, "Role"),
                    Role(1, "Role"),
                    Role(1, "Role"),
                    Role(1, "Role"),
                    Role(1, "Role"),
                    Role(1, "Role"),
                    Role(1, "Role"),
                    Role(1, "Role"),
                    Role(1, "Role"),
                    Role(1, "Role"),
                    Role(1, "Role")
                )

                items(roles) { role ->
                    RoleItem(role) { selectedRole, isSelected ->
                        if (isSelected) {
                            selectedRoles = selectedRoles + selectedRole
                        } else {
                            selectedRoles = selectedRoles - selectedRole
                        }
                    }
                }
            }

        }

        Column(
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(16.dp)
        ) {
            Button(onClick = { /* TODO: Handle button click */ }) {
                Text("                  Start                  ")
            }
            Spacer(modifier = Modifier.height(8.dp))
            Button(onClick = { /* TODO: Handle button click */ }) {
                Text("             Edit Roles             ")
            }
        }

        Button(
            onClick = { navController.navigate("settings") },
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(16.dp)
        ) {
            Text("Settings")
        }
    }
}


@Composable
fun SettingsScreen(navController: NavHostController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(text = "Settings")
        Spacer(modifier = Modifier.height(16.dp))
        // Add your settings UI elements here

        Button(onClick = { navController.popBackStack() }) {
            Text("Back")
        }
    }
}

 @Composable
 fun EditRolesScreen(navController: NavHostController) {
     Box(modifier = Modifier
         .fillMaxWidth()
         .padding(16.dp)
     ) {

     }

 }


@Composable
fun RoleItem(role: Role,  onRoleSelected: (Role, Boolean) -> Unit) {
    var isSelected by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .aspectRatio(1f)
            .fillMaxSize()
            .padding(3.dp)
            .background(if (isSelected) Color.Green else Color.White)
            .clickable {
                isSelected = !isSelected
                onRoleSelected(role, isSelected)
            }
    ) {
        Text(text = role.roleName)
    }
}
@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    WerewolfCompanionTheme {
        MainScreen(navController = rememberNavController())
    }
}


