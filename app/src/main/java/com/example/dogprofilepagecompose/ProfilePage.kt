package com.example.dogprofilepagecompose

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import androidx.constraintlayout.compose.Dimension


@Composable
fun ProfilePage() {
    Card(
        elevation = 6.dp,
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 100.dp, bottom = 100.dp, start = 16.dp, end = 16.dp)
            .border(width = 2.dp, color = Color.White, shape = RoundedCornerShape(30.dp))
    ) {
        BoxWithConstraints() {
            val constraints = if (minWidth < 600.dp){
                portraidConstraints(16.dp)
            }
            else{
                landscapeConstraint(16.dp)
            }

        //Content of our card, including picture, name, description and buttons
        ConstraintLayout(constraints) {



            Image(painter = painterResource(id = R.drawable.ismael),
                contentDescription = "Ismael",
                modifier = Modifier
                    .size(100.dp)
                    .clip(CircleShape)
                    .border(
                        width = 2.dp,
                        color = Color.Red,
                        shape = CircleShape
                    ).layoutId("image"),
                contentScale = ContentScale.Crop
            )

            Text(
                text = "Ismael Gonzalez Brouchy",
                modifier = Modifier.layoutId("nameText")
            )
            Text(
                text = "Youtuber",
                modifier = Modifier.layoutId("jobName")
            )

            Row(
                horizontalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .layoutId("rowStat")
            ) {
                ProfileStats(count = "150", title = "Followers")
                ProfileStats(count = "100", title = "Following")
                ProfileStats(count = "30", title = "Videos")
            }

            Button(
                onClick = { /*TODO*/ },
                modifier = Modifier.layoutId("followButton")
            ) {
                Text(text = "Follow user")
            }

            Button(
                onClick = { /*TODO*/ },
                modifier = Modifier.layoutId("messageButton")
            ) {
                Text(text = "Direct Message")
            }
        }
        }
    }
}

@Composable
fun ProfileStats(count: String, title: String){
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = count, fontWeight = FontWeight.Bold)
        Text(text = title)
    }
}

private fun portraidConstraints(margin: Dp): ConstraintSet{
    return ConstraintSet{
        val image = createRefFor("image")
        val nameText = createRefFor("nameText")
        val jobName = createRefFor("jobName")
        val rowStat = createRefFor("rowStat")
        val followButton = createRefFor("followButton")
        val messageButton = createRefFor("messageButton")
        val guideline = createGuidelineFromTop(0.3f)

        constrain(image){
            top.linkTo(parent.top)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
        }

        constrain(nameText){
            top.linkTo(image.bottom)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
        }

        constrain(jobName){
            top.linkTo(nameText.bottom)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
        }

        constrain(rowStat){
            top.linkTo(jobName.bottom)
        }

        constrain(followButton){
            top.linkTo(rowStat.bottom, margin = 16.dp)
            start.linkTo(parent.start)
            end.linkTo(messageButton.start)
            width= Dimension.wrapContent
        }

        constrain(messageButton){
            top.linkTo(rowStat.bottom, margin = 16.dp)
            start.linkTo(followButton.end)
            end.linkTo(parent.end)
            width= Dimension.wrapContent
        }
    }
}

private fun landscapeConstraint(margin: Dp): ConstraintSet{
    return ConstraintSet{
        val image = createRefFor("image")
        val nameText = createRefFor("nameText")
        val jobName = createRefFor("jobName")
        val rowStat = createRefFor("rowStat")
        val followButton = createRefFor("followButton")
        val messageButton = createRefFor("messageButton")
        val guideline = createGuidelineFromTop(0.3f)

        constrain(image){
            top.linkTo(parent.top, margin = margin)
            start.linkTo(nameText.start)
            end.linkTo(nameText.end)
        }

        constrain(nameText){
            top.linkTo(image.bottom)
            start.linkTo(parent.start, margin = margin)
        }

        constrain(jobName){
            top.linkTo(nameText.bottom)
            start.linkTo(nameText.start)
            end.linkTo(nameText.end)
        }

        constrain(rowStat){
            top.linkTo(image.top)
            start.linkTo(nameText.end, margin = margin)
            end.linkTo(parent.end, margin = margin)
        }

        constrain(followButton){
            top.linkTo(rowStat.bottom, margin = 16.dp)
            start.linkTo(rowStat.start)
            end.linkTo(messageButton.start)
            width= Dimension.wrapContent
        }

        constrain(messageButton){
            top.linkTo(rowStat.bottom, margin = 16.dp)
            start.linkTo(followButton.end)
            end.linkTo(parent.end)
            width= Dimension.wrapContent
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ProfilePAgePreview() {
    ProfilePage()
}