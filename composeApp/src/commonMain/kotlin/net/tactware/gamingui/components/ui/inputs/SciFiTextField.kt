package net.tactware.gamingui.components.ui.inputs

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.interaction.collectIsHoveredAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import net.tactware.gamingui.components.theme.SciFiColors
import net.tactware.gamingui.components.theme.SciFiShapes
import net.tactware.gamingui.components.theme.SciFiTypography
import net.tactware.gamingui.components.theme.drawSciFiBorder

/**
 * A sci-fi themed text field with glowing borders and hover/focus effects.
 *
 * @param value The input text to be shown in the text field
 * @param onValueChange The callback that is triggered when the input service updates the text
 * @param modifier The modifier to be applied to the text field
 * @param enabled Controls the enabled state of the text field
 * @param readOnly Controls the editable state of the text field
 * @param label The optional label to be displayed above the text field
 * @param placeholder The optional placeholder to be displayed when the text field is empty
 * @param visualTransformation The visual transformation of the input (e.g., for password fields)
 * @param keyboardOptions The keyboard options to configure the keyboard for this text field
 * @param keyboardActions The keyboard actions to configure the IME behavior
 * @param singleLine Whether the input text should be constrained to a single line
 * @param maxLines The maximum number of visible lines
 * @param textStyle The style to be applied to the input text
 * @param backgroundColor The background color of the text field
 * @param textColor The color of the input text
 * @param labelColor The color of the label text
 * @param placeholderColor The color of the placeholder text
 * @param borderColor The color of the border
 * @param glowColor The color of the glow effect
 * @param baseGlowIntensity The base intensity of the glow effect (0.0-1.0)
 */
@Composable
fun SciFiTextField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    readOnly: Boolean = false,
    label: String? = null,
    placeholder: String? = null,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    singleLine: Boolean = false,
    maxLines: Int = if (singleLine) 1 else Int.MAX_VALUE,
    textStyle: TextStyle = SciFiTypography.bodyLarge,
    backgroundColor: Color = SciFiColors.backgroundMedium,
    textColor: Color = SciFiColors.onSurface,
    labelColor: Color = SciFiColors.primary,
    placeholderColor: Color = SciFiColors.onSurfaceMedium,
    borderColor: Color = SciFiColors.border,
    glowColor: Color = SciFiColors.primaryGlow,
    baseGlowIntensity: Float = 0.5f
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isFocused by interactionSource.collectIsFocusedAsState()
    val isHovered by interactionSource.collectIsHoveredAsState()
    
    // Increase glow intensity when focused or hovered
    val glowIntensity by animateFloatAsState(
        targetValue = when {
            !enabled -> 0.2f
            isFocused -> 1.0f
            isHovered -> 0.7f
            else -> baseGlowIntensity
        },
        label = "glowIntensity"
    )
    
    // Adjust colors based on state
    val currentBorderColor = when {
        !enabled -> borderColor.copy(alpha = 0.5f)
        isFocused -> glowColor
        else -> borderColor
    }
    
    val currentTextColor = when {
        !enabled -> textColor.copy(alpha = 0.5f)
        else -> textColor
    }
    
    Column(modifier = modifier) {
        // Optional label
        if (label != null) {
            Text(
                text = label,
                style = SciFiTypography.labelMedium,
                color = if (enabled) labelColor else labelColor.copy(alpha = 0.5f),
                modifier = Modifier.padding(bottom = 4.dp)
            )
        }
        
        // Text field with sci-fi styling
        BasicTextField(
            value = value,
            onValueChange = onValueChange,
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    color = if (enabled) backgroundColor else backgroundColor.copy(alpha = 0.7f),
                    shape = SciFiShapes.textFieldShape
                )
                .drawWithContent {
                    drawContent()
                    if (enabled) {
                        // Draw sci-fi border with glow effect
                        drawSciFiBorder(
                            borderColor = currentBorderColor.copy(alpha = glowIntensity),
                            borderWidth = 1.5f,
                            notchSize = 4f,
                            segmentLength = if (isFocused) 30f else 20f,
                            gapLength = if (isFocused) 2f else 4f
                        )
                    }
                }
                .padding(horizontal = 12.dp, vertical = 10.dp)
                .onFocusChanged { /* Needed for focus state */ },
            enabled = enabled,
            readOnly = readOnly,
            textStyle = textStyle.copy(color = currentTextColor),
            keyboardOptions = keyboardOptions,
            keyboardActions = keyboardActions,
            singleLine = singleLine,
            maxLines = maxLines,
            visualTransformation = visualTransformation,
            interactionSource = interactionSource,
            cursorBrush = SolidColor(glowColor),
            decorationBox = { innerTextField ->
                Box {
                    // Show placeholder if text is empty
                    if (value.isEmpty() && placeholder != null) {
                        Text(
                            text = placeholder,
                            style = textStyle,
                            color = placeholderColor.copy(alpha = 0.7f)
                        )
                    }
                    innerTextField()
                }
            }
        )
    }
}

/**
 * A sci-fi themed password field with masked input and glowing borders.
 *
 * @param value The input text to be shown in the text field
 * @param onValueChange The callback that is triggered when the input service updates the text
 * @param modifier The modifier to be applied to the text field
 * @param enabled Controls the enabled state of the text field
 * @param label The optional label to be displayed above the text field
 * @param placeholder The optional placeholder to be displayed when the text field is empty
 * @param keyboardOptions The keyboard options to configure the keyboard for this text field
 * @param keyboardActions The keyboard actions to configure the IME behavior
 * @param backgroundColor The background color of the text field
 * @param textColor The color of the input text
 * @param labelColor The color of the label text
 * @param placeholderColor The color of the placeholder text
 * @param borderColor The color of the border
 * @param glowColor The color of the glow effect
 * @param baseGlowIntensity The base intensity of the glow effect (0.0-1.0)
 */
@Composable
fun SciFiPasswordField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    label: String? = null,
    placeholder: String? = null,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    backgroundColor: Color = SciFiColors.backgroundMedium,
    textColor: Color = SciFiColors.onSurface,
    labelColor: Color = SciFiColors.primary,
    placeholderColor: Color = SciFiColors.onSurfaceMedium,
    borderColor: Color = SciFiColors.border,
    glowColor: Color = SciFiColors.primaryGlow,
    baseGlowIntensity: Float = 0.5f
) {
    // Use password visual transformation
    val passwordVisualTransformation = androidx.compose.ui.text.input.PasswordVisualTransformation()
    
    SciFiTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier,
        enabled = enabled,
        label = label,
        placeholder = placeholder,
        visualTransformation = passwordVisualTransformation,
        keyboardOptions = keyboardOptions.copy(
            keyboardType = androidx.compose.ui.text.input.KeyboardType.Password
        ),
        keyboardActions = keyboardActions,
        singleLine = true,
        backgroundColor = backgroundColor,
        textColor = textColor,
        labelColor = labelColor,
        placeholderColor = placeholderColor,
        borderColor = borderColor,
        glowColor = glowColor,
        baseGlowIntensity = baseGlowIntensity
    )
}

/**
 * A sci-fi themed number input field with glowing borders.
 *
 * @param value The input number as a string
 * @param onValueChange The callback that is triggered when the input service updates the text
 * @param modifier The modifier to be applied to the text field
 * @param enabled Controls the enabled state of the text field
 * @param label The optional label to be displayed above the text field
 * @param placeholder The optional placeholder to be displayed when the text field is empty
 * @param keyboardActions The keyboard actions to configure the IME behavior
 * @param backgroundColor The background color of the text field
 * @param textColor The color of the input text
 * @param labelColor The color of the label text
 * @param placeholderColor The color of the placeholder text
 * @param borderColor The color of the border
 * @param glowColor The color of the glow effect
 * @param baseGlowIntensity The base intensity of the glow effect (0.0-1.0)
 */
@Composable
fun SciFiNumberField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    label: String? = null,
    placeholder: String? = null,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    backgroundColor: Color = SciFiColors.backgroundMedium,
    textColor: Color = SciFiColors.onSurface,
    labelColor: Color = SciFiColors.primary,
    placeholderColor: Color = SciFiColors.onSurfaceMedium,
    borderColor: Color = SciFiColors.border,
    glowColor: Color = SciFiColors.primaryGlow,
    baseGlowIntensity: Float = 0.5f
) {
    // Only allow numeric input
    val numericFilter: (String) -> String = { input ->
        input.filter { it.isDigit() || it == '.' || it == '-' }
    }
    
    SciFiTextField(
        value = value,
        onValueChange = { onValueChange(numericFilter(it)) },
        modifier = modifier,
        enabled = enabled,
        label = label,
        placeholder = placeholder,
        keyboardOptions = KeyboardOptions(keyboardType = androidx.compose.ui.text.input.KeyboardType.Number),
        keyboardActions = keyboardActions,
        singleLine = true,
        textStyle = LocalTextStyle.current.copy(fontFamily = androidx.compose.ui.text.font.FontFamily.Monospace),
        backgroundColor = backgroundColor,
        textColor = textColor,
        labelColor = labelColor,
        placeholderColor = placeholderColor,
        borderColor = borderColor,
        glowColor = glowColor,
        baseGlowIntensity = baseGlowIntensity
    )
}

/**
 * A sci-fi themed multiline text area with glowing borders.
 *
 * @param value The input text to be shown in the text area
 * @param onValueChange The callback that is triggered when the input service updates the text
 * @param modifier The modifier to be applied to the text area
 * @param enabled Controls the enabled state of the text area
 * @param readOnly Controls the editable state of the text area
 * @param label The optional label to be displayed above the text area
 * @param placeholder The optional placeholder to be displayed when the text area is empty
 * @param minLines The minimum number of lines to display
 * @param maxLines The maximum number of visible lines
 * @param textStyle The style to be applied to the input text
 * @param backgroundColor The background color of the text area
 * @param textColor The color of the input text
 * @param labelColor The color of the label text
 * @param placeholderColor The color of the placeholder text
 * @param borderColor The color of the border
 * @param glowColor The color of the glow effect
 * @param baseGlowIntensity The base intensity of the glow effect (0.0-1.0)
 */
@Composable
fun SciFiTextArea(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    readOnly: Boolean = false,
    label: String? = null,
    placeholder: String? = null,
    minLines: Int = 3,
    maxLines: Int = 6,
    textStyle: TextStyle = SciFiTypography.bodyMedium,
    backgroundColor: Color = SciFiColors.backgroundMedium,
    textColor: Color = SciFiColors.onSurface,
    labelColor: Color = SciFiColors.primary,
    placeholderColor: Color = SciFiColors.onSurfaceMedium,
    borderColor: Color = SciFiColors.border,
    glowColor: Color = SciFiColors.primaryGlow,
    baseGlowIntensity: Float = 0.5f
) {
    SciFiTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier,
        enabled = enabled,
        readOnly = readOnly,
        label = label,
        placeholder = placeholder,
        singleLine = false,
        maxLines = maxLines,
        textStyle = textStyle,
        backgroundColor = backgroundColor,
        textColor = textColor,
        labelColor = labelColor,
        placeholderColor = placeholderColor,
        borderColor = borderColor,
        glowColor = glowColor,
        baseGlowIntensity = baseGlowIntensity
    )
}
