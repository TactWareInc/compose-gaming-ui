package net.tactware.gamingui.components.ui.inputs.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.interaction.collectIsHoveredAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import net.tactware.gamingui.components.theme.SciFiColors
import net.tactware.gamingui.components.theme.SciFiTypography
import net.tactware.gamingui.components.ui.inputs.SciFiTextField

/**
 * A sci-fi themed search field with glowing borders and an optional search icon.
 *
 * @param value The input text to be shown in the search field
 * @param onValueChange The callback that is triggered when the input service updates the text
 * @param onSearch The callback that is triggered when the search action is performed
 * @param modifier The modifier to be applied to the search field
 * @param enabled Controls the enabled state of the search field
 * @param placeholder The optional placeholder to be displayed when the search field is empty
 * @param searchIcon The optional icon to be displayed for the search action
 * @param backgroundColor The background color of the search field
 * @param textColor The color of the input text
 * @param placeholderColor The color of the placeholder text
 * @param borderColor The color of the border
 * @param glowColor The color of the glow effect
 * @param baseGlowIntensity The base intensity of the glow effect (0.0-1.0)
 */
@Composable
fun SciFiSearchField(
    value: String,
    onValueChange: (String) -> Unit,
    onSearch: (String) -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    placeholder: String = "Search...",
    searchIcon: ImageVector? = null,
    backgroundColor: Color = SciFiColors.backgroundMedium,
    textColor: Color = SciFiColors.onSurface,
    placeholderColor: Color = SciFiColors.onSurfaceMedium,
    borderColor: Color = SciFiColors.border,
    glowColor: Color = SciFiColors.primaryGlow,
    baseGlowIntensity: Float = 0.5f
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        SciFiTextField(
            value = value,
            onValueChange = onValueChange,
            modifier = Modifier.weight(1f),
            enabled = enabled,
            placeholder = placeholder,
            singleLine = true,
            keyboardActions = androidx.compose.foundation.text.KeyboardActions(
                onSearch = { onSearch(value) }
            ),
            keyboardOptions = androidx.compose.foundation.text.KeyboardOptions(
                imeAction = androidx.compose.ui.text.input.ImeAction.Search
            ),
            backgroundColor = backgroundColor,
            textColor = textColor,
            placeholderColor = placeholderColor,
            borderColor = borderColor,
            glowColor = glowColor,
            baseGlowIntensity = baseGlowIntensity
        )
        
        if (searchIcon != null) {
            Spacer(modifier = Modifier.width(8.dp))
            IconButton(
                onClick = { onSearch(value) },
                modifier = Modifier.height(36.dp)
            ) {
                androidx.compose.material3.Icon(
                    imageVector = searchIcon,
                    contentDescription = "Search",
                    tint = if (enabled) glowColor else glowColor.copy(alpha = 0.5f)
                )
            }
        }
    }
}
