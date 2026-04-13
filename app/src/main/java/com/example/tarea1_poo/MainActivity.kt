package com.example.tarea1_poo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.tarea1_poo.ui.screen.FormularioPedido
import com.example.tarea1_poo.ui.theme.Tarea1_PooTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            Tarea1_PooTheme {
                FormularioPedido()
            }
        }
    }
}