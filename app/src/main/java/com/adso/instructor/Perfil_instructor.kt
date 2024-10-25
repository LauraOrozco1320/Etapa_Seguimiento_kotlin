package com.adso.instructor

import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class Perfil_instructor : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainScreen()
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    @Composable
    fun MainScreen() {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),

            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            HeaderSection()
            Spacer(modifier = Modifier.height(16.dp)) // Añadir espacio
            NotificationBar()
            Spacer(modifier = Modifier.height(16.dp)) // Añadir espacio
            InstructorInfo()
        }
    }

    @Composable
    fun HeaderSection() {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .background(Color.White),
            verticalAlignment = Alignment.Top
        ) {
            Image(
                painter = painterResource(id = R.drawable.logo_sena),
                contentDescription = "SENA Logo",
                modifier = Modifier.size(70.dp)
            )

            Spacer(modifier = Modifier.width(10.dp))

            Column {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Image(
                        painter = painterResource(id = R.drawable.logo),
                        contentDescription = "Etapa Productiva Logo",
                        modifier = Modifier.size(40.dp)
                    )
                    Column {
                        Text(
                            "Etapa",
                            fontSize = 12.sp,
                            color = Color(0xFF009E00),
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            "Productiva",
                            fontSize = 12.sp,
                            color = Color(0xFF009E00),
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
                Spacer(modifier = Modifier.height(15.dp))
                Text("Centro de Comercio y Servicios", fontSize = 14.sp, color = Color(0xFF009E00))
            }

            Spacer(modifier = Modifier.weight(1f))

            Image(
                painter = painterResource(id = R.drawable.mujer),
                contentDescription = "User Icon",
                modifier = Modifier
                    .size(45.dp)
                    .clickable {
                        // Acción al hacer clic en la imagen (Ej: navegar a otra actividad)
                        startActivity(Intent(this@Perfil_instructor, Perfil_instructor::class.java))
                    }
            )
        }
    }

    @Composable
    fun NotificationBar() {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(64.dp)
                .background(Color(0xFF009E00)), // Verde
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.End
        ) {
            Image(
                painter = painterResource(id = R.drawable.notificaciones),
                contentDescription = "Notification Icon",
                modifier = Modifier
                    .size(60.dp)
                    .clickable {
                        // Acción al hacer clic en la imagen (Ej: navegar a otra actividad)
                        startActivity(Intent(this@Perfil_instructor, Notificaciones::class.java))
                    },
                colorFilter = ColorFilter.tint(Color.White)

            )
        }
    }


    @Composable
    fun InstructorInfo() {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .background(Color(0xFFE0E0E0), shape = MaterialTheme.shapes.medium)
                .border(
                    width = 2.dp, // Grosor del borde
                    color = Color.Black, // Color del borde negro
                    shape = RoundedCornerShape(2.dp) // Esquinas redondeadas de 2 dp
                )
                .padding(16.dp)
        ) {
            // Icono del instructor
            Image(
                painter = painterResource(id = R.drawable.mujer),
                contentDescription = "Instructor Icon",
                modifier = Modifier
                    .size(100.dp)
                    .align(Alignment.CenterHorizontally)
            )
            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = "INSTRUCTOR",
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
            Spacer(modifier = Modifier.height(20.dp))

            // Sección de información básica
            Text(text = "Datos básicos", fontWeight = FontWeight.Bold, fontSize = 18.sp)
            Spacer(modifier = Modifier.height(10.dp))
            InfoItem(label = "Nombres:", value = "Mariani")
            InfoItem(label = "Apellidos:", value = "Dorado")
            InfoItem(label = "Teléfono", value = "3132545658")
            InfoItem(label = "Correo electrónico:", value = "marianodorado@gmail.com")
            InfoItem(label = "Fecha De Nacimiento:", value = "")

            Text(
                text = "Información de Seguimiento",
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp
            )
            Spacer(modifier = Modifier.height(10.dp))
            InfoItem(label = "Número de horas seguimiento:", value = "")
            InfoItem(label = "Mes:", value = "")
            InfoItem(label = "Número De Aprendices Asignados:", value = "")

            Spacer(modifier = Modifier.height(20.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                // Botón Actualizar
                Button(
                    onClick = { /* Acción de Actualizar */ },
                    modifier = Modifier.padding(4.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF009e00), // Fondo verde
                        contentColor = Color.White          // Texto blanco
                    )
                ) {
                    Text(text = "Actualizar")
                }

                // Espacio entre los botones
                Spacer(modifier = Modifier.width(8.dp))

                // Botón Cancelar
                Button(
                    onClick = { /* Acción de Cancelar */ },
                    modifier = Modifier.padding(4.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Gray, // Fondo gris
                        contentColor = Color.White  // Texto blanco
                    )
                ) {
                    Text(text = "Cancelar")
                }
            }
        }
    }

    @Composable
    fun InfoItem(label: String, value: String) {
        Column(modifier = Modifier.padding(vertical = 4.dp)) {
            Text(text = label, fontWeight = FontWeight.Bold)
            Text(
                text = value,
                fontSize = 16.sp,
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        Color.White,
                        shape = RoundedCornerShape(8.dp)
                    ) // Fondo blanco redondeado
                    .border(
                        1.dp,
                        Color.Gray,
                        shape = RoundedCornerShape(8.dp)
                    ) // Borde gris redondeado
                    .padding(8.dp)
            )
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    @Preview(showBackground = true)
    @Composable
    fun DefaultPreview() {
        MainScreen()
    }
}


