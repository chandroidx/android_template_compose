package com.chandroidx.core

import android.graphics.PointF
import android.graphics.Rect
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PointMode
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke

fun DrawScope.drawRect(rect: Rect) {
  drawRect(
    color = Color.Red,
    topLeft = Offset(rect.left.toFloat(), rect.top.toFloat()),
    size = Size(rect.width().toFloat(), rect.height().toFloat()),
    style = Stroke(3f),
  )
}

fun DrawScope.drawLine(
  start: PointF?,
  end: PointF?,
  color: Color = Color.White,
  strokeWidth: Float = 2f,
) {
  if (start == null || end == null) return

  drawLine(
    color = color,
    start = toOffset(start),
    end = toOffset(end),
    strokeWidth = strokeWidth,
  )
}

fun DrawScope.drawPoints(
  points: List<PointF>,
  pointColor: Color = Color.White,
  strokeWidth: Float = 2f,
) {
  drawPoints(
    points = points.map(::toOffset),
    pointMode = PointMode.Points,
    color = pointColor,
    strokeWidth = strokeWidth,
    cap = StrokeCap.Round,
  )
}

fun DrawScope.drawPointsWithPolygon(
  points: List<PointF>,
  pointColor: Color = Color.White,
  lineColor: Color = Color.Red,
  polygonWidth: Float = 2f,
  pointWidth: Float = 2.5f,
) {
  drawPoints(
    points = points.map(::toOffset),
    pointMode = PointMode.Polygon,
    color = lineColor,
    strokeWidth = polygonWidth,
  )

  drawPoints(
    points = points.map(::toOffset),
    pointMode = PointMode.Points,
    color = pointColor,
    strokeWidth = pointWidth,
    cap = StrokeCap.Round,
  )
}

private fun toOffset(point: PointF) = Offset(point.x, point.y)
