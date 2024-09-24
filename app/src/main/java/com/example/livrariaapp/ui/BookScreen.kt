package com.example.livrariaapp.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BookScreen(viewModel: BookViewModel) {
    var title by remember { mutableStateOf("") }
    var author by remember { mutableStateOf("") }
    var genre by remember { mutableStateOf("") }
    var pub_year by remember { mutableStateOf("") }
    var publisher by remember { mutableStateOf("") }
    var selectedBookId by remember { mutableStateOf<Int?>(null) }

    val bookList by viewModel.bookList.collectAsState(initial = emptyList())

    val isFormValid = title.isNotBlank() && pub_year.isNotBlank() && author.isNotBlank() && genre.isNotBlank() && publisher.isNotBlank()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(Color(0xFF3700B3), Color(0xFF03DAC5)),
                )
            )
            .padding(4.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(32.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(
                text = "Cadastro de Livros",
                style = TextStyle(
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    fontSize = 30.sp,
                    textAlign = TextAlign.Center
                ),
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )

            // Card para o formulário
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .shadow(8.dp, RoundedCornerShape(8.dp))
                    .border(2.dp, Color.White, RoundedCornerShape(8.dp)),
                colors = CardDefaults.cardColors(containerColor = Color(0xFFF5F5F5))
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    TextField(
                        value = title,
                        onValueChange = { title = it },
                        label = { Text("Nome", color = Color.Black) },
                        modifier = Modifier
                            .fillMaxWidth()
                            .border(1.dp, Color.Gray, RoundedCornerShape(8.dp)),
                        colors = TextFieldDefaults.colors(
                            focusedTextColor = Color.Black,
                            unfocusedTextColor = Color.Black,
                            focusedContainerColor = Color.White.copy(alpha = 0.8f),
                            unfocusedContainerColor = Color.White.copy(alpha = 0.8f),
                        )
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    TextField(
                        value = author,
                        onValueChange = { author = it },
                        label = { Text("Autor", color = Color.Black) },
                        modifier = Modifier
                            .fillMaxWidth()
                            .border(1.dp, Color.Gray, RoundedCornerShape(8.dp)),
                        colors = TextFieldDefaults.colors(
                            focusedTextColor = Color.Black,
                            unfocusedTextColor = Color.Black,
                            focusedContainerColor = Color.White.copy(alpha = 0.8f),
                            unfocusedContainerColor = Color.White.copy(alpha = 0.8f),
                        )
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    TextField(
                        value = genre,
                        onValueChange = { genre = it },
                        label = { Text("Genero", color = Color.Black) },
                        modifier = Modifier
                            .fillMaxWidth()
                            .border(1.dp, Color.Gray, RoundedCornerShape(8.dp)),
                        colors = TextFieldDefaults.colors(
                            focusedTextColor = Color.Black,
                            unfocusedTextColor = Color.Black,
                            focusedContainerColor = Color.White.copy(alpha = 0.8f),
                            unfocusedContainerColor = Color.White.copy(alpha = 0.8f),
                        )
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    TextField(
                        value = pub_year,
                        onValueChange = { pub_year = it },
                        label = { Text("Ano de Publicação", color = Color.Black) },
                        modifier = Modifier
                            .fillMaxWidth()
                            .border(1.dp, Color.Gray, RoundedCornerShape(8.dp)),
                        keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                        colors = TextFieldDefaults.colors(
                            focusedTextColor = Color.Black,
                            unfocusedTextColor = Color.Black,
                            focusedContainerColor = Color.White.copy(alpha = 0.8f),
                            unfocusedContainerColor = Color.White.copy(alpha = 0.8f),
                        )
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    TextField(
                        value = publisher,
                        onValueChange = { publisher = it },
                        label = { Text("Editora", color = Color.Black) },
                        modifier = Modifier
                            .fillMaxWidth()
                            .border(1.dp, Color.Gray, RoundedCornerShape(8.dp)),
                        colors = TextFieldDefaults.colors(
                            focusedTextColor = Color.Black,
                            unfocusedTextColor = Color.Black,
                            focusedContainerColor = Color.White.copy(alpha = 0.8f),
                            unfocusedContainerColor = Color.White.copy(alpha = 0.8f),
                        )
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    Button(
                        onClick = {
                            if (isFormValid) {
                                viewModel.addOrUpdateBook(
                                    selectedBookId,
                                    title,
                                    pub_year.toIntOrNull() ?: 1,
                                    author,
                                    genre,
                                    publisher
                                )
                                title = ""
                                pub_year = ""
                                author = ""
                                genre = ""
                                publisher = ""
                                selectedBookId = null
                            }
                        },
                        modifier = Modifier
                            .padding(top = 16.dp)
                            .align(Alignment.CenterHorizontally),
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF03DAC5), disabledContainerColor = Color.LightGray),
                        enabled = isFormValid,
                        shape = RoundedCornerShape(16.dp)
                    ) {
                        Text(if (selectedBookId == null) "Adicionar Livro" else "Atualizar Livro", color = Color.White, fontWeight = FontWeight.Bold, fontSize = 18.sp)
                    }
                }
            }

            LazyColumn(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(bookList) { book ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(5.dp)
                            .shadow(4.dp, RoundedCornerShape(8.dp)),
                        colors = CardDefaults.cardColors(containerColor = Color(0xFFBBDEFB)),
                        shape = RoundedCornerShape(8.dp),
                    ) {
                        Row(
                            modifier = Modifier.padding(16.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Column(modifier = Modifier.weight(1f)) {
                                Text(text = "Nome: ${book.title}", color = Color.Black, fontWeight = FontWeight.Bold)
                                Text(text = "Autor: ${book.author}", color = Color.DarkGray)
                                Text(text = "Gênero: ${book.genre}", color = Color.DarkGray)
                                Text(text = "Ano: ${book.pub_year}", color = Color.DarkGray)
                                Text(text = "Editora: ${book.publisher}", color = Color.DarkGray)
                            }

                            Row {
                                IconButton(onClick = {
                                    title = book.title
                                    pub_year = book.pub_year.toString()
                                    author = book.author
                                    genre = book.genre
                                    publisher = book.publisher
                                    selectedBookId = book.id
                                }) {
                                    Icon(
                                        imageVector = Icons.Outlined.Edit,
                                        modifier = Modifier.size(32.dp),
                                        contentDescription = "Editar Livro",
                                        tint = Color.Blue
                                    )
                                }
                                IconButton(onClick = { viewModel.deleteBook(book) }) {
                                    Icon(
                                        imageVector = Icons.Outlined.Delete,
                                        modifier = Modifier.size(32.dp),
                                        contentDescription = "Excluir Livro",
                                        tint = Color.Red
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
