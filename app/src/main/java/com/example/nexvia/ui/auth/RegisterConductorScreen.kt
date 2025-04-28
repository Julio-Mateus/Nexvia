package com.example.nexvia.ui.auth

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.nexvia.navigation.Destinations
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

// ui/auth/RegisterConductorScreen.kt
@Composable
fun RegisterConductorScreen(navController: NavController) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var nombre by remember { mutableStateOf("") }
    var placa by remember { mutableStateOf("") }
    var ruta by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf<String?>(null) }

    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Registro Conductor", style = MaterialTheme.typography.headlineLarge)

        Spacer(modifier = Modifier.height(16.dp))

        TextField(
            value = nombre,
            onValueChange = { nombre = it },
            label = { Text("Nombre completo") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        TextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Correo electrónico") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        TextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Contraseña") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        TextField(
            value = placa,
            onValueChange = { placa = it },
            label = { Text("Placa del vehículo") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        TextField(
            value = ruta,
            onValueChange = { ruta = it },
            label = { Text("Número de ruta") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                if (email.isNotBlank() && password.isNotBlank() && nombre.isNotBlank() && placa.isNotBlank() && ruta.isNotBlank()) {
                    FirebaseAuth.getInstance()
                        .createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener { task ->
                            if (task.isSuccessful) {
                                val uid = task.result?.user?.uid
                                if (uid != null) {
                                    val db = FirebaseFirestore.getInstance()
                                    val conductorData = hashMapOf(
                                        "nombre" to nombre,
                                        "email" to email,
                                        "placa" to placa,
                                        "ruta" to ruta,
                                        "rol" to "conductor"
                                    )
                                    db.collection("usuarios")
                                        .document(uid)
                                        .set(conductorData)
                                        .addOnSuccessListener {
                                            Toast.makeText(context, "Registro exitoso", Toast.LENGTH_SHORT).show()
                                            navController.navigate(Destinations.HOME_CONDUCTOR) {
                                                popUpTo(Destinations.LOGIN) { inclusive = true }
                                            }
                                        }
                                        .addOnFailureListener { e ->
                                            errorMessage = "Error al guardar datos: ${e.message}"
                                        }
                                }
                            } else {
                                errorMessage = "Error en registro: ${task.exception?.message}"
                            }
                        }
                } else {
                    errorMessage = "Completa todos los campos"
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Registrar")
        }

        errorMessage?.let { msg ->
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = msg,
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}

