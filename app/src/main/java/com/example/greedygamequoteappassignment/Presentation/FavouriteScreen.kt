package com.example.greedygamequoteappassignment.Presentation

import QuotesCard
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.greedygamequoteappassignment.viewModel.QuoteViewModel
import gradientList

// Enhanced gradient colors
val gradientListFav = listOf(
    listOf(Color(0xFFFF6B9D), Color(0xFFC44569)),
    listOf(Color(0xFF845EC2), Color(0xFF2D3561)),
    listOf(Color(0xFF4B7BEC), Color(0xFF3867D6)),
    listOf(Color(0xFF0FB9B1), Color(0xFF009688)),
    listOf(Color(0xFFFEA47F), Color(0xFFF97F51)),
    listOf(Color(0xFFFA7DC2), Color(0xFFEE5A6F)),
    listOf(Color(0xFF4FACFE), Color(0xFF00F2FE)),
    listOf(Color(0xFFB721FF), Color(0xFF21D4FD))
)

@Composable
fun FavouriteScreen(
    viewModel: QuoteViewModel,
    navController: NavController,
    color: List<Color> = gradientListFav.flatten()
) {
    val favoriteQuotes = viewModel.getFavoriteQuotes()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFFFAFBFC),
                        Color(0xFFF0F2F5)
                    )
                )
            )
    ) {
        // Background decorative elements
        Canvas(modifier = Modifier.fillMaxSize()) {
            drawCircle(
                brush = Brush.radialGradient(
                    colors = listOf(
                        Color(0xFFFF6B9D).copy(alpha = 0.08f),
                        Color.Transparent
                    )
                ),
                radius = 350f,
                center = Offset(size.width * 0.85f, size.height * 0.15f)
            )
            drawCircle(
                brush = Brush.radialGradient(
                    colors = listOf(
                        Color(0xFF4B7BEC).copy(alpha = 0.08f),
                        Color.Transparent
                    )
                ),
                radius = 400f,
                center = Offset(size.width * 0.15f, size.height * 0.75f)
            )
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp)
        ) {
            // Beautiful header with icon
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 24.dp, top = 8.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column {
                    Text(
                        "My Favorites",
                        style = MaterialTheme.typography.headlineMedium.copy(
                            fontWeight = FontWeight.Bold,
                            fontSize = 32.sp,
                            color = Color(0xFF2C3E50)
                        )
                    )

                    Text(
                        "${favoriteQuotes.size} saved quotes",
                        style = MaterialTheme.typography.bodyMedium.copy(
                            color = Color(0xFF7F8C8D),
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Medium
                        ),
                        modifier = Modifier.padding(top = 4.dp)
                    )
                }

                // Decorative heart icon
                Box(
                    modifier = Modifier
                        .size(64.dp)
                        .clip(CircleShape)
                        .background(
                            Brush.linearGradient(
                                colors = listOf(
                                    Color(0xFFFF6B9D),
                                    Color(0xFFFF4757)
                                )
                            )
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.Favorite,
                        contentDescription = "Favorites",
                        tint = Color.White,
                        modifier = Modifier.size(32.dp)
                    )
                }
            }

            AnimatedVisibility(
                visible = favoriteQuotes.isEmpty(),
                enter = fadeIn() + scaleIn(),
                exit = fadeOut() + scaleOut()
            ) {
                // Beautiful empty state
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        // Large heart outline
                        Box(
                            modifier = Modifier
                                .size(120.dp)
                                .clip(CircleShape)
                                .background(Color.White)
                                .padding(32.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = Icons.Default.FavoriteBorder,
                                contentDescription = null,
                                tint = Color(0xFFBDC3C7),
                                modifier = Modifier.size(64.dp)
                            )
                        }

                        Spacer(modifier = Modifier.height(24.dp))

                        Text(
                            "No favorites yet",
                            style = MaterialTheme.typography.titleLarge.copy(
                                fontWeight = FontWeight.Bold,
                                fontSize = 24.sp,
                                color = Color(0xFF2C3E50)
                            )
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        Text(
                            "Start saving quotes you love\nby tapping the heart icon",
                            style = MaterialTheme.typography.bodyMedium.copy(
                                color = Color(0xFF95A5A6),
                                fontSize = 16.sp,
                                textAlign = TextAlign.Center,
                                lineHeight = 24.sp
                            )
                        )
                    }
                }
            }

            AnimatedVisibility(
                visible = favoriteQuotes.isNotEmpty(),
                enter = fadeIn(),
                exit = fadeOut()
            ) {
                LazyColumn(
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    contentPadding = PaddingValues(bottom = 16.dp)
                ) {
                    items(favoriteQuotes.size) { index ->
                        val quote = favoriteQuotes[index]
                        val gradientPair = gradientListFav.random()
                        val topColor = gradientPair[0]
                        val bottomColor = gradientPair[1]

                        // Add subtle animation to each card
                        androidx.compose.animation.AnimatedVisibility(
                            visible = true,
                            enter = fadeIn() + scaleIn(),
                        ) {
                            QuotesCard(
                                quoteModel = quote,
                                backgroundColors = listOf(topColor, bottomColor),
                                onLongPress = {
                                    viewModel.toggleFavorite(quote.id)
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}