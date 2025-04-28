package com.example.nexvia.data

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

class AuthRepository(
    private val auth: FirebaseAuth = FirebaseAuth.getInstance(),
    private val firestore: FirebaseFirestore = FirebaseFirestore.getInstance()
) {
    suspend fun registerPasajero(nombre: String, email: String, password: String): Result<Unit> {
        return try {
            val result = auth.createUserWithEmailAndPassword(email, password).await()
            val uid = result.user?.uid ?: throw Exception("UID no encontrado")

            // Guardar datos en Firestore
            val userMap = mapOf(
                "nombre" to nombre,
                "email" to email,
                "rol" to "pasajero"
            )
            firestore.collection("usuarios").document(uid).set(userMap).await()

            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun registerConductor(nombre: String, email: String, password: String, placa: String, ruta: String): Result<Unit> {
        return try {
            val result = auth.createUserWithEmailAndPassword(email, password).await()
            val uid = result.user?.uid ?: throw Exception("UID no encontrado")

            val userMap = mapOf(
                "nombre" to nombre,
                "email" to email,
                "rol" to "conductor",
                "placa" to placa,
                "ruta" to ruta
            )
            firestore.collection("usuarios").document(uid).set(userMap).await()

            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun login(email: String, password: String): Result<String> {
        return try {
            val result = auth.signInWithEmailAndPassword(email, password).await()
            val uid = result.user?.uid ?: throw Exception("UID no encontrado")

            // Buscar el rol
            val snapshot = firestore.collection("usuarios").document(uid).get().await()
            val rol = snapshot.getString("rol") ?: throw Exception("Rol no encontrado")

            Result.success(rol)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}