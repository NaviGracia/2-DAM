package com.example.jetpacktest

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintLayoutScope


@Preview(showBackground = true)
@Composable
fun ConstraintExample(){
    ConstraintLayout(modifier = Modifier.fillMaxSize()) {
        val (boxBlack, boxCyan1, boxCyan2, boxCyan3, boxCyan4,
            boxBlue1, boxBlue2, boxBlue3, boxBlue4) = createRefs()
        Box(modifier = Modifier
            .size(40.dp)
            .background(Color.Black)
            .constrainAs(boxBlack){
                top.linkTo(parent.top)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                bottom.linkTo(parent.bottom)
        })
        Box(modifier = Modifier
            .size(40.dp)
            .background(Color.Cyan)
            .constrainAs(boxCyan1){
                bottom.linkTo(boxBlack.top)
                end.linkTo(boxBlack.start)
            })
        Box(modifier = Modifier
            .size(40.dp)
            .background(Color.Cyan)
            .constrainAs(boxCyan2){
                bottom.linkTo(boxBlack.top)
                start.linkTo(boxBlack.end)
            })
        Box(modifier = Modifier
            .size(40.dp)
            .background(Color.Cyan)
            .constrainAs(boxCyan3){
                top.linkTo(boxBlack.bottom)
                start.linkTo(boxBlack.end)
            })
        Box(modifier = Modifier
            .size(40.dp)
            .background(Color.Cyan)
            .constrainAs(boxCyan4){
                top.linkTo(boxBlack.bottom)
                end.linkTo(boxBlack.start)
            })
        Box(modifier = Modifier
            .size(40.dp)
            .background(Color.Blue)
            .constrainAs(boxBlue1){
                bottom.linkTo(boxCyan1.top)
                start.linkTo(boxCyan1.end)
            })
        Box(modifier = Modifier
            .size(40.dp)
            .background(Color.Blue)
            .constrainAs(boxBlue2){
                top.linkTo(boxCyan3.bottom)
                start.linkTo(boxCyan1.end)
            })
        Box(modifier = Modifier
            .size(40.dp)
            .background(Color.Blue)
            .constrainAs(boxBlue3){
                top.linkTo(boxCyan3.bottom)
                start.linkTo(boxCyan3.end)
            })
        Box(modifier = Modifier
            .size(40.dp)
            .background(Color.Blue)
            .constrainAs(boxBlue4){
                top.linkTo(boxCyan3.bottom)
                end.linkTo(boxCyan4.start)
            })
    }
}

@Composable
fun create4CyanBox(ly: ConstraintLayoutScope){
    val (boxCyan1, boxCyan2, boxCyan3, boxCyan4) = ly.createRefs()
    for(x in 1..4){
        Box (modifier = Modifier
            .size(40.dp)
            .background(color = Color.Cyan)


        )
    }
}







