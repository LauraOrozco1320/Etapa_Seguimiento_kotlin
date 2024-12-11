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
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.time.LocalDate

class Bitacora : ComponentActivity() {
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
            EtapaSeguimientoScreen()
            RegistroScreen()
        }
    }

    @Composable
    fun HeaderSection() {
        // Estado para manejar el menú desplegable
        var expanded by remember { mutableStateOf(false) }

        // Contenido del menú desplegable
        val options = listOf("Opción 1", "Opción 2", "Opción 3")
        val context = LocalContext.current // Obtener el contexto

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
                .background(Color(0xFF009e00)), // Verde
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
                    startActivity(Intent(this@Bitacora, Notificaciones::class.java)) },
                colorFilter = ColorFilter.tint(Color.White)

            )
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    @Composable
    fun EtapaSeguimientoScreen() {
        var nombreAprendiz by remember { mutableStateOf("Marian Diaz") }
        var ficha by remember { mutableStateOf("2654013") }
        var identificacion by remember { mutableStateOf("10604335627") }
        var correo by remember { mutableStateOf("mariandiaz@gmail.com") }
        var selectedNumbers by remember { mutableStateOf(mutableSetOf<Int>()) }
        var fecha by remember { mutableStateOf(LocalDate.now().toString()) }
        var modalidad by remember { mutableStateOf("Pasantía") }

        // Título "Bitacora"
        Text(
            text = "BITACORA",
            style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold), // Negrilla añadida
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp),
            textAlign = TextAlign.Center
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp),

            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text("Nombre Completo Del Aprendiz", fontWeight = FontWeight.Bold, fontSize = 16.sp)
            Spacer(Modifier.height(4.dp))
            Text(
                nombreAprendiz,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth() // Asegura que el fondo blanco se extienda completamente
                    .background(Color.White, RoundedCornerShape(8.dp)) // Fondo blanco redondeado
                    .border(1.dp, Color.Gray, RoundedCornerShape(8.dp)) // Borde gris redondeado
                    .padding(8.dp)
            )

            Spacer(Modifier.height(8.dp))

            Row(
                Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column {
                    Text("N° Ficha", fontWeight = FontWeight.Bold)
                    Spacer(Modifier.height(6.dp))
                    Text(
                        ficha,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .background(Color.White, RoundedCornerShape(8.dp)) // Fondo blanco redondeado
                            .border(1.dp, Color.Gray, RoundedCornerShape(8.dp)) // Borde gris redondeado
                            .padding(8.dp)
                    )
                }
                Column {
                    Text("N° Identificación", fontWeight = FontWeight.Bold)
                    Spacer(Modifier.height(6.dp))
                    Text(
                        identificacion,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .background(Color.White, RoundedCornerShape(8.dp)) // Fondo blanco redondeado
                            .border(1.dp, Color.Gray, RoundedCornerShape(8.dp)) // Borde gris redondeado
                            .padding(8.dp)
                    )
                }
            }

            Spacer(Modifier.height(8.dp))

            Text("Correo electrónico", textAlign = TextAlign.Center, fontWeight = FontWeight.Bold)
            Spacer(Modifier.height(4.dp))
            Text(
                correo,
                textAlign = TextAlign.Center, // Centrar el texto del correo
                modifier = Modifier
                    .fillMaxWidth() // Asegura que el cuadro del texto ocupe todo el ancho disponible
                    .background(Color.White, RoundedCornerShape(8.dp)) // Fondo blanco redondeado
                    .border(1.dp, Color.Gray, RoundedCornerShape(8.dp)) // Borde gris redondeado
                    .padding(8.dp)
            )

            Spacer(Modifier.height(16.dp))

            //bitacora

            // Color seleccionado
            val selectedColor = Color(0xFF009e00) // Color cuando está seleccionado

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White, RoundedCornerShape(8.dp)) // Fondo blanco con bordes redondeados
                    .border(2.dp, Color.Gray, RoundedCornerShape(8.dp)) // Borde gris con bordes redondeados
                    .padding(16.dp) // Padding dentro del cuadro
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally, // Centra los textos horizontalmente
                    verticalArrangement = Arrangement.spacedBy(8.dp) // Espacio entre los textos
                ) {
                    // Números del 1 al 12 centrados
                    for (i in 1..12) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(48.dp) // Asegura que el Box tenga suficiente altura
                                .background(
                                    if (selectedNumbers.contains(i)) selectedColor else Color.White, // Fondo verde si está seleccionado, blanco si no lo está
                                    RoundedCornerShape(8.dp)
                                )
                                .border(1.dp, Color.Gray, RoundedCornerShape(8.dp)) // Borde gris
                                .clickable {
                                    // Si el número ya está seleccionado, lo deseleccionamos, si no, lo agregamos al conjunto
                                    if (selectedNumbers.contains(i)) {
                                        selectedNumbers =
                                            (selectedNumbers - i) as MutableSet<Int> // Deseleccionar
                                    } else {
                                        selectedNumbers =
                                            (selectedNumbers + i) as MutableSet<Int> // Seleccionar
                                    }
                                }
                                .padding(8.dp) // Padding interno del cuadro
                        ) {
                            Text(
                                text = i.toString(), // Mostrar el número en texto
                                fontWeight = FontWeight.Bold, // Texto en negrita
                                color = if (selectedNumbers.contains(i)) Color.White else Color.Black, // Texto blanco si está seleccionado
                                modifier = Modifier
                                    .fillMaxSize() // Asegura que el texto ocupe todo el espacio disponible
                                    .wrapContentWidth(Alignment.CenterHorizontally) // Centra el texto horizontalmente
                                    .wrapContentHeight(Alignment.CenterVertically) // Centra el texto verticalmente
                            )
                        }
                    }
                }
            }
//fin bitacora

            Spacer(Modifier.height(30.dp))

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .border(2.dp, Color.Black, RoundedCornerShape(8.dp)) // Borde negro
                    .padding(12.dp) // Padding dentro del cuadro
                    .background(Color.White) // Color de fondo del cuadro
            ) {
                Column {
                    // Tipo De Modalidad De Etapa Productiva
                    Text("Tipo De Modalidad De Etapa Productiva", textAlign = TextAlign.Center, fontWeight = FontWeight.Bold)
                    Spacer(Modifier.height(4.dp))
                    Text(
                        modalidad,
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(Color.White, RoundedCornerShape(8.dp)) // Fondo blanco redondeado
                            .border(1.dp, Color.Gray, RoundedCornerShape(8.dp)) // Borde gris redondeado
                            .padding(8.dp),
                        textAlign = TextAlign.Center // Centrar texto
                    )

                    Spacer(Modifier.height(16.dp))

                    // Fecha
                    Text("Fecha", fontWeight = FontWeight.Bold, textAlign = TextAlign.Center)
                    Spacer(Modifier.height(4.dp))
                    BasicTextField(
                        value = fecha,
                        onValueChange = { fecha = it },
                        modifier = Modifier
                            .background(Color.White, RoundedCornerShape(8.dp)) // Fondo blanco redondeado
                            .border(1.dp, Color.Gray, RoundedCornerShape(8.dp)) // Borde negro redondeado
                            .padding(8.dp)
                            .fillMaxWidth(),
                        textStyle = TextStyle(textAlign = TextAlign.Center) // Centrar texto de entrada
                    )

                    Spacer(Modifier.height(16.dp))

                }
            }
        }
    }
    @Composable
    fun RegistroScreen() {
        var mensajeRegistro by remember { mutableStateOf("") }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .background(Color.White)
        ) {
            // Botón de registrar
            Button(
                onClick = { mensajeRegistro = "Bitácora Registrada" }, // Acción al registrar
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF009e00)),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(35.dp)
            ) {
                Text("REGISTRAR", color = Color.White, fontWeight = FontWeight.Bold)
            }

            // Mostrar el mensaje si no está vacío
            if (mensajeRegistro.isNotEmpty()) {
                Text(
                    text = mensajeRegistro,
                    color = Color(0xFF009e00),
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp, // Aumentar tamaño de texto
                    modifier = Modifier
                        .padding(top = 20.dp)
                        .fillMaxWidth() // Centrar el texto en todo el ancho disponible
                        .wrapContentWidth(Alignment.CenterHorizontally) // Centrar el texto
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




