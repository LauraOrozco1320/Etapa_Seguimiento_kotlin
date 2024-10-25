package com.adso.instructor

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainScreen()
        }
    }

    @Composable
    fun MainScreen() {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            HeaderSection()
            NotificationBar()
            SearchBar()
            ContentGrid()
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
                            fontSize = 13.sp,
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
                        startActivity(Intent(this@MainActivity, Perfil_instructor::class.java))
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
                        startActivity(Intent(this@MainActivity, Notificaciones::class.java)) },
                colorFilter = ColorFilter.tint(Color.White)

            )
        }
    }


    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun SearchBar() {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Campo de búsqueda
            TextField(
                value = "",
                onValueChange = {},
                placeholder = { Text("Buscar...") },
                modifier = Modifier
                    .weight(1f)
                    .padding(end = 8.dp)
                    .background(Color(0xFFE0E0E0), shape = RoundedCornerShape(2.dp)), // Fondo gris con esquinas redondeadas
                shape = RoundedCornerShape(2.dp), // Asegúrate de que esto coincida con el fondo
                colors = TextFieldDefaults.textFieldColors(
                    focusedIndicatorColor = Color.Transparent, // Sin indicador al enfocar
                    unfocusedIndicatorColor = Color.Transparent // Sin indicador al no enfocar
                )
            )
        }
    }

    @Composable
    fun ContentGrid() {
        LazyVerticalGrid(
            columns = GridCells.Fixed(2), // Establece que sean dos columnas
            verticalArrangement = Arrangement.spacedBy(16.dp), // Espaciado entre filas
            horizontalArrangement = Arrangement.spacedBy(16.dp), // Espaciado entre columnas
            modifier = Modifier.padding(16.dp) // Margen general del grid
        ) {
            items(24) { index -> // Cambia el número de items según lo necesites
                CardItem(index)
            }
        }
    }

    @Composable
    fun CardItem(index: Int) {
        val context = LocalContext.current  // Obtiene el contexto actual

        // Botón que envuelve todo el contenido del CardItem
        Column(
            modifier = Modifier
                .padding(8.dp)
                .border(2.dp, Color(0xFF009E00), RoundedCornerShape(2.dp))
                .background(Color.White)
                .clickable {
                    Log.d("MainActivity", "Ver Perfil Aprendiz button clicked for index $index")
                    // Navega a la actividad Perfil_Aprendiz
                    context.startActivity(Intent(context, Perfil_Aprendiz::class.java))
                }
                .padding(16.dp) // Espaciado interno
                .fillMaxWidth(), // Asegúrate de que el Column use todo el ancho disponible
            horizontalAlignment = Alignment.CenterHorizontally // Alineación horizontal al centro
        ) {
            Image(
                painter = painterResource(id = R.drawable.aprendiz_icono_tra),
                contentDescription = "User",
                modifier = Modifier.size(48.dp)
            )
            Text("Nombre Completo", fontSize = 12.sp, textAlign = TextAlign.Center)
            Text("Cédula", fontSize = 12.sp, textAlign = TextAlign.Center)
            Text("Ficha", fontSize = 12.sp, textAlign = TextAlign.Center)
            Text("Tipo de seguimiento", fontSize = 12.sp, textAlign = TextAlign.Center)
        }
    }

    @Preview(showBackground = true)
    @Composable
    fun DefaultPreview() {
        MainScreen()
    }
}