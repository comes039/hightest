package com.example.highcharttest.data

import com.example.highcharttest.chart.data.*
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*
import kotlin.math.round


class getDate(
    val startDate: String,
    val endDate: String
)


val format = DateTimeFormatter.ofPattern("MMM d, yyyy", Locale.ENGLISH)

// 7days
val week = getDate(
    LocalDate.now().minusWeeks(1).plusDays(1).format(format),
    LocalDate.now().format(format)
)

// 1month
val month = getDate(
    LocalDate.now().minusMonths(1).format(format),
    LocalDate.now().format(format)
)

// 3 month
val threeMonth = getDate(
    LocalDate.now().minusMonths(3).format(format),
    LocalDate.now().format(format)
)
val weekReportAuraData = ReportAuraResponse(
    26,
    9,
    null,
    null,
    null,
    1,
    "HA001",
    "Had Aura",
    "9",
    "34.6",
    "HA004",
    "Skip",
    "8",
    "30.8",
    "HA002",
    "No aura",
    "5",
    "19.2",
    "HA003",
    "Unknown",
    "4",
    "15.4"

)
val monthReportAuraData = ReportAuraResponse(
    84,
    45,
    null,
    null,
    null,
    1,
    "HA001",
    "Had Aura",
    "45",
    "38.5",
    "HA004",
    "Skip",
    "31",
    "29.1",
    "HA002",
    "No aura",
    "22",
    "18.8",
    "HA003",
    "Unknown",
    "19",
    "13.6"

)
val threeMonthReportAuraData = ReportAuraResponse(
    252,
    124,
    null,
    null,
    null,
    1,
    "HA001",
    "Had Aura",
    "124",
    "45.9",
    "HA004",
    "Skip",
    "73",
    "27.0",
    "HA002",
    "No aura",
    "41",
    "15.2",
    "HA003",
    "Unknown",
    "32",
    "11.9"

)
public val weekPieData = ReportAuraTagResponse(
    1, 34.6, 7, listOf(
        TagInfoList(1, 78, "Double vision", 45, 57.6),
        TagInfoList(1, 78, "Headache", 11, 14.1),
        TagInfoList(1, 78, "Unknown", 8, 10.2),
        TagInfoList(1, 78, "Anxiety", 5, 6.4),
        TagInfoList(1, 78, "Upset stomach", 4, 5.1),
        TagInfoList(1, 78, "Sleep issue", 3, 3.8),
        TagInfoList(1, 78, "Tiredness", 2, 2.5),
    )
)
val monthPieData = ReportAuraTagResponse(
    1, 34.6, 7, listOf(
        TagInfoList(1, 78, "Double vision", 45 * 2, 57.6),
        TagInfoList(1, 78, "Headache", 11 * 2, 14.1),
        TagInfoList(1, 78, "Unknown", 8 * 2, 10.2),
        TagInfoList(1, 78, "Anxiety", 5 * 2, 6.4),
        TagInfoList(1, 78, "Upset stomach", 4 * 2, 5.1),
        TagInfoList(1, 78, "Sleep issue", 3 * 2, 3.8),
        TagInfoList(1, 78, "Tiredness", 2 * 2, 2.5),
    )
)
val threeMonthPieData = ReportAuraTagResponse(
    1, 34.6, 7, listOf(
        TagInfoList(1, 78, "Double vision", 45 * 6, 57.6),
        TagInfoList(1, 78, "Headache", 11 * 6, 14.1),
        TagInfoList(1, 78, "Unknown", 8 * 6, 10.2),
        TagInfoList(1, 78, "Anxiety", 5 * 6, 6.4),
        TagInfoList(1, 78, "Upset stomach", 4 * 6, 5.1),
        TagInfoList(1, 78, "Sleep issue", 3 * 6, 3.8),
        TagInfoList(1, 78, "Tiredness", 2 * 6, 2.5),
    )
)
val colorList = listOf(
    GradientColor("F16899", "F4B2D5"),
    GradientColor("696A73", "696A73"),
    GradientColor("A0A0A0", "A0A0A0"),
    GradientColor("CCCCCC", "CCCCCC"),
    GradientColor("E9E9E9", "E9E9E9"),
)

private fun pieChartData(response: List<TagInfoList>): List<HCDataGradient> {
    val inputData: ArrayList<HCDataGradient> = ArrayList()
    var sumOtherValue = 0
    for (i in response.indices) {
        if (i < 4) {
            inputData.add(HCDataGradient(response[i].auraTag, response[i].tagCount, colorList[i]))
        } else {
            sumOtherValue += response[i].tagCount.toInt()
        }
    }
    inputData.add(HCDataGradient("Other", sumOtherValue, colorList[4]))
    return inputData
}

public fun pieListData(response: List<TagInfoList>): List<SampleData> {
    var otherPercent = 100.0
    var sumOtherValue = 0
    val pieList: ArrayList<SampleData> = arrayListOf()
    for (i in response.indices) {
        if (i < 4) {
            val percent = String.format("%.0f", round(response[i].rate.toDouble())) + "%"
            otherPercent -= String.format("%.0f", round(response[i].rate.toDouble())).toDouble()
            pieList.add(SampleData(response[i].auraTag, response[i].tagCount, i + 1, percent))
        } else {
            sumOtherValue += response[i].tagCount.toInt()
        }
    }
    pieList.add(SampleData("Other", sumOtherValue, 5, String.format("%.0f", round(otherPercent)) + "%"))
    return pieList
}
public fun pieAllListData(response: List<TagInfoList>): List<SampleData> {
    var otherPercent = 100.0
    var sumOtherValue = 0
    val pieList: ArrayList<SampleData> = arrayListOf()
    for (i in response.indices) {
        val percent = String.format("%.0f", round(response[i].rate.toDouble())) + "%"
        if (i < 4) {

            otherPercent -= String.format("%.0f", round(response[i].rate.toDouble())).toDouble()
            pieList.add(SampleData(response[i].auraTag, response[i].tagCount, i + 1, percent))
        } else {
            pieList.add(SampleData(response[i].auraTag, response[i].tagCount, 5, percent))
        }
    }
    pieList.add(SampleData("Other", sumOtherValue, 5, String.format("%.0f", round(otherPercent)) + "%"))
    return pieList
}

private fun packedBubbleChartData(response: ReportAuraResponse): List<HCPackedBubbleData> {
    return listOf(
        HCPackedBubbleData(
            "Aura confirmed", response.auraConfirmed.toInt(), GradientColor("F16899", "F4B2D5"), response.firstRate.toDouble()
        ),
        HCPackedBubbleData(
            "No aura confirmed", response.noAuraConfirmed.toInt(), GradientColor("9697A5", "9697A5"), response.secondRate.toDouble()
        ),
        HCPackedBubbleData(
            "Aura unknown", response.auraUnknown.toInt(), GradientColor("CBCBD5", "CBCBD5"), response.thirdRate.toDouble()
        ),
        HCPackedBubbleData(
            "No record", response.noRecord.toInt(), GradientColor("E9E9E9", "E9E9E9"), response.fourthRate.toDouble()
        )
    )
}
