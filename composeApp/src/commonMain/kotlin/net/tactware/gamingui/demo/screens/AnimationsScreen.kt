package net.tactware.gamingui.demo.screens

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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import net.tactware.gamingui.components.animations.SciFiLoadingSpinner
import net.tactware.gamingui.components.animations.SciFiProgressBar
import net.tactware.gamingui.components.animations.TypingText
import net.tactware.gamingui.components.animations.gridBackground
import net.tactware.gamingui.components.animations.holographicParticles
import net.tactware.gamingui.components.animations.pulsingGlow
import net.tactware.gamingui.components.animations.rotatingGlow
import net.tactware.gamingui.components.animations.scanLineEffect
import net.tactware.gamingui.components.theme.SciFiColors
import net.tactware.gamingui.components.theme.SciFiTypography
import net.tactware.gamingui.components.ui.buttons.SciFiButton
import net.tactware.gamingui.components.ui.containers.SciFiCard
import net.tactware.gamingui.components.ui.containers.SciFiPanel
import net.tactware.gamingui.components.ui.inputs.SciFiSlider

/**
 * Screen showcasing the SciFi UI animation and effect components.
 */
@Composable
fun AnimationsScreen(
    onNavigateUp: () -> Unit
) {
    ComponentShowcaseScaffold(
        title = "Animations & Effects",
        onNavigateUp = onNavigateUp
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            // Pulsing Glow Effect
            SciFiPanel(
                title = "Pulsing Glow Effect",
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "Components with pulsing glow effects",
                    style = SciFiTypography.bodyMedium,
                    color = SciFiColors.onSurface,
                    modifier = Modifier.padding(bottom = 16.dp)
                )
                
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(100.dp)
                        .pulsingGlow(
                            glowColor = SciFiColors.primaryGlow,
                            minAlpha = 0.2f,
                            maxAlpha = 0.8f,
                            pulseDuration = 2000
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "Pulsing Glow Effect",
                        style = SciFiTypography.titleMedium,
                        color = SciFiColors.onSurface
                    )
                }
            }
            
            // Scan Line Effect
            SciFiPanel(
                title = "Scan Line Effect",
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "Components with scanning line effects",
                    style = SciFiTypography.bodyMedium,
                    color = SciFiColors.onSurface,
                    modifier = Modifier.padding(bottom = 16.dp)
                )
                
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(100.dp)
                        .scanLineEffect(
                            scanLineColor = SciFiColors.primaryGlow,
                            scanLineAlpha = 0.3f,
                            scanLineWidth = 2f,
                            scanDuration = 3000
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "Scan Line Effect",
                        style = SciFiTypography.titleMedium,
                        color = SciFiColors.onSurface
                    )
                }
            }
            
            // Holographic Particles
            SciFiPanel(
                title = "Holographic Particles",
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "Components with holographic particle effects",
                    style = SciFiTypography.bodyMedium,
                    color = SciFiColors.onSurface,
                    modifier = Modifier.padding(bottom = 16.dp)
                )
                
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(150.dp)
                        .holographicParticles(
                            particleColor = SciFiColors.primaryGlow,
                            particleCount = 50,
                            particleAlpha = 0.3f,
                            particleSize = 2f,
                            animationDuration = 10000
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "Holographic Particles",
                        style = SciFiTypography.titleMedium,
                        color = SciFiColors.onSurface
                    )
                }
            }
            
            // Grid Background
            SciFiPanel(
                title = "Grid Background",
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "Components with grid background effects",
                    style = SciFiTypography.bodyMedium,
                    color = SciFiColors.onSurface,
                    modifier = Modifier.padding(bottom = 16.dp)
                )
                
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(150.dp)
                        .gridBackground(
                            gridColor = SciFiColors.primaryGlow,
                            gridAlpha = 0.2f,
                            gridSpacing = 20f,
                            pulseEffect = true,
                            pulseDuration = 5000
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "Grid Background",
                        style = SciFiTypography.titleMedium,
                        color = SciFiColors.onSurface
                    )
                }
            }
            
            // Rotating Glow
            SciFiPanel(
                title = "Rotating Glow",
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "Components with rotating glow effects",
                    style = SciFiTypography.bodyMedium,
                    color = SciFiColors.onSurface,
                    modifier = Modifier.padding(bottom = 16.dp)
                )
                
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(150.dp)
                        .rotatingGlow(
                            glowColor = SciFiColors.primaryGlow,
                            glowAlpha = 0.3f,
                            rotationDuration = 8000
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "Rotating Glow",
                        style = SciFiTypography.titleMedium,
                        color = SciFiColors.onSurface
                    )
                }
            }
            
            // Typing Text
            SciFiPanel(
                title = "Typing Text Animation",
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "Text with typing animation effect",
                    style = SciFiTypography.bodyMedium,
                    color = SciFiColors.onSurface,
                    modifier = Modifier.padding(bottom = 16.dp)
                )
                
                var typingComplete by remember { mutableStateOf(false) }
                var restartTyping by remember { mutableStateOf(false) }
                
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(100.dp),
                    contentAlignment = Alignment.Center
                ) {
                    if (restartTyping) {
                        typingComplete = false
                        restartTyping = false
                    }
                    
                    TypingText(
                        text = "Welcome to the SciFi UI Component Library. This text is being typed out character by character.",
                        typingSpeed = 20f,
                        onTypingComplete = { typingComplete = true },
                        textStyle = SciFiTypography.bodyLarge,
                        color = SciFiColors.primary
                    )
                }
                
                Spacer(modifier = Modifier.height(16.dp))
                
                SciFiButton(
                    onClick = { restartTyping = true },
                    text = "Restart Typing Animation",
                    enabled = typingComplete,
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )
            }
            
            // Loading Spinner
            SciFiPanel(
                title = "Loading Spinner",
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "Sci-fi themed loading spinner",
                    style = SciFiTypography.bodyMedium,
                    color = SciFiColors.onSurface,
                    modifier = Modifier.padding(bottom = 16.dp)
                )
                
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // Small spinner
                    SciFiLoadingSpinner(
                        color = SciFiColors.primary,
                        size = 32f,
                        strokeWidth = 2f,
                        rotationDuration = 2000
                    )
                    
                    // Medium spinner
                    SciFiLoadingSpinner(
                        color = SciFiColors.primaryGlow,
                        size = 48f,
                        strokeWidth = 2.5f,
                        rotationDuration = 2000
                    )
                    
                    // Large spinner
                    SciFiLoadingSpinner(
                        color = SciFiColors.secondary,
                        size = 64f,
                        strokeWidth = 3f,
                        rotationDuration = 2000
                    )
                }
            }
            
            // Progress Bar
            SciFiPanel(
                title = "Progress Bar",
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "Sci-fi themed progress bar with adjustable value",
                    style = SciFiTypography.bodyMedium,
                    color = SciFiColors.onSurface,
                    modifier = Modifier.padding(bottom = 16.dp)
                )
                
                var progressValue by remember { mutableFloatStateOf(0.5f) }
                
                Column(modifier = Modifier.fillMaxWidth()) {
                    Text(
                        text = "Progress: ${(progressValue * 100).toInt()}%",
                        style = SciFiTypography.bodyMedium,
                        color = SciFiColors.onSurface,
                        modifier = Modifier.padding(bottom = 8.dp)
                    )
                    
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(16.dp)
                    ) {
                        SciFiProgressBar(
                            progress = progressValue,
                            backgroundColor = SciFiColors.backgroundMedium,
                            progressColor = SciFiColors.primary,
                            glowColor = SciFiColors.primaryGlow,
                            scanLineEffect = true
                        )
                    }
                    
                    Spacer(modifier = Modifier.height(16.dp))
                    
                    SciFiSlider(
                        value = progressValue,
                        onValueChange = { progressValue = it },
                        valueRange = 0f..1f,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }
            
            // Combined Effects Demo
            SciFiPanel(
                title = "Combined Effects Demo",
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "This demo combines multiple effects for a rich sci-fi experience",
                    style = SciFiTypography.bodyMedium,
                    color = SciFiColors.onSurface,
                    modifier = Modifier.padding(bottom = 16.dp)
                )
                
                SciFiCard(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                        .gridBackground(
                            gridColor = SciFiColors.primaryGlow,
                            gridAlpha = 0.1f,
                            gridSpacing = 20f
                        )
                        .holographicParticles(
                            particleColor = SciFiColors.primaryGlow,
                            particleCount = 30,
                            particleAlpha = 0.2f
                        )
                        .scanLineEffect(
                            scanLineColor = SciFiColors.primaryGlow,
                            scanLineAlpha = 0.1f
                        )
                ) {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                text = "SYSTEM ONLINE",
                                style = SciFiTypography.titleLarge,
                                color = SciFiColors.primary,
                                modifier = Modifier.padding(bottom = 16.dp)
                            )
                            
                            SciFiLoadingSpinner(
                                color = SciFiColors.primaryGlow,
                                size = 48f,
                                strokeWidth = 2f,
                                rotationDuration = 2000
                            )
                            
                            Spacer(modifier = Modifier.height(16.dp))
                            
                            Box(
                                modifier = Modifier
                                    .width(200.dp)
                                    .height(16.dp)
                            ) {
                                SciFiProgressBar(
                                    progress = 0.75f,
                                    backgroundColor = SciFiColors.backgroundMedium,
                                    progressColor = SciFiColors.primary,
                                    glowColor = SciFiColors.primaryGlow,
                                    scanLineEffect = true
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}
