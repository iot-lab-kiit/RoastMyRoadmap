package `in`.iot.lab.roastmychoice.view.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import `in`.iot.lab.roastmychoice.R
import `in`.iot.lab.roastmychoice.data.model.CreateLevelChoiceBody
import `in`.iot.lab.roastmychoice.data.utils.UiState
import `in`.iot.lab.roastmychoice.vm.UserViewModel

@Composable
fun Questions(
    viewModel: UserViewModel = hiltViewModel(),
    navController: NavController,
    userId: Int
) {
    val data = viewModel.getDomainLevelsState.collectAsState().value
    when (data) {
        is UiState.Idle -> {
            viewModel.getDomainLevels(155)
        }
        is UiState.Loading -> {
            CircularProgressIndicator(
                color = Color.Blue,
                modifier = Modifier.size(48.dp),
                strokeWidth = 4.dp,
                trackColor = Color.Gray
            )
        }
        is UiState.Success -> {
            val currentQuestionIndex = remember { mutableStateOf(0) }
            val currentQuestion = data.data.levels[currentQuestionIndex.value]
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(colorResource(id = R.color.white))
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(colorResource(id = R.color.white))
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1.5f)
                            .background(colorResource(id = R.color.white))
                    ) {
                        // Content for the top section
                        Column(
                            modifier = Modifier
                                .align(Alignment.TopCenter)
                                .padding(16.dp) // Padding for spacing
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.innovance_text),
                                contentDescription = null,
                                modifier = Modifier
                                    .size(200.dp) // Adjust the size as needed
                                    .align(Alignment.CenterHorizontally)
                            )
                            Spacer(modifier = Modifier.height(16.dp)) // Spacer to provide space between image and text
                            Text(
                                text = currentQuestion.question,
                                color = Color.Black,
                                fontSize = 18.sp,
                                modifier = Modifier.align(Alignment.CenterHorizontally)
                            )
                        }
                    }

                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1.5f)
                            .clip(RoundedCornerShape(topStart = 30.dp, topEnd = 30.dp))
                            .background(colorResource(id = R.color.bluescreen)),
                        contentAlignment = Alignment.Center
                    ) {
                        Column(
                            modifier = Modifier.fillMaxWidth(0.9f),
                            verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterVertically),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            currentQuestion.options.forEachIndexed { index, question ->
                                GradientButton(onClick = {
                                    viewModel.createChoice(
                                        CreateLevelChoiceBody(
                                            levelId = currentQuestion.id,
                                            selected = index,
                                            userId = userId
                                        )
                                    )
                                    if (currentQuestionIndex.value < data.data.levels.size - 1) {
                                        currentQuestionIndex.value++
                                    }
                                }, text = question)
                            }
                        }
                    }
                }
            }
        }
        else -> {}
    }
}

@Composable
fun GradientButton(
    onClick: () -> Unit,
    text: String
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(
                brush = Brush.horizontalGradient(
                    colors = listOf(
                        Color(0xFFFFA11E),
                        Color(0xFFFF5216),
                        Color(0xFFFF2999),
                        Color(0xFF7B0FFF)
                    )
                )
            )
    ) {
        Button(
            onClick = onClick,
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Transparent
            ),
            contentPadding = PaddingValues(),
            modifier = Modifier
                .fillMaxSize()
                .clip(RoundedCornerShape(16.dp))
        ) {
            Text(
                text = text,
                fontSize = 18.sp,
                color = Color.White
            )
        }
    }
}