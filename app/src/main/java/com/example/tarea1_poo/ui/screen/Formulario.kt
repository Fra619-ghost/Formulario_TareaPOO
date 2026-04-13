package com.example.tarea1_poo.ui.screen

// Nota decudi hacerlo como el ejemplo que hizo en clase, ya que me parecio la forma sencilla y ordenada

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun FormularioPedido() {

    //Aquí se definen las variables del formulario usando remember y mutableStateOf,
    // lo que permite guardar los datos ingresados por el usuario y actualizar la interfaz automáticamente.
    var nombre by remember { mutableStateOf("") }
    var telefono by remember { mutableStateOf("") }
    var direccion by remember { mutableStateOf("") }
    var producto by remember { mutableStateOf("") }
    var cantidad by remember { mutableStateOf("") }
    var notas by remember { mutableStateOf("") }

    var cargando by remember { mutableStateOf(false) }
    var mensajeEstado by remember { mutableStateOf("") }

    val nombreError = nombre.isBlank() || nombre.length < 3
    val telefonoError = telefono.isBlank() || !telefono.all { it.isDigit() } || telefono.length < 8
    val direccionError = direccion.isBlank()
    val productoError = producto.isBlank()
    val cantidadError = cantidad.isBlank() || cantidad.toIntOrNull() == null || cantidad.toIntOrNull()!! <= 0

    val formularioValido = !(nombreError || telefonoError || direccionError || productoError || cantidadError)

    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    Scaffold( // esta es la estructuira basica como un contenedor principal mas q todo
        snackbarHost = { SnackbarHost(snackbarHostState) }
    ) { padding ->

        Column( // esta columna sirve mas que todo para organizar el formulario de manera vertical
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp)
        ) {
            Text(
                text = "Formulario de Pedido",
                style = MaterialTheme.typography.headlineMedium
            )

            Spacer(modifier = Modifier.height(20.dp))

            OutlinedTextField( //cada uno de estos son los campos de texto
                value = nombre,
                onValueChange = { nombre = it },
                label = { Text("Nombre del cliente") },
                isError = nombreError,
                supportingText = {
                    if (nombreError) { // estos son mss de error para validacion
                        Text("Debe tener al menos 3 caracteres")
                    }
                }
            )

            Spacer(modifier = Modifier.height(10.dp))

            OutlinedTextField(
                value = telefono,
                onValueChange = { telefono = it },
                label = { Text("Teléfono") },
                isError = telefonoError,
                supportingText = {
                    if (telefonoError) {
                        Text("Solo números y mínimo 8 dígitos")
                    }
                }
            )

            Spacer(modifier = Modifier.height(10.dp))

            OutlinedTextField(
                value = direccion,
                onValueChange = { direccion = it },
                label = { Text("Dirección") },
                isError = direccionError,
                supportingText = {
                    if (direccionError) {
                        Text("La dirección es obligatoria")
                    }
                }
            )

            Spacer(modifier = Modifier.height(10.dp))

            OutlinedTextField(
                value = producto,
                onValueChange = { producto = it },
                label = { Text("Producto") },
                isError = productoError,
                supportingText = {
                    if (productoError) {
                        Text("El producto es obligatorio")
                    }
                }
            )

            Spacer(modifier = Modifier.height(10.dp))

            OutlinedTextField(
                value = cantidad,
                onValueChange = { cantidad = it },
                label = { Text("Cantidad") },
                isError = cantidadError,
                supportingText = {
                    if (cantidadError) {
                        Text("La cantidad debe ser mayor que 0")
                    }
                }
            )

            Spacer(modifier = Modifier.height(10.dp))

            OutlinedTextField(
                value = notas,
                onValueChange = { notas = it },
                label = { Text("Notas adicionales (opcional)") }
            )

            Spacer(modifier = Modifier.height(20.dp))

            if (cargando) {
                CircularProgressIndicator()
                Spacer(modifier = Modifier.height(10.dp))
                Text("Enviando pedido...")
            }

            if (mensajeEstado.isNotEmpty()) {
                Spacer(modifier = Modifier.height(10.dp))
                Text(mensajeEstado)
            }

            Spacer(modifier = Modifier.height(20.dp))

            Button(
                onClick = {
                    scope.launch {
                        cargando = true
                        mensajeEstado = ""
                        delay(2000)
                        cargando = false
                        mensajeEstado = "Pedido enviado correctamente"
                        snackbarHostState.showSnackbar("Pedido enviado")
                        // esto limpia los campos
                        nombre = ""
                        telefono = ""
                        direccion = ""
                        producto = ""
                        cantidad = ""
                        notas = ""
                    }
                },
                enabled = formularioValido && !cargando
            ) {
                Text("Enviar")
            }

            Spacer(modifier = Modifier.height(10.dp))

            Button( // Esto es para reinicar o limpoar el formulario
                onClick = {
                    nombre = ""
                    telefono = ""
                    direccion = ""
                    producto = ""
                    cantidad = ""
                    notas = ""
                    mensajeEstado = ""
                }
            ) {
                Text("Limpiar")
            }
        }
    }
}
