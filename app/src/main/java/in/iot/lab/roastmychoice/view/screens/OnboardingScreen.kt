package `in`.iot.lab.roastmychoice.view.screens

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import `in`.iot.lab.roastmychoice.R
import `in`.iot.lab.roastmychoice.data.model.CreateUserBody
import `in`.iot.lab.roastmychoice.data.utils.UiState
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OnboardingScreen(
    navController: NavController,
    viewModel: UserViewModel = hiltViewModel()
) {

    val labelTextStyle = TextStyle(
        fontWeight = FontWeight.Normal,
        color = Color.White,
        fontSize = 16.sp
    )
    val textFieldStyle = TextStyle(
        fontWeight = FontWeight.Medium,
        fontSize = 16.sp,
        color = Color.White
    )
    var name by remember { mutableStateOf("") }
    var rollNo by remember { mutableStateOf("") }

    var selectedChip by remember { mutableStateOf(0) }

    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF041529))
            .padding(top = 80.dp, start = 16.dp, end = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Image(
            painter = painterResource(id = R.drawable.innovance_logo),
            contentDescription = "Logo",
            modifier = Modifier.size(250.dp)
        )

        Spacer(modifier = Modifier.height(24.dp))

        // User ID Text Field with Gradient Border Only
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp)
                .border(
                    width = 2.dp, // Border width
                    brush = GradientBrush,
                    shape = RoundedCornerShape(16.dp)
                )
        ) {
            OutlinedTextField(
                value = name,
                onValueChange = { name = it },
                label = { Text("User ID", style = labelTextStyle, color = Color.White) },
                singleLine = true,
                textStyle = textFieldStyle,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp), // Padding inside the text field
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Color.Transparent, // Hide default border
                    unfocusedBorderColor = Color.Transparent,
                    focusedLabelColor = Color.White,
                    unfocusedLabelColor = Color.White,
                    cursorColor = Color.White,
                    containerColor = Color.Transparent // Ensure background is transparent
                ),
                shape = RoundedCornerShape(16.dp),
                keyboardOptions = KeyboardOptions.Default,
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp)
                .border(
                    width = 2.dp, // Border width
                    brush = GradientBrush,
                    shape = RoundedCornerShape(16.dp)
                )
        ) {
            OutlinedTextField(
                value = rollNo,
                onValueChange = { rollNo = it },
                label = { Text("Roll Number", style = labelTextStyle, color = Color.White) },
                singleLine = true,
                textStyle = textFieldStyle,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Color.Transparent,
                    unfocusedBorderColor = Color.Transparent,
                    focusedLabelColor = Color.White,
                    unfocusedLabelColor = Color.White,
                    cursorColor = Color.White,
                    containerColor = Color.Transparent
                ),
                shape = RoundedCornerShape(16.dp),
                keyboardOptions = KeyboardOptions.Default,
            )
        }

        Spacer(modifier = Modifier.height(28.dp))


        Column(
            modifier = Modifier.padding(horizontal = 20.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.fillMaxWidth(),
            ) {
                ClickableChip(text = "App Dev", isSelected = selectedChip == 155) {
                    selectedChip = 155
                }
                ClickableChip(
                    text = "Cybersecurity",
                    isSelected = selectedChip == 2
                ) {
                    selectedChip = 2
                }
                ClickableChip(text = "AI/ML", isSelected = selectedChip == 3) {
                    selectedChip = 3
                }
            }
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.fillMaxWidth(),
            ) {
                ClickableChip(text = "Web Dev", isSelected = selectedChip == 4) {
                    selectedChip = 4
                }
                ClickableChip(text = "IoT", isSelected = selectedChip == 5) {
                    selectedChip = 5
                }
                ClickableChip(text = "CP", isSelected = selectedChip == 6) {
                    selectedChip = 6
                }
            }
        }

        Spacer(modifier = Modifier.height(28.dp))

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
                .height(66.dp)
                .padding(horizontal = 15.dp)
                .background(brush = GradientBrush, shape = RoundedCornerShape(16.dp)),
            colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
            shape = RoundedCornerShape(16.dp),
            contentPadding = PaddingValues()
        ) {
            Text(
                text = "Get Started",
                color = Color.White,
                fontSize = 22.sp,
                modifier = Modifier.padding(vertical = 20.dp)
            )
        }
        when (data) {
            is UiState.Loading -> Toast.makeText(context, "Loading", Toast.LENGTH_SHORT).show()
            is UiState.Success -> {
                navController.navigate(AppScreens.QuestionsScreen.route + "/${data.data.id}")
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