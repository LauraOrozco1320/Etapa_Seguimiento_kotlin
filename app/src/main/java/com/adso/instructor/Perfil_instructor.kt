package com.adso.instructor

import android.content.Context
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
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
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
import androidx.compose.ui.platform.LocalContext
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

            UserIconMenu()

        }
    }
    @Composable
    fun UserIconMenu() {
        var expanded by remember { mutableStateOf(false) }
        val context = LocalContext.current

        // Datos de usuario (reemplazar por datos reales si es necesario)
        val userName = "Laura Orozco" // Nombre del usuario
        val userRole = "Instructor" // Rol del usuario

        Box(modifier = Modifier.wrapContentSize(Alignment.TopEnd)) {
            Image(
                painter = painterResource(id = R.drawable.mujer),
                contentDescription = "User Icon",
                modifier = Modifier
                    .size(45.dp)
                    .clickable { expanded = true } // Abre el menú al hacer clic
            )

            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false } // Cierra el menú al hacer clic fuera
            ) {
                // Añadir nombre y rol en la parte superior del menú
                Column(
                    modifier = Modifier.padding(16.dp) // Espaciado en la cabecera del menú
                ) {
                    Text(text = userName, style = MaterialTheme.typography.titleMedium)
                    Text(text = userRole, style = MaterialTheme.typography.bodyMedium)
                }

                // Elementos del menú
                DropdownMenuItem(
                    text = { Text("Ver perfil") },
                    onClick = {
                        expanded = false
                        context.startActivity(Intent(context, Perfil_instructor::class.java))
                    }
                )
                DropdownMenuItem(
                    text = { Text("Aprendices") },
                    onClick = {
                        expanded = false
                        context.startActivity(Intent(context, Lista_Aprendiz::class.java))
                    }
                )
                DropdownMenuItem(
                    text = { Text("Calendario") },
                    onClick = {
                        expanded = false
                        context.startActivity(Intent(context, Calendar::class.java))
                    }
                )
                DropdownMenuItem(
                    text = { Text("Configuración") },
                    onClick = {
                        expanded = false
                        context.startActivity(Intent(context, Configuracion::class.java))
                    }
                )
                DropdownMenuItem(
                    text = { Text("Cerrar sesión") },
                    onClick = {
                        expanded = false
                        // Acción para cerrar sesión
                    }
                )
            }
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


    @RequiresApi(Build.VERSION_CODES.O)
    @Composable
    fun InstructorInfo() {
        var isEditing by remember { mutableStateOf(false) } // Estado de edición
        var nombres by remember { mutableStateOf("Mariani") }
        var apellidos by remember { mutableStateOf("Dorado") }
        var telefono by remember { mutableStateOf("3132545658") }
        var correo by remember { mutableStateOf("marianodorado@gmail.com") }
        var fechaNacimiento by remember { mutableStateOf("") }
        var horasSeguimiento by remember { mutableStateOf("") }
        var mes by remember { mutableStateOf("") }
        var aprendicesAsignados by remember { mutableStateOf("") }

        // Función de actualización
        fun actualizarDatos() {
            // Aquí se podría agregar la lógica para actualizar los datos, por ejemplo, guardar en un repositorio
            println("Datos Actualizados:")
            println("Nombres: $nombres")
            println("Apellidos: $apellidos")
            println("Teléfono: $telefono")
            println("Correo: $correo")
            println("Fecha de Nacimiento: $fechaNacimiento")
            println("Horas Seguimiento: $horasSeguimiento")
            println("Mes: $mes")
            println("Aprendices Asignados: $aprendicesAsignados")
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .background(Color(0xFFE0E0E0), shape = MaterialTheme.shapes.medium)
                .border(2.dp, Color.Black, shape = RoundedCornerShape(2.dp))
                .padding(16.dp)
        ) {
            // Imagen de Instructor
            Image(
                painter = painterResource(id = R.drawable.mujer), // Reemplaza con tu imagen
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

            Text(text = "Datos básicos", fontWeight = FontWeight.Bold, fontSize = 18.sp)
            Spacer(modifier = Modifier.height(10.dp))

            // Campos editables si isEditing es true
            InfoItem(label = "Nombres:", value = nombres, isEditing = isEditing) { nombres = it }
            InfoItem(label = "Apellidos:", value = apellidos, isEditing = isEditing) { apellidos = it }
            InfoItem(label = "Teléfono:", value = telefono, isEditing = isEditing) { telefono = it }
            InfoItem(label = "Correo electrónico:", value = correo, isEditing = isEditing) { correo = it }
            InfoItem(label = "Fecha De Nacimiento:", value = fechaNacimiento, isEditing = isEditing) { fechaNacimiento = it }

            Spacer(modifier = Modifier.height(20.dp))
            Text(
                text = "Información de Seguimiento",
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp
            )
            Spacer(modifier = Modifier.height(10.dp))

            // Campos de seguimiento
            InfoItem(label = "Número de horas seguimiento:", value = horasSeguimiento, isEditing = isEditing) { horasSeguimiento = it }
            InfoItem(label = "Mes:", value = mes, isEditing = isEditing) { mes = it }
            InfoItem(label = "Número De Aprendices Asignados:", value = aprendicesAsignados, isEditing = isEditing) { aprendicesAsignados = it }

            Spacer(modifier = Modifier.height(8.dp)) // Espacio antes del botón
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Button(
                    onClick = {
                        if (isEditing) {
                            // Guardar cambios al hacer clic en "Guardar"
                            actualizarDatos()
                        }
                        isEditing = !isEditing // Cambiar entre editar y ver
                    },
                    modifier = Modifier.padding(8.dp),
                    colors = ButtonDefaults.run { val buttonColors =
                        buttonColors(Color(0xFF009E00))
                        buttonColors
                    }
                ) {
                    Text(text = if (isEditing) "Guardar" else "Actualizar", color = Color.White)
                }
            }
        }
    }

    @Composable
    fun InfoItem(label: String, value: String, isEditing: Boolean, onValueChange: (String) -> Unit) {
        Column(modifier = Modifier.padding(vertical = 4.dp)) {
            Text(text = label, fontWeight = FontWeight.Bold)
            if (isEditing) {
                TextField(
                    value = value,
                    onValueChange = onValueChange,
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.White, shape = RoundedCornerShape(8.dp))
                        .border(1.dp, Color.Gray, shape = RoundedCornerShape(8.dp))
                        .padding(8.dp)
                )
            } else {
                Text(
                    text = value,
                    fontSize = 16.sp,
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.White, shape = RoundedCornerShape(8.dp))
                        .border(1.dp, Color.Gray, shape = RoundedCornerShape(8.dp))
                        .padding(8.dp)
                )
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    @Preview(showBackground = true)
    @Composable
    fun DefaultPreview() {
        MainScreen()
    }
}



