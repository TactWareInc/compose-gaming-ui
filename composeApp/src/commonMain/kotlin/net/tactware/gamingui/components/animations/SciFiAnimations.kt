package net.tactware.gamingui.components.animations

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.unit.dp
import net.tactware.gamingui.components.theme.SciFiColors
import kotlinx.coroutines.delay
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.sin
import kotlin.random.Random

/**
 * Adds a pulsing glow effect to a composable.
 *
 * @param glowColor The color of the glow effect
 * @param minAlpha The minimum alpha value during pulsing
 * @param maxAlpha The maximum alpha value during pulsing
 * @param pulseDuration The duration of one pulse cycle in milliseconds
 */
@Composable
fun Modifier.pulsingGlow(
    glowColor: Color = SciFiColors.primaryGlow,
    minAlpha: Float = 0.2f,
    maxAlpha: Float = 0.8f,
    pulseDuration: Int = 2000
): Modifier {
    val infiniteTransition = rememberInfiniteTransition(label = "pulsingGlow")
    val pulseProgress by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = pulseDuration, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        ),
        label = "pulseProgress"
    )
    
    return this.then(
        Modifier.drawWithContent {
            drawContent()
            
            // Calculate the current alpha based on pulse progress
            // Using a sine wave for smooth pulsing
            val progress = (sin(pulseProgress * PI * 2).toFloat() + 1f) / 2f
            val currentAlpha = minAlpha + (maxAlpha - minAlpha) * progress
            
            // Draw the glow overlay
            drawRect(
                color = glowColor.copy(alpha = currentAlpha),
                blendMode = BlendMode.SrcOver
            )
        }
    )
}

/**
 * Adds a scanning line effect to a composable.
 *
 * @param scanLineColor The color of the scan line
 * @param scanLineAlpha The opacity of the scan line
 * @param scanLineWidth The width of the scan line in dp
 * @param scanDuration The duration of one scan cycle in milliseconds
 */
@Composable
fun Modifier.scanLineEffect(
    scanLineColor: Color = SciFiColors.primaryGlow,
    scanLineAlpha: Float = 0.2f,
    scanLineWidth: Float = 2f,
    scanDuration: Int = 3000
): Modifier {
    val infiniteTransition = rememberInfiniteTransition(label = "scanLine")
    val scanProgress by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = scanDuration, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        ),
        label = "scanProgress"
    )
    
    return this.then(
        Modifier.drawWithContent {
            drawContent()
            
            // Calculate the position of the scan line
            val scanLineX = size.width * scanProgress
            
            // Draw the scan line
            drawRect(
                color = scanLineColor.copy(alpha = scanLineAlpha),
                topLeft = Offset(scanLineX - scanLineWidth / 2, 0f),
                size = androidx.compose.ui.geometry.Size(scanLineWidth, size.height)
            )
            
            // Draw a subtle glow around the scan line
            drawRect(
                brush = Brush.horizontalGradient(
                    colors = listOf(
                        Color.Transparent,
                        scanLineColor.copy(alpha = scanLineAlpha * 0.5f),
                        scanLineColor.copy(alpha = scanLineAlpha),
                        scanLineColor.copy(alpha = scanLineAlpha * 0.5f),
                        Color.Transparent
                    ),
                    startX = scanLineX - scanLineWidth * 4,
                    endX = scanLineX + scanLineWidth * 4
                ),
                topLeft = Offset(scanLineX - scanLineWidth * 4, 0f),
                size = androidx.compose.ui.geometry.Size(scanLineWidth * 8, size.height)
            )
        }
    )
}

/**
 * Adds a holographic particle effect to a composable.
 *
 * @param particleColor The color of the particles
 * @param particleCount The number of particles
 * @param particleAlpha The opacity of the particles
 * @param particleSize The size of the particles in dp
 * @param animationDuration The duration of the animation cycle in milliseconds
 */
@Composable
fun Modifier.holographicParticles(
    particleColor: Color = SciFiColors.primaryGlow,
    particleCount: Int = 50,
    particleAlpha: Float = 0.3f,
    particleSize: Float = 2f,
    animationDuration: Int = 10000
): Modifier {
    // Generate random particles
    val particles = remember {
        List(particleCount) {
            Particle(
                initialX = Random.nextFloat(),
                initialY = Random.nextFloat(),
                speed = Random.nextFloat() * 0.2f + 0.1f,
                angle = Random.nextFloat() * 2f * PI.toFloat(),
                size = Random.nextFloat() * particleSize + 1f
            )
        }
    }
    
    val infiniteTransition = rememberInfiniteTransition(label = "particles")
    val animationProgress by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = animationDuration, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        ),
        label = "particleProgress"
    )
    
    return this.then(
        Modifier.drawWithContent {
            drawContent()
            
            // Draw each particle
            particles.forEach { particle ->
                // Calculate current position based on animation progress
                val x = (particle.initialX + cos(particle.angle) * particle.speed * animationProgress) * size.width
                val y = (particle.initialY + sin(particle.angle) * particle.speed * animationProgress) * size.height
                
                // Ensure particle stays within bounds (wrap around)
                val wrappedX = ((x / size.width) % 1f) * size.width
                val wrappedY = ((y / size.height) % 1f) * size.height
                
                // Draw the particle
                drawCircle(
                    color = particleColor.copy(alpha = particleAlpha * (0.5f + Random.nextFloat() * 0.5f)),
                    radius = particle.size,
                    center = Offset(wrappedX, wrappedY)
                )
            }
        }
    )
}

/**
 * Adds a grid pattern background effect to a composable.
 *
 * @param gridColor The color of the grid lines
 * @param gridAlpha The opacity of the grid lines
 * @param gridSpacing The spacing between grid lines in dp
 * @param pulseEffect Whether to add a pulsing effect to the grid
 * @param pulseDuration The duration of one pulse cycle in milliseconds
 */
@Composable
fun Modifier.gridBackground(
    gridColor: Color = SciFiColors.primaryGlow,
    gridAlpha: Float = 0.1f,
    gridSpacing: Float = 20f,
    pulseEffect: Boolean = true,
    pulseDuration: Int = 5000
): Modifier {
    val infiniteTransition = rememberInfiniteTransition(label = "gridPulse")
    val pulseProgress by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = pulseDuration, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        ),
        label = "gridPulseProgress"
    )
    
    return this.then(
        Modifier.drawBehind {
            // Calculate current alpha based on pulse progress if pulse effect is enabled
            val currentAlpha = if (pulseEffect) {
                val progress = (sin(pulseProgress * PI * 2).toFloat() + 1f) / 2f
                gridAlpha * 0.5f + gridAlpha * 0.5f * progress
            } else {
                gridAlpha
            }
            
            val adjustedGridColor = gridColor.copy(alpha = currentAlpha)
            
            // Draw horizontal lines
            var y = 0f
            while (y < size.height) {
                drawLine(
                    color = adjustedGridColor,
                    start = Offset(0f, y),
                    end = Offset(size.width, y),
                    strokeWidth = 1f
                )
                y += gridSpacing
            }
            
            // Draw vertical lines
            var x = 0f
            while (x < size.width) {
                drawLine(
                    color = adjustedGridColor,
                    start = Offset(x, 0f),
                    end = Offset(x, size.height),
                    strokeWidth = 1f
                )
                x += gridSpacing
            }
        }
    )
}

/**
 * Adds a rotating glow effect to a composable.
 *
 * @param glowColor The color of the glow effect
 * @param glowAlpha The opacity of the glow effect
 * @param rotationDuration The duration of one rotation cycle in milliseconds
 */
@Composable
fun Modifier.rotatingGlow(
    glowColor: Color = SciFiColors.primaryGlow,
    glowAlpha: Float = 0.3f,
    rotationDuration: Int = 8000
): Modifier {
    val infiniteTransition = rememberInfiniteTransition(label = "rotatingGlow")
    val rotation by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = rotationDuration, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        ),
        label = "rotationProgress"
    )
    
    return this.then(
        Modifier.drawWithContent {
            drawContent()
            
            // Draw rotating glow effect
            rotate(rotation) {
                drawCircle(
                    brush = Brush.radialGradient(
                        colors = listOf(
                            glowColor.copy(alpha = glowAlpha),
                            glowColor.copy(alpha = glowAlpha * 0.7f),
                            glowColor.copy(alpha = glowAlpha * 0.3f),
                            glowColor.copy(alpha = 0f)
                        )
                    ),
                    radius = size.width * 0.8f
                )
            }
        }
    )
}

/**
 * Adds a typing animation effect to text.
 *
 * @param text The text to animate
 * @param typingSpeed The typing speed in characters per second
 * @param onTypingComplete Callback when typing animation is complete
 */
@Composable
fun TypingText(
    text: String,
    typingSpeed: Float = 20f, // characters per second
    onTypingComplete: () -> Unit = {},
    modifier: Modifier = Modifier,
    textStyle: androidx.compose.ui.text.TextStyle = androidx.compose.material3.MaterialTheme.typography.bodyMedium,
    color: Color = SciFiColors.onSurface
) {
    var displayedText by remember { mutableStateOf("") }
    var isComplete by remember { mutableStateOf(false) }
    
    LaunchedEffect(text) {
        displayedText = ""
        isComplete = false
        
        val delayPerChar = (1000 / typingSpeed).toLong()
        
        for (i in text.indices) {
            displayedText = text.substring(0, i + 1)
            delay(delayPerChar)
        }
        
        isComplete = true
        onTypingComplete()
    }
    
    androidx.compose.material3.Text(
        text = displayedText,
        style = textStyle,
        color = color,
        modifier = modifier
    )
}

/**
 * Creates a loading spinner with a sci-fi aesthetic.
 *
 * @param color The color of the spinner
 * @param size The size of the spinner in dp
 * @param strokeWidth The width of the spinner stroke in dp
 * @param rotationDuration The duration of one rotation cycle in milliseconds
 */
@Composable
fun SciFiLoadingSpinner(
    color: Color = SciFiColors.primaryGlow,
    size: Float = 48f,
    strokeWidth: Float = 2f,
    rotationDuration: Int = 2000,
    modifier: Modifier = Modifier
) {
    val infiniteTransition = rememberInfiniteTransition(label = "loadingSpinner")
    val rotation by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = rotationDuration, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        ),
        label = "spinnerRotation"
    )
    
    Box(
        modifier = modifier
            .size(size.dp)
            .drawWithContent {
                rotate(rotation) {
                    drawArc(
                        color = color,
                        startAngle = 0f,
                        sweepAngle = 270f,
                        useCenter = false,
                        style = androidx.compose.ui.graphics.drawscope.Stroke(width = strokeWidth)
                    )

                    fun toRadians(degrees: Double): Double {
                        return degrees * (PI / 180)
                    }

                    // Draw small notches
                    for (i in 0 until 12) {
                        val angle = i * 30f
                        val notchLength = if (i % 3 == 0) strokeWidth * 3 else strokeWidth * 2
                        val startRadius = size / 2 - notchLength
                        val endRadius = size / 2
                        
                        val startX = center.x + cos(toRadians(angle.toDouble())).toFloat() * startRadius
                        val startY = center.y + sin(toRadians(angle.toDouble())).toFloat() * startRadius
                        val endX = center.x + cos(toRadians(angle.toDouble())).toFloat() * endRadius
                        val endY = center.y + sin(toRadians(angle.toDouble())).toFloat() * endRadius
                        
                        drawLine(
                            color = color.copy(alpha = 0.7f),
                            start = Offset(startX, startY),
                            end = Offset(endX, endY),
                            strokeWidth = strokeWidth / 2
                        )
                    }
                }
            }
    )
}

/**
 * Creates a progress bar with a sci-fi aesthetic.
 *
 * @param progress The current progress value (0.0-1.0)
 * @param backgroundColor The background color of the progress bar
 * @param progressColor The color of the progress indicator
 * @param glowColor The color of the glow effect
 * @param height The height of the progress bar in dp
 * @param scanLineEffect Whether to add a scan line effect
 */
@Composable
fun SciFiProgressBar(
    progress: Float,
    backgroundColor: Color = SciFiColors.backgroundMedium,
    progressColor: Color = SciFiColors.primary,
    glowColor: Color = SciFiColors.primaryGlow,
    height: Float = 12f,
    scanLineEffect: Boolean = true,
    modifier: Modifier = Modifier
) {
    val infiniteTransition = rememberInfiniteTransition(label = "progressBar")
    val scanProgress by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 1500, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        ),
        label = "scanProgress"
    )
    
    Box(
        modifier = modifier
            .fillMaxSize()
            .height(height.dp)
            .background(
                color = backgroundColor,
                shape = androidx.compose.foundation.shape.RoundedCornerShape(2.dp)
            )
            .drawWithContent {
                drawContent()
                
                // Draw progress
                drawRect(
                    color = progressColor,
                    size = androidx.compose.ui.geometry.Size(size.width * progress, size.height)
                )
                
                // Draw scan line effect
                if (scanLineEffect) {
                    val maxScanX = size.width * progress
                    val scanLineX = maxScanX * scanProgress
                    
                    if (maxScanX > 0) {
                        drawRect(
                            color = glowColor.copy(alpha = 0.7f),
                            topLeft = Offset(scanLineX - 1f, 0f),
                            size = androidx.compose.ui.geometry.Size(2f, size.height)
                        )
                    }
                }
                
                // Draw glow effect at the edge of progress
                if (progress > 0 && progress < 1) {
                    drawRect(
                        brush = Brush.horizontalGradient(
                            colors = listOf(
                                Color.Transparent,
                                glowColor.copy(alpha = 0.7f)
                            ),
                            startX = size.width * progress - 10f,
                            endX = size.width * progress
                        ),
                        topLeft = Offset(size.width * progress - 10f, 0f),
                        size = androidx.compose.ui.geometry.Size(10f, size.height)
                    )
                }
            }
    )
}

/**
 * Helper class for particle animation.
 */
private data class Particle(
    val initialX: Float,
    val initialY: Float,
    val speed: Float,
    val angle: Float,
    val size: Float
)
