package net.tactware.gamingui.demo.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import net.tactware.gamingui.components.theme.SciFiColors
import net.tactware.gamingui.components.theme.SciFiTypography
import net.tactware.gamingui.components.ui.buttons.SciFiButton
import net.tactware.gamingui.components.ui.buttons.SciFiSuccessButton
import net.tactware.gamingui.components.ui.buttons.SciFiWarningButton
import net.tactware.gamingui.components.ui.containers.InfoBoxType
import net.tactware.gamingui.components.ui.containers.SciFiCard
import net.tactware.gamingui.components.ui.containers.SciFiDialog
import net.tactware.gamingui.components.ui.containers.SciFiHolographicContainer
import net.tactware.gamingui.components.ui.containers.SciFiInfoBox
import net.tactware.gamingui.components.ui.containers.SciFiPanel

/**
 * Screen showcasing the SciFi UI card and panel components.
 */
@Composable
fun CardsScreen(
    onNavigateUp: () -> Unit
) {
    ComponentShowcaseScaffold(
        title = "Cards & Panels",
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
            // Standard Cards
            SciFiPanel(
                title = "Cards",
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "Standard cards with different content",
                    style = SciFiTypography.bodyMedium,
                    color = SciFiColors.onSurface,
                    modifier = Modifier.padding(bottom = 16.dp)
                )
                
                // Basic Card
                SciFiCard(
                    modifier = Modifier.fillMaxWidth(),
                    contentPadding = PaddingValues(16.dp)
                ) {
                    Text(
                        text = "Basic Card",
                        style = SciFiTypography.titleMedium,
                        color = SciFiColors.primary,
                        modifier = Modifier.padding(bottom = 8.dp)
                    )
                    
                    Text(
                        text = "This is a basic card component with a simple content layout. Cards can contain various types of content and are useful for grouping related information.",
                        style = SciFiTypography.bodyMedium,
                        color = SciFiColors.onSurface
                    )
                }
                
                Spacer(modifier = Modifier.height(16.dp))
                
                // Card with actions
                SciFiCard(
                    modifier = Modifier.fillMaxWidth(),
                    contentPadding = PaddingValues(16.dp)
                ) {
                    Text(
                        text = "Card with Actions",
                        style = SciFiTypography.titleMedium,
                        color = SciFiColors.primary,
                        modifier = Modifier.padding(bottom = 8.dp)
                    )
                    
                    Text(
                        text = "Cards can also contain interactive elements like buttons for actions related to the card content.",
                        style = SciFiTypography.bodyMedium,
                        color = SciFiColors.onSurface,
                        modifier = Modifier.padding(bottom = 16.dp)
                    )
                    
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.End
                    ) {
                        SciFiButton(
                            onClick = { /* Do something */ },
                            text = "Action",
                        )
                    }
                }
            }
            
            // Panels
            SciFiPanel(
                title = "Panels",
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "Panels with headers for sectioning content",
                    style = SciFiTypography.bodyMedium,
                    color = SciFiColors.onSurface,
                    modifier = Modifier.padding(bottom = 16.dp)
                )
                
                // Nested panel example
                SciFiPanel(
                    title = "Nested Panel",
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = "Panels can be nested to create hierarchical content structures. This is useful for organizing complex interfaces.",
                        style = SciFiTypography.bodyMedium,
                        color = SciFiColors.onSurface,
                        modifier = Modifier.padding(bottom = 16.dp)
                    )
                    
                    SciFiPanel(
                        title = "Sub-Section",
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(
                            text = "This is a nested panel that represents a sub-section of content.",
                            style = SciFiTypography.bodyMedium,
                            color = SciFiColors.onSurface
                        )
                    }
                }
            }
            
            // Dialog
            SciFiPanel(
                title = "Dialogs",
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "Dialog components for important messages",
                    style = SciFiTypography.bodyMedium,
                    color = SciFiColors.onSurface,
                    modifier = Modifier.padding(bottom = 16.dp)
                )
                
                // Dialog example (static representation)
                SciFiDialog(
                    title = "Confirmation Dialog",
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = "Are you sure you want to proceed with this action?",
                        style = SciFiTypography.bodyLarge,
                        color = SciFiColors.onSurface,
                        modifier = Modifier.padding(bottom = 16.dp)
                    )
                    
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.End
                    ) {
                        SciFiWarningButton(
                            onClick = { /* Cancel action */ },
                            text = "Cancel",

                        )
                        
                        Spacer(modifier = Modifier.padding(horizontal = 8.dp))

                        SciFiSuccessButton(
                            onClick = { /* Confirm action */ },
                            text = "Confirm",

                        )
                    }
                }
            }
            
            // Holographic Container
            SciFiPanel(
                title = "Holographic Container",
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "Container with holographic effect",
                    style = SciFiTypography.bodyMedium,
                    color = SciFiColors.onSurface,
                    modifier = Modifier.padding(bottom = 16.dp)
                )
                
                SciFiHolographicContainer(
                    modifier = Modifier.fillMaxWidth(),
                    contentPadding = PaddingValues(16.dp)
                ) {
                    Text(
                        text = "Holographic Display",
                        style = SciFiTypography.titleMedium,
                        color = SciFiColors.primary,
                        modifier = Modifier.padding(bottom = 8.dp)
                    )
                    
                    Text(
                        text = "This container has a holographic effect that gives it a futuristic, sci-fi appearance. It's perfect for displaying important or featured content.",
                        style = SciFiTypography.bodyMedium,
                        color = SciFiColors.onSurface
                    )
                }
            }
            
            // Info Boxes
            SciFiPanel(
                title = "Info Boxes",
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "Info boxes for different types of messages",
                    style = SciFiTypography.bodyMedium,
                    color = SciFiColors.onSurface,
                    modifier = Modifier.padding(bottom = 16.dp)
                )
                
                // Info type
                SciFiInfoBox(
                    message = "This is an informational message providing details about a feature.",
                    type = InfoBoxType.Info,
                    modifier = Modifier.fillMaxWidth()
                )
                
                Spacer(modifier = Modifier.height(8.dp))
                
                // Success type
                SciFiInfoBox(
                    message = "Operation completed successfully! Your changes have been saved.",
                    type = InfoBoxType.Success,
                    modifier = Modifier.fillMaxWidth()
                )
                
                Spacer(modifier = Modifier.height(8.dp))
                
                // Warning type
                SciFiInfoBox(
                    message = "Warning: This action cannot be undone. Please proceed with caution.",
                    type = InfoBoxType.Warning,
                    modifier = Modifier.fillMaxWidth()
                )
                
                Spacer(modifier = Modifier.height(8.dp))
                
                // Error type
                SciFiInfoBox(
                    message = "Error: Unable to connect to the server. Please check your connection and try again.",
                    type = InfoBoxType.Error,
                    modifier = Modifier.fillMaxWidth()
                )
            }
            
            // Interactive Card Demo
            SciFiPanel(
                title = "Interactive Card Demo",
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "Interactive card with state changes",
                    style = SciFiTypography.bodyMedium,
                    color = SciFiColors.onSurface,
                    modifier = Modifier.padding(bottom = 16.dp)
                )
                
                var expanded by remember { mutableStateOf(false) }
                
                SciFiCard(
                    modifier = Modifier.fillMaxWidth(),
                    contentPadding = PaddingValues(16.dp)
                ) {
                    Text(
                        text = "Expandable Content Card",
                        style = SciFiTypography.titleMedium,
                        color = SciFiColors.primary,
                        modifier = Modifier.padding(bottom = 8.dp)
                    )
                    
                    Text(
                        text = "Click the button below to toggle additional content.",
                        style = SciFiTypography.bodyMedium,
                        color = SciFiColors.onSurface,
                        modifier = Modifier.padding(bottom = 16.dp)
                    )
                    
                    if (expanded) {
                        Text(
                            text = "This is additional content that appears when the card is expanded. It can contain any type of information that might be initially hidden to save space.",
                            style = SciFiTypography.bodyMedium,
                            color = SciFiColors.onSurface,
                            modifier = Modifier.padding(bottom = 16.dp)
                        )
                        
                        SciFiInfoBox(
                            message = "You can include other components inside the expanded content.",
                            type = InfoBoxType.Info,
                            modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp)
                        )
                    }
                    
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        SciFiButton(
                            onClick = { expanded = !expanded },
                            text = if (expanded) "Show Less" else "Show More",
                        )
                    }
                }
            }
        }
    }
}
