package `in`.iot.lab.roastmychoice.view.screens

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import `in`.iot.lab.roastmychoice.data.model.CreateUserBody
import `in`.iot.lab.roastmychoice.data.utils.UiState
import `in`.iot.lab.roastmychoice.view.components.AppTextField
import `in`.iot.lab.roastmychoice.view.components.DomainOptionUI
import `in`.iot.lab.roastmychoice.view.components.OnBoardingImage
import `in`.iot.lab.roastmychoice.view.navigation.AppScreens
import `in`.iot.lab.roastmychoice.vm.UserViewModel

val GradientBrush = Brush.linearGradient(
    colors = listOf(
        Color(0xFFFFA11E),
        Color(0xFFFF521E),
        Color(0xFFFF2898),
        Color(0xFF7B0FFF)
    ),
    tileMode = TileMode.Clamp
)

@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
fun OnboardingScreen(
    navController: NavController,
    viewModel: UserViewModel = hiltViewModel(),
) {

    var name by remember { mutableStateOf("") }
    var rollNo by remember { mutableStateOf("") }
    var selectedChip by remember { mutableIntStateOf(0) }
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF041529))
            .padding(top = 80.dp, start = 16.dp, end = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {

        OnBoardingImage()

        // User ID Text Field with Gradient Border Only
        AppTextField(
            value = name,
            title = "User name"
        ) {
            name = it
        }

        // Roll Number
        AppTextField(
            value = rollNo,
            title = "Roll Number"
        ) {
            rollNo = it
        }

        // List of Domains
        val domainList: List<Pair<Int, String>> = listOf(
            Pair(1, "App Dev"), Pair(2, "IoT"),
            Pair(3, "Web Dev"), Pair(4, "CP"), Pair(5, "Cybersecurity"), Pair(6, "ML")
        )

        // Domain UI
        FlowRow(
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {

            domainList.forEach { (id, text) ->
                DomainOptionUI(
                    text = text,
                    isSelected = selectedChip == id
                ) {
                    selectedChip = id
                }
            }
        }

        val data = viewModel.createUserState.collectAsState().value

        Button(
            onClick = {
                viewModel.createUser(
                    userDetails = CreateUserBody(
                        domainId = selectedChip,
                        name = name,
                        rollNo = rollNo
                    )
                )
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .background(
                    brush = GradientBrush,
                    shape = RoundedCornerShape(16.dp)
                ),
            colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
            shape = RoundedCornerShape(16.dp),
            contentPadding = PaddingValues()
        ) {
            Text(
                modifier = Modifier
                    .padding(vertical = 16.dp),
                text = "Get Started",
                color = Color.White,
                fontSize = 22.sp
            )
        }
        when (data) {
            is UiState.Loading -> Toast.makeText(context, "Loading", Toast.LENGTH_SHORT).show()
            is UiState.Success -> {
                navController.navigate(AppScreens.QuestionsScreen.route + "/${data.data.id}/${selectedChip}")
                viewModel.resetCreateUserState()
            }

            else -> {
            }
        }
    }
}

@Composable
fun ClickableChip(text: String, isSelected: Boolean, onClick: () -> Unit) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .clickable { onClick() }
            .background(
                color = if (isSelected) Color(0xFF7B0FFF) else Color.White,
                shape = RoundedCornerShape(16.dp)
            )
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        Text(
            text = text,
            color = if (isSelected) Color.White else Color.Black,
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium
        )
    }
}