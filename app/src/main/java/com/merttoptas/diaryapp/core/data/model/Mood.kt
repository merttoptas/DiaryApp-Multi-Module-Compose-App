package com.merttoptas.diaryapp.core.data.model

import androidx.compose.ui.graphics.Color
import com.merttoptas.diaryapp.R
import com.merttoptas.diaryapp.ui.theme.*

/**
 * Created by mertcantoptas on 02.03.2023
 */
enum class Mood(val icon: Int, val contentColor: Color, val containerColor: Color){
    Neutral(R.drawable.ic_neutral, Color.Black, NeutralColor),
    Happy(R.drawable.ic_happy, Color.Black, HappyColor),
    Angry(R.drawable.ic_angry, Color.White, AngryColor),
    Bored(
        icon = R.drawable.ic_bored,
        contentColor = Color.Black,
        containerColor = BoredColor
    ),
    Calm(
        icon = R.drawable.ic_calm,
        contentColor = Color.Black,
        containerColor = CalmColor
    ),
    Depressed(
        icon = R.drawable.ic_depressed,
        contentColor = Color.Black,
        containerColor = DepressedColor
    ),
    Disappointed(
        icon = R.drawable.ic_disappointed,
        contentColor = Color.White,
        containerColor = DisappointedColor
    ),
    Humorous(
        icon = R.drawable.ic_humorous,
        contentColor = Color.Black,
        containerColor = HumorousColor
    ),
    Lonely(
        icon = R.drawable.ic_lonely,
        contentColor = Color.White,
        containerColor = LonelyColor
    ),
    Mysterious(
        icon = R.drawable.ic_mysterious,
        contentColor = Color.Black,
        containerColor = MysteriousColor
    ),
    Romantic(
        icon = R.drawable.ic_romantic,
        contentColor = Color.White,
        containerColor = RomanticColor
    ),
    Shameful(
        icon = R.drawable.ic_shameful,
        contentColor = Color.White,
        containerColor = ShamefulColor
    ),
    Awful(
        icon = R.drawable.ic_awful,
        contentColor = Color.Black,
        containerColor = AwfulColor
    ),
    Surprised(
        icon = R.drawable.ic_surprised,
        contentColor = Color.Black,
        containerColor = SurprisedColor
    ),
    Suspicious(
        icon = R.drawable.ic_suspicious,
        contentColor = Color.Black,
        containerColor = SuspiciousColor
    ),
    Tense(
        icon = R.drawable.ic_tense,
        contentColor = Color.Black,
        containerColor = TenseColor
    )
}