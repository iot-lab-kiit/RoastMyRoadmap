package `in`.iot.lab.roastmychoice.view.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import `in`.iot.lab.roastmychoice.R

@Composable
fun OnBoardingImage(){
    Image(
        painter = painterResource(id = R.drawable.innovance_logo),
        contentDescription = "Logo",
        modifier = Modifier.size(250.dp)
    )
}