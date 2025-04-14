package net.tactware.gamingui.components.ui.buttons

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.hoverable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsHoveredAsState
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.addOutline
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import net.tactware.gamingui.components.theme.SciFiColors
import net.tactware.gamingui.components.theme.SciFiShapes
import net.tactware.gamingui.components.theme.SciFiTypography
import net.tactware.gamingui.components.theme.drawSciFiBorder
import net.tactware.gamingui.components.theme.sciFiGlowingBorder

/**
 * A sci-fi themed button with glowing borders and hover effects.
 *
 * @param onClick The callback to be invoked when the button is clicked
 * @param modifier The modifier to be applied to the button
 * @param enabled Controls the enabled state of the button
 * @param text The text to be displayed on the button
 * @param contentPadding The padding values to be applied to the content of the button
 * @param buttonColor The background color of the button
 * @param contentColor The color of the content (text) of the button
 * @param glowColor The color of the glow effect around the button
 * @param shape The shape of the button
 * @param baseGlowIntensity The base intensity of the glow effect (0.0-1.0)
 */
@Composable
fun SciFiButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    text: String,
    contentPadding: PaddingValues = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
    buttonColor: Color = SciFiColors.primary,
    contentColor: Color = SciFiColors.onPrimary,
    glowColor: Color = SciFiColors.primaryGlow,
    shape: Shape = SciFiShapes.sciFiButtonShape,
    baseGlowIntensity: Float = 0.7f
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()
    val isHovered by interactionSource.collectIsHoveredAsState()

    // Increase glow intensity when hovered or pressed
    val glowIntensity by animateFloatAsState(
        targetValue = when {
            !enabled -> 0.3f
            isPressed -> 1.0f
            isHovered -> 0.85f
            else -> baseGlowIntensity
        },
        label = "glowIntensity"
    )

    // Adjust colors based on state
    val backgroundColor = when {
        !enabled -> buttonColor.copy(alpha = 0.5f)
        isPressed -> buttonColor.copy(alpha = 0.8f)
        else -> buttonColor
    }

    val textColor = when {
        !enabled -> contentColor.copy(alpha = 0.5f)
        else -> contentColor
    }

    // Apply sci-fi styling with glowing border
    val buttonModifier = modifier
        .defaultMinSize(minWidth = 88.dp, minHeight = 36.dp)
        .then(
            Modifier.drawWithContent {
                drawContent()
                if (enabled) {
                    // Draw additional glow effect when button is interactive
                    drawSciFiBorder(
                        borderColor = glowColor.copy(alpha = glowIntensity),
                        borderWidth = 2f,
                        notchSize = 5f,
                        segmentLength = 20f,
                        gapLength = 3f
                    )
                }
            }
        )

    Button(
        onClick = onClick,
        modifier = buttonModifier,
        enabled = enabled,
        shape = shape,
        colors = ButtonDefaults.buttonColors(
            containerColor = backgroundColor,
            contentColor = textColor,
            disabledContainerColor = backgroundColor,
            disabledContentColor = textColor
        ),
        contentPadding = contentPadding,
        interactionSource = interactionSource
    ) {
        Text(
            text = text,
            style = SciFiTypography.buttonText,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
fun SciFiButton2(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    buttonColor: Color = SciFiColors.primary,
    borderColor: Color = SciFiColors.secondary,
    contentColor: Color = SciFiColors.onPrimary,
    glowColor: Color = SciFiColors.primaryGlow,
    content: @Composable RowScope.() -> Unit
) {
    val shape = CutCornerShape(8.dp)
    val interactionSource = remember { MutableInteractionSource() }
    val isHovered by interactionSource.collectIsHoveredAsState()
    Box(contentAlignment = Alignment.Center) {
        Surface(
            modifier = modifier
                .clip(shape)
                .border(BorderStroke(2.dp, borderColor), shape)
                .then(
                    if (isHovered) Modifier.border(
                        BorderStroke(4.dp, glowColor),
                        shape
                    ) else Modifier
                )
                .hoverable(interactionSource = interactionSource)
                .clickable(interactionSource = interactionSource, indication = null) {
                    onClick()
                }
                .padding(16.dp),
            color = buttonColor,
            contentColor = contentColor,
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
                content = content
            )
        }
    }

}


/**
 * A sci-fi themed button with an asymmetrical design and glowing borders.
 *
 * @param onClick The callback to be invoked when the button is clicked
 * @param modifier The modifier to be applied to the button
 * @param enabled Controls the enabled state of the button
 * @param text The text to be displayed on the button
 * @param contentPadding The padding values to be applied to the content of the button
 * @param buttonColor The background color of the button
 * @param contentColor The color of the content (text) of the button
 * @param glowColor The color of the glow effect around the button
 * @param baseGlowIntensity The base intensity of the glow effect (0.0-1.0)
 */
@Composable
fun SciFiAsymButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    text: String,
    contentPadding: PaddingValues = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
    buttonColor: Color = SciFiColors.primary,
    contentColor: Color = SciFiColors.onPrimary,
    glowColor: Color = SciFiColors.primaryGlow,
    baseGlowIntensity: Float = 0.7f
) {
    // Use the asymmetrical button shape
    SciFiButton(
        onClick = onClick,
        modifier = modifier,
        enabled = enabled,
        text = text,
        contentPadding = contentPadding,
        buttonColor = buttonColor,
        contentColor = contentColor,
        glowColor = glowColor,
        shape = SciFiShapes.asymButtonShape,
        baseGlowIntensity = baseGlowIntensity
    )
}

/**
 * A sci-fi themed button with a success/positive style.
 *
 * @param onClick The callback to be invoked when the button is clicked
 * @param modifier The modifier to be applied to the button
 * @param enabled Controls the enabled state of the button
 * @param text The text to be displayed on the button
 * @param contentPadding The padding values to be applied to the content of the button
 */
@Composable
fun SciFiSuccessButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    text: String,
    contentPadding: PaddingValues = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
) {
    SciFiButton(
        onClick = onClick,
        modifier = modifier,
        enabled = enabled,
        text = text,
        contentPadding = contentPadding,
        buttonColor = SciFiColors.success,
        contentColor = SciFiColors.onPrimary,
        glowColor = SciFiColors.successGlow,
        baseGlowIntensity = 0.7f
    )
}

/**
 * A sci-fi themed button with a danger/negative style.
 *
 * @param onClick The callback to be invoked when the button is clicked
 * @param modifier The modifier to be applied to the button
 * @param enabled Controls the enabled state of the button
 * @param text The text to be displayed on the button
 * @param contentPadding The padding values to be applied to the content of the button
 */
@Composable
fun SciFiDangerButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    text: String,
    contentPadding: PaddingValues = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
) {
    SciFiButton(
        onClick = onClick,
        modifier = modifier,
        enabled = enabled,
        text = text,
        contentPadding = contentPadding,
        buttonColor = SciFiColors.error,
        contentColor = SciFiColors.onPrimary,
        glowColor = SciFiColors.errorGlow,
        baseGlowIntensity = 0.7f
    )
}

/**
 * A sci-fi themed button with a warning style.
 *
 * @param onClick The callback to be invoked when the button is clicked
 * @param modifier The modifier to be applied to the button
 * @param enabled Controls the enabled state of the button
 * @param text The text to be displayed on the button
 * @param contentPadding The padding values to be applied to the content of the button
 */
@Composable
fun SciFiWarningButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    text: String,
    contentPadding: PaddingValues = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
) {
    SciFiButton(
        onClick = onClick,
        modifier = modifier,
        enabled = enabled,
        text = text,
        contentPadding = contentPadding,
        buttonColor = SciFiColors.warning,
        contentColor = Color.Black,
        glowColor = SciFiColors.warningGlow,
        baseGlowIntensity = 0.7f
    )
}

/**
 * A sci-fi themed outline button with glowing borders.
 *
 * @param onClick The callback to be invoked when the button is clicked
 * @param modifier The modifier to be applied to the button
 * @param enabled Controls the enabled state of the button
 * @param text The text to be displayed on the button
 * @param contentPadding The padding values to be applied to the content of the button
 * @param outlineColor The color of the button outline
 * @param contentColor The color of the content (text) of the button
 * @param glowColor The color of the glow effect around the button
 * @param shape The shape of the button
 * @param baseGlowIntensity The base intensity of the glow effect (0.0-1.0)
 */
@Composable
fun SciFiOutlineButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    text: String,
    contentPadding: PaddingValues = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
    outlineColor: Color = SciFiColors.primary,
    contentColor: Color = SciFiColors.primary,
    glowColor: Color = SciFiColors.primaryGlow,
    shape: Shape = SciFiShapes.sciFiButtonShape,
    baseGlowIntensity: Float = 0.7f
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()
    val isHovered by interactionSource.collectIsHoveredAsState()

    // Increase glow intensity when hovered or pressed
    val glowIntensity by animateFloatAsState(
        targetValue = when {
            !enabled -> 0.3f
            isPressed -> 1.0f
            isHovered -> 0.85f
            else -> baseGlowIntensity
        },
        label = "glowIntensity"
    )

    // Adjust colors based on state
    val borderColor = when {
        !enabled -> outlineColor.copy(alpha = 0.5f)
        isPressed -> outlineColor.copy(alpha = 0.8f)
        else -> outlineColor
    }

    val textColor = when {
        !enabled -> contentColor.copy(alpha = 0.5f)
        else -> contentColor
    }

    // Apply sci-fi styling with glowing border
    Box(
        modifier = modifier
            .defaultMinSize(minWidth = 88.dp, minHeight = 36.dp)
            .then(
                Modifier.sciFiGlowingBorder(
                    borderColor = borderColor,
                    glowColor = glowColor,
                    borderWidth = 1.dp,
                    glowWidth = 4.dp,
                    glowIntensity = glowIntensity,
                    shape = { size ->
                        val path = androidx.compose.ui.graphics.Path()
                        val shapeHelper = shape
                        val outline = shapeHelper.createOutline(
                            size,
                            density = androidx.compose.ui.unit.Density(1f),
                            layoutDirection = androidx.compose.ui.unit.LayoutDirection.Ltr
                        )
                        path.addOutline(outline)
                        path
                    }
                )
            )
            .padding(contentPadding),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            style = SciFiTypography.buttonText,
            color = textColor,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
fun SciFiGlowButton(
    modifier: Modifier = Modifier,
    text: String,
    onClick: () -> Unit
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isHovered by interactionSource.collectIsHoveredAsState()

    val glowColor by animateColorAsState(
        if (isHovered) SciFiColors.primaryGlow else Color.Transparent,
        animationSpec = tween(300),
        label = "GlowColor"
    )

    val strokeColor by animateColorAsState(
        if (isHovered) SciFiColors.primaryGlow else SciFiColors.primary,
        animationSpec = tween(300),
        label = "StrokeColor"
    )

    Box(
        modifier = modifier
            .graphicsLayer()
            .pointerInput(Unit) { detectTapGestures(onTap = { onClick() }) }
            .hoverable(interactionSource)
            .padding(8.dp)
    ) {
        Canvas(modifier = Modifier.fillMaxSize()) {
            val strokeWidth = 3.dp.toPx()
            val path = Path().apply {
                val inset = strokeWidth / 2
                moveTo(inset + 10f, inset)
                lineTo(size.width - inset - 10f, inset)
                lineTo(size.width - inset, inset + 10f)
                lineTo(size.width - inset, size.height - inset - 10f)
                lineTo(size.width - inset - 10f, size.height - inset)
                lineTo(inset + 10f, size.height - inset)
                lineTo(inset, size.height - inset - 10f)
                lineTo(inset, inset + 10f)
                close()
            }

            drawPath(
                path = path,
                color = strokeColor,
                style = Stroke(width = strokeWidth)
            )

            if (isHovered) {
                drawPath(
                    path = path,
                    color = glowColor,
                    style = Stroke(width = strokeWidth * 2),
                    alpha = 0.6f,
                    blendMode = BlendMode.Plus
                )
            }
        }

        Box(
            modifier = Modifier
                .align(Alignment.Center)
        ) {
            Text(
                text = text,
                style = MaterialTheme.typography.labelLarge.copy(
                    color = Color.White,
                    shadow = if (isHovered)
                        Shadow(glowColor, offset = Offset.Zero, blurRadius = 20f)
                    else null
                )
            )
        }
    }
}
