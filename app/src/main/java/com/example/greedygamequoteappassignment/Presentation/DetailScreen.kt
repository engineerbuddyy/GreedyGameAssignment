package com.example.greedygamequoteappassignment.Presentation

import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.FormatQuote
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil3.compose.AsyncImage
import com.example.greedygamequoteappassignment.data.QuoteModel
import com.example.greedygamequoteappassignment.viewModel.QuoteViewModel

val gradientList = listOf(
    listOf(Color(0xFFFF6B9D), Color(0xFFC44569)), // Pink-Red
    listOf(Color(0xFF845EC2), Color(0xFF2D3561)), // Purple-Navy
    listOf(Color(0xFF4B7BEC), Color(0xFF3867D6)), // Blue
    listOf(Color(0xFF0FB9B1), Color(0xFF009688)), // Teal
    listOf(Color(0xFFFEA47F), Color(0xFFF97F51)), // Orange
    listOf(Color(0xFFFA7DC2), Color(0xFFEE5A6F)), // Pink
    listOf(Color(0xFF4FACFE), Color(0xFF00F2FE)), // Sky Blue
    listOf(Color(0xFFB721FF), Color(0xFF21D4FD))  // Purple-Cyan
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(
    navController: NavController,
    quote: QuoteModel,
    viewModel: QuoteViewModel
) {
    val favoriteIds by viewModel.favoriteIds.collectAsState()
    val isFavorite by remember { derivedStateOf { quote.id in favoriteIds } }
    val scrollState = rememberScrollState()


    val gradientPair = remember { gradientList.random() }
    val topColor = gradientPair[0]
    val bottomColor = gradientPair[1]

    // Animation for floating effect
    val infiniteTransition = rememberInfiniteTransition(label = "float")
    val floatingOffset by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 20f,
        animationSpec = infiniteRepeatable(
            animation = tween(3000, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "floating"
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFFF5F7FA),
                        Color(0xFFE8EAF0)
                    )
                )
            )
    ) {
        // Background decorative elements
        Canvas(modifier = Modifier.fillMaxSize()) {
            drawCircle(
                brush = Brush.radialGradient(
                    colors = listOf(
                        topColor.copy(alpha = 0.1f),
                        Color.Transparent
                    )
                ),
                radius = 400f,
                center = Offset(size.width * 0.2f, size.height * 0.15f)
            )
            drawCircle(
                brush = Brush.radialGradient(
                    colors = listOf(
                        bottomColor.copy(alpha = 0.08f),
                        Color.Transparent
                    )
                ),
                radius = 500f,
                center = Offset(size.width * 0.8f, size.height * 0.7f)
            )
        }

        Column(modifier = Modifier.fillMaxSize()) {
            // Custom Top Bar with glass morphism effect
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        Brush.horizontalGradient(
                            colors = listOf(topColor, bottomColor)
                        )
                    )
                    .statusBarsPadding()
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 8.dp, vertical = 12.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconButton(
                        onClick = { navController.popBackStack() },
                        modifier = Modifier
                            .size(44.dp)
                            .background(
                                Color.White.copy(alpha = 0.2f),
                                CircleShape
                            )
                    ) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "Back",
                            tint = Color.White,
                            modifier = Modifier.size(24.dp)
                        )
                    }

                    Text(
                        text = "Quote Details",
                        style = MaterialTheme.typography.titleLarge.copy(
                            color = Color.White,
                            fontWeight = FontWeight.Bold,
                            fontSize = 22.sp
                        )
                    )

                    IconButton(
                        onClick = { viewModel.toggleFavorite(quote.id) },
                        modifier = Modifier
                            .size(44.dp)
                            .background(
                                Color.White.copy(alpha = 0.2f),
                                CircleShape
                            )
                    ) {
                        Icon(
                            imageVector = if (isFavorite) Icons.Filled.Favorite else Icons.Filled.FavoriteBorder,
                            contentDescription = "Favorite",
                            tint = if (isFavorite) Color(0xFFFF4444) else Color.White,
                            modifier = Modifier.size(24.dp)
                        )
                    }
                }
            }

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(scrollState)
                    .padding(20.dp)
            ) {
                Spacer(modifier = Modifier.height(8.dp))

                // Enhanced Image Card with floating shadow
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(280.dp)
                        .offset(y = (floatingOffset / 4).dp),
                    shape = RoundedCornerShape(28.dp),
                    elevation = CardDefaults.cardElevation(
                        defaultElevation = 16.dp
                    )
                ) {
                    Box {
                        AsyncImage(
                            model = quote.downloadUrl,
                            contentDescription = "Quote Image",
                            modifier = Modifier.fillMaxSize(),
                            contentScale = ContentScale.Crop
                        )


                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .background(
                                    Brush.verticalGradient(
                                        colors = listOf(
                                            Color.Transparent,
                                            Color.Black.copy(alpha = 0.4f)
                                        ),
                                        startY = 0f,
                                        endY = Float.POSITIVE_INFINITY
                                    )
                                )
                        )
                    }
                }

                Spacer(modifier = Modifier.height(32.dp))


                Card(
                    modifier = Modifier
                        .fillMaxWidth(),
                    shape = RoundedCornerShape(32.dp),
                    elevation = CardDefaults.cardElevation(
                        defaultElevation = 20.dp
                    )
                ) {
                    Box {
                        // Animated gradient background
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .background(
                                    brush = Brush.linearGradient(
                                        colors = listOf(
                                            topColor,
                                            bottomColor,
                                            topColor.copy(alpha = 0.8f)
                                        ),
                                        start = Offset(0f, 0f),
                                        end = Offset(1500f, 1500f)
                                    )
                                )
                        )

                        // Decorative geometric shapes
                        Canvas(modifier = Modifier.fillMaxSize()) {
                            // Large decorative circle
                            drawCircle(
                                color = Color.White.copy(alpha = 0.08f),
                                radius = 180f,
                                center = Offset(size.width * 0.15f, size.height * 0.25f)
                            )

                            // Smaller accent circle
                            drawCircle(
                                color = Color.White.copy(alpha = 0.12f),
                                radius = 100f,
                                center = Offset(size.width * 0.88f, size.height * 0.75f)
                            )

                            // Additional small circles for depth
                            drawCircle(
                                color = Color.White.copy(alpha = 0.06f),
                                radius = 60f,
                                center = Offset(size.width * 0.85f, size.height * 0.15f)
                            )

                            // Subtle lines for elegance
                            rotate(45f, Offset(size.width * 0.5f, size.height * 0.5f)) {
                                drawLine(
                                    color = Color.White.copy(alpha = 0.05f),
                                    start = Offset(0f, size.height * 0.3f),
                                    end = Offset(size.width, size.height * 0.3f),
                                    strokeWidth = 2f
                                )
                            }
                        }

                        Column(
                            modifier = Modifier
                                .padding(32.dp)
                        ) {
                            // Opening quote mark with glow effect
                            Box(
                                modifier = Modifier
                                    .size(52.dp)
                                    .background(
                                        Color.White.copy(alpha = 0.15f),
                                        CircleShape
                                    ),
                                contentAlignment = Alignment.Center
                            ) {
                                Icon(
                                    imageVector = Icons.Default.FormatQuote,
                                    contentDescription = null,
                                    tint = Color.White.copy(alpha = 0.9f),
                                    modifier = Modifier.size(28.dp)
                                )
                            }

                            Spacer(modifier = Modifier.height(24.dp))

                            // Quote text with enhanced typography
                            Text(
                                text = quote.text,
                                style = MaterialTheme.typography.bodyLarge.copy(
                                    color = Color.White,
                                    lineHeight = 32.sp,
                                    fontSize = 20.sp,
                                    fontWeight = FontWeight.Normal,
                                    letterSpacing = 0.3.sp
                                )
                            )

                            Spacer(modifier = Modifier.height(28.dp))

                            // Elegant divider
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(1.dp)
                                    .background(
                                        Brush.horizontalGradient(
                                            colors = listOf(
                                                Color.Transparent,
                                                Color.White.copy(alpha = 0.4f),
                                                Color.Transparent
                                            )
                                        )
                                    )
                            )

                            Spacer(modifier = Modifier.height(24.dp))

                            // Author attribution with icon
                            Row(
                                modifier = Modifier.align(Alignment.End),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Box(
                                    modifier = Modifier
                                        .width(50.dp)
                                        .height(2.dp)
                                        .background(
                                            Color.White.copy(alpha = 0.5f),
                                            RoundedCornerShape(1.dp)
                                        )
                                )

                                Spacer(modifier = Modifier.width(16.dp))

                                Column(horizontalAlignment = Alignment.End) {
                                    Text(
                                        text = quote.author,
                                        style = MaterialTheme.typography.bodyLarge.copy(
                                            color = Color.White,
                                            fontStyle = androidx.compose.ui.text.font.FontStyle.Italic,
                                            fontWeight = FontWeight.SemiBold,
                                            fontSize = 18.sp,
                                            letterSpacing = 0.5.sp
                                        )
                                    )
                                }
                            }
                        }
                    }
                }

                Spacer(modifier = Modifier.height(32.dp))
            }
        }
    }
}