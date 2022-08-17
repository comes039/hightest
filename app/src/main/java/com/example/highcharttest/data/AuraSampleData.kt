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
    1,
    listOf(
        ReportAuraInfo("HA001", "Aura confirmed", 9, 34.6),
        ReportAuraInfo("HA002", "No aura confirmed", 5, 19.2),
        ReportAuraInfo("HA003", "Aura unknown", 4, 15.4),
        ReportAuraInfo("HA004", "No record", 8, 30.8)
    )

)
val monthReportAuraData = ReportAuraResponse(
    84,
    45,
    1,
    listOf(
        ReportAuraInfo("HA001", "Aura confirmed", 45, 38.5),
        ReportAuraInfo("HA002", "No aura confirmed", 22, 18.8),
        ReportAuraInfo("HA003", "Aura unknown", 19, 13.6),
        ReportAuraInfo("HA004", "No record", 31, 29.1)
    )

)
val threeMonthReportAuraData = ReportAuraResponse(
    252,
    124,
    1,
    listOf(
        ReportAuraInfo("HA001", "Aura confirmed", 124, 45.9),
        ReportAuraInfo("HA002", "No aura confirmed", 41, 15.2),
        ReportAuraInfo("HA003", "Aura unknown", 32, 11.9),
        ReportAuraInfo("HA004", "No record", 73, 27.0)
    )

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

fun pieChartData(response: List<TagInfoList>): List<HCDataGradient> {
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
    return pieList
}

/*
 * 순서 HA001->HA002->HA003->HA004
 */
public fun packedBubbleChartData(response: ReportAuraResponse): List<HCPackedBubbleData> {
    val reportAuraInfoList = response.reportAuraInfoList
    val hcPackedBubbleDataList = ArrayList<HCPackedBubbleData>()
    for (i in reportAuraInfoList.indices) {
        hcPackedBubbleDataList.add(
            HCPackedBubbleData(
                getAuraStatusName(reportAuraInfoList[i].haveAuraStatus),
                reportAuraInfoList[i].count,
                auraColorList[i],
                reportAuraInfoList[i].auraRate.toDouble()
            )
        )
    }
    return hcPackedBubbleDataList
}

val auraColorList: List<GradientColor> = listOf(
    GradientColor("F16899", "F4B2D5"), GradientColor("9697A5", "9697A5"), GradientColor("CBCBD5", "CBCBD5"), GradientColor("E9E9E9", "E9E9E9")
)
fun getAuraStatusName(auraStatusCode:String):String{
    return when(auraStatusCode){
        "HA001"->"Aura confirmed"
        "HA002"->"No aura confirmed"
        "HA003"->"Aura unknown"
        "HA004"->"No record"
        else->{""}
    }
}