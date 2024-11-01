package com.example.examateapp.util

import com.example.examateapp.data.model.Info
import com.example.examateapp.data.model.StudyPlan
import com.example.examateapp.data.model.Suggestions

fun studyPlanData(): List<StudyPlan>{
    val plans = ArrayList<StudyPlan>()
    plans.add(StudyPlan("1","Unit 1: what is examate",true ))
    plans.add(StudyPlan("2","Unit 2: what is TCF",false ))
    plans.add(StudyPlan("3","Writing Tasks",false ))
    plans.add(StudyPlan("4","Oral Task",false ))
    plans.add(StudyPlan("5","End",false ))
    return plans
}

fun suggestionsPartners(): List<Suggestions>{
    val suggestions = ArrayList<Suggestions>()


    suggestions.add(
        Suggestions(
        "RS",
        "Reem Sayed",
        "B1",
        "Yesterday",
        listOf("Arabic","English","French"),
        Info("Egypt","Female","29","25 May 2023")
    ))

    suggestions.add(
        Suggestions(
            "EA",
            "Eman Ahmed",
            "B2",
            "Today",
            listOf("Arabic","English","French"),
            Info("Saudi","Female","33","15 Jan 2023")
        ))

    suggestions.add(
        Suggestions(
            "SM",
            "Sara Said",
            "A1",
            "Today",
            listOf("Arabic","English","French"),
            Info("Egypt","Female","27","13 Apr 2023")
        ))

    suggestions.add(
        Suggestions(
            "NA",
            "Nourhan Adel",
            "B2",
            "Yesterday",
            listOf("Arabic","English","French"),
            Info("Egypt","Female","29","7 Feb 2024")
        ))

    return suggestions
}
