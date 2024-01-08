package com.ozanyazici.statemanagementcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainScreen()
        }
    }
}
//mutableStateOf fonksiyonu, değişken bir değeri tutan bir MutableState nesnesini oluşturur.
//Bu nesne, durum değiştikçe Compose'un otomatik olarak yeniden çizim yapmasını sağlar.
//remember fonksiyonu kullanılarak oluşturulan myString nesnesi,
//bileşenin yeniden çizilmesi sırasında önceki durumunu korur.
//Bu, kullanıcı arayüzünün önceki state'in değerini sıfırlamadan
//güncellemeleri işlemesine olanak tanır.
@Composable
fun MainScreen() {
    var myString = remember { mutableStateOf("") }
    Surface(modifier = Modifier.fillMaxSize()) {
        Column(verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally) {
            SpecialText(string = "Test")
            Spacer(modifier = Modifier.padding(5.dp))
            SpecialText(string = "Android")
            Spacer(modifier = Modifier.padding(5.dp))
            SpecialTextField(myString.value) {
                myString.value = it
            }
        }
    }
}

@Composable
fun SpecialText(string : String) {
    Text(text = string,
        fontSize = 20.sp,
        fontStyle = FontStyle.Italic,
        fontFamily = FontFamily.SansSerif)
}

//SpecialTextField daki textfield ın durumunu bir üst bileşen olan mainScreen de kontrol ediyoruz.
// Buna State hoisting deniyor. Böylece birden fazla specialtextfield gerektiği durumlarda
// her textfield a yazılan değeri başka işlemlerde kullanabileceğim (Özerklik).
// Hoist kelime anlamı kaldırma.
@Composable
fun SpecialTextField(string : String, function: (String) -> Unit) {
    TextField(value = string, onValueChange = function, modifier = Modifier.padding(5.dp))
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MainScreen()
}