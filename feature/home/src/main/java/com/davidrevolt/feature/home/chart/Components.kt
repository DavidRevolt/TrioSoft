package com.davidrevolt.feature.home.chart


import android.graphics.Typeface
import android.text.Layout
import android.text.TextUtils
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.patrykandpatrick.vico.compose.component.shape.chartShape
import com.patrykandpatrick.vico.compose.component.shape.shader.toDynamicShader
import com.patrykandpatrick.vico.compose.dimensions.dimensionsOf
import com.patrykandpatrick.vico.compose.extension.pixelSize
import com.patrykandpatrick.vico.compose.legend.legendItem
import com.patrykandpatrick.vico.compose.legend.verticalLegend
import com.patrykandpatrick.vico.compose.style.currentChartStyle
import com.patrykandpatrick.vico.core.DEF_LABEL_LINE_COUNT
import com.patrykandpatrick.vico.core.DefaultDimens
import com.patrykandpatrick.vico.core.component.Component
import com.patrykandpatrick.vico.core.component.OverlayingComponent
import com.patrykandpatrick.vico.core.component.shape.LineComponent
import com.patrykandpatrick.vico.core.component.shape.ShapeComponent
import com.patrykandpatrick.vico.core.component.shape.Shapes
import com.patrykandpatrick.vico.core.component.shape.shader.DynamicShader
import com.patrykandpatrick.vico.core.component.text.TextComponent
import com.patrykandpatrick.vico.core.component.text.textComponent
import com.patrykandpatrick.vico.core.dimensions.Dimensions
import com.patrykandpatrick.vico.core.dimensions.MutableDimensions
import com.patrykandpatrick.vico.core.dimensions.emptyDimensions

public typealias ChartShape = com.patrykandpatrick.vico.core.component.shape.Shape

/**
 * Creates and remembers a [LineComponent] with the specified properties.
 */
@Composable
public fun rememberLineComponent(
    color: Color = Color.Black,
    thickness: Dp,
    shape: ChartShape,
    dynamicShader: DynamicShader? = null,
    margins: Dimensions = emptyDimensions(),
    strokeWidth: Dp = 0.dp,
    strokeColor: Color = Color.Transparent,
): LineComponent =
    remember(
        color,
        thickness,
        shape,
        dynamicShader,
        margins,
        strokeWidth,
        strokeColor,
    ) {
        LineComponent(
            color = color.toArgb(),
            thicknessDp = thickness.value,
            shape = shape,
            dynamicShader = dynamicShader,
            margins = margins,
            strokeWidthDp = strokeWidth.value,
            strokeColor = strokeColor.toArgb(),
        )
    }

/**
 * Creates and remembers a [LineComponent] with the specified properties.
 */
@Composable
public fun rememberLineComponent(
    color: Color = Color.Black,
    thickness: Dp = DefaultDimens.COLUMN_WIDTH.dp,
    shape: Shape = RectangleShape,
    dynamicShader: DynamicShader? = null,
    margins: Dimensions = emptyDimensions(),
    strokeWidth: Dp = 0.dp,
    strokeColor: Color = Color.Transparent,
): LineComponent =
    rememberLineComponent(
        color = color,
        thickness = thickness,
        shape = shape.chartShape(),
        dynamicShader = dynamicShader,
        margins = margins,
        strokeWidth = strokeWidth,
        strokeColor = strokeColor,
    )

/**
 * Creates and remembers a [ShapeComponent] with the specified properties.
 */
@Composable
public fun rememberShapeComponent(
    shape: ChartShape = Shapes.rectShape,
    color: Color = Color.Black,
    dynamicShader: DynamicShader? = null,
    margins: Dimensions = emptyDimensions(),
    strokeWidth: Dp = 0.dp,
    strokeColor: Color = Color.Transparent,
): ShapeComponent =
    remember(
        shape,
        color,
        dynamicShader,
        margins,
        strokeWidth,
        strokeColor,
    ) {
        ShapeComponent(
            shape = shape,
            color = color.toArgb(),
            dynamicShader = dynamicShader,
            margins = margins,
            strokeWidthDp = strokeWidth.value,
            strokeColor = strokeColor.toArgb(),
        )
    }

/**
 * Creates and remembers a [ShapeComponent] with the specified properties.
 */
@Composable
public fun rememberShapeComponent(
    shape: Shape,
    color: Color = Color.Black,
    dynamicShader: DynamicShader? = null,
    margins: Dimensions = emptyDimensions(),
    strokeWidth: Dp = 0.dp,
    strokeColor: Color = Color.Transparent,
): ShapeComponent =
    rememberShapeComponent(
        shape = shape.chartShape(),
        color = color,
        dynamicShader = dynamicShader,
        margins = margins,
        strokeWidth = strokeWidth,
        strokeColor = strokeColor,
    )

/**
 * Creates and remembers a [ShapeComponent] with the specified properties.
 */
@Composable
public fun rememberShapeComponent(
    shape: ChartShape = Shapes.rectShape,
    color: Color = Color.Black,
    brush: Brush,
    margins: Dimensions = emptyDimensions(),
    strokeWidth: Dp = 0.dp,
    strokeColor: Color = Color.Transparent,
): ShapeComponent =
    rememberShapeComponent(
        shape = shape,
        color = color,
        dynamicShader = brush.toDynamicShader(),
        margins = margins,
        strokeWidth = strokeWidth,
        strokeColor = strokeColor,
    )

/**
 * Creates an [OverlayingComponent].
 *
 * @param outer the outer (background) [Component].
 * @param inner the inner (foreground) [Component].
 * @property innerPaddingStart the start padding between the inner and outer components.
 * @property innerPaddingTop the top padding between the inner and outer components.
 * @property innerPaddingEnd the end padding between the inner and outer components.
 * @property innerPaddingBottom the bottom padding between the inner and outer components.
 */
@Composable
public fun overlayingComponent(
    outer: Component,
    inner: Component,
    innerPaddingStart: Dp = 0.dp,
    innerPaddingTop: Dp = 0.dp,
    innerPaddingBottom: Dp = 0.dp,
    innerPaddingEnd: Dp = 0.dp,
): OverlayingComponent =
    remember(
        outer,
        inner,
        innerPaddingStart,
        innerPaddingTop,
        innerPaddingBottom,
        innerPaddingEnd,
    ) {
        OverlayingComponent(
            outer = outer,
            inner = inner,
            innerPaddingStartDp = innerPaddingStart.value,
            innerPaddingTopDp = innerPaddingTop.value,
            innerPaddingBottomDp = innerPaddingBottom.value,
            innerPaddingEndDp = innerPaddingEnd.value,
        )
    }

/**
 * Creates an [OverlayingComponent].
 *
 * @param outer the outer (background) [Component].
 * @param inner the inner (foreground) [Component].
 * @param innerPaddingAll the padding between the inner and outer components.
 */
@Composable
public fun overlayingComponent(
    outer: Component,
    inner: Component,
    innerPaddingAll: Dp,
): OverlayingComponent =
    overlayingComponent(
        outer = outer,
        inner = inner,
        innerPaddingStart = innerPaddingAll,
        innerPaddingTop = innerPaddingAll,
        innerPaddingBottom = innerPaddingAll,
        innerPaddingEnd = innerPaddingAll,
    )

/**
 * Creates and remembers a [TextComponent].
 *
 * @param color the text color.
 * @param textSize the text size.
 * @param background an optional [ShapeComponent] to be displayed behind the text.
 * @param ellipsize the text truncation behavior.
 * @param lineCount the line count.
 * @param padding the padding between the text and the background.
 * @param margins the margins around the background.
 * @param typeface the [Typeface] for the text.
 * @param textAlignment the text alignment.
 */
@Composable
public fun rememberTextComponent(
    color: Color = Color.Black,
    textSize: TextUnit = DefaultDimens.TEXT_COMPONENT_TEXT_SIZE.sp,
    background: ShapeComponent? = null,
    ellipsize: TextUtils.TruncateAt = TextUtils.TruncateAt.END,
    lineCount: Int = DEF_LABEL_LINE_COUNT,
    padding: MutableDimensions = emptyDimensions(),
    margins: MutableDimensions = emptyDimensions(),
    typeface: Typeface? = null,
    textAlignment: Layout.Alignment = Layout.Alignment.ALIGN_NORMAL,
): TextComponent =
    remember(color, textSize, background, ellipsize, lineCount, padding, margins, typeface, textAlignment) {
        textComponent {
            this.color = color.toArgb()
            textSizeSp = textSize.pixelSize()
            this.ellipsize = ellipsize
            this.lineCount = lineCount
            this.background = background
            this.padding = padding
            this.margins = margins
            this.typeface = typeface
            this.textAlignment = textAlignment
        }
    }




@Composable
fun rememberLegend(parms: List<String>) =
    verticalLegend(
        items =
        chartColors.mapIndexed { index, chartColor ->
            legendItem(
                icon = rememberShapeComponent(Shapes.pillShape, chartColor),
                label =
                rememberTextComponent(
                    color = currentChartStyle.axis.axisLabelColor,
                    textSize = legendItemLabelTextSize,
                    typeface = Typeface.MONOSPACE,
                ),
                labelText =parms[index],
            )
        },
        iconSize = legendItemIconSize,
        iconPadding = legendItemIconPaddingValue,
        spacing = legendItemSpacing,
        padding = legendPadding,
    )
private const val COLOR_1_CODE = 0xffb983ff
private const val COLOR_2_CODE = 0xff91b1fd
private val color1 = Color(COLOR_1_CODE)
private val color2 = Color(COLOR_2_CODE)
private val chartColors = listOf(color1, color2)
private val legendItemLabelTextSize = 12.sp
private val legendItemIconSize = 8.dp
private val legendItemIconPaddingValue = 10.dp
private val legendItemSpacing = 4.dp
private val legendTopPaddingValue = 8.dp
private val legendPadding = dimensionsOf(top = legendTopPaddingValue)