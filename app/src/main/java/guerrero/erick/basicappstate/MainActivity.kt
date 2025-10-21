package guerrero.erick.basicappstate

import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import guerrero.erick.basicappstate.ui.theme.BasicAppStateTheme
import kotlin.random.Random

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val contexto = LocalContext.current
            BasicAppStateTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Content(modifier = Modifier.padding(innerPadding),contexto)
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Composable
fun Content(modifier: Modifier = Modifier, context: Context){

    var respuesta by remember { mutableStateOf("") }
    var numero1 by remember { mutableStateOf(1) }
    var numero2 by remember { mutableStateOf(3) }
    var showDialog by remember {mutableStateOf(false)}

    var mult = numero2 * numero1



    Column(verticalArrangement = Arrangement.spacedBy(23.dp)) {
        Text(text = "$numero1 * $numero2", modifier = modifier.weight(3f).fillMaxSize(), fontSize = 30.sp, textAlign = TextAlign.Center)
        Text(text = "=", modifier = modifier.weight(1f).fillMaxSize(), fontSize = 40.sp, textAlign = TextAlign.Center)
        Box(modifier = modifier.weight(2f),){
            TextField(
                value = respuesta,
                onValueChange = { respuesta = it },
                modifier = modifier.fillMaxSize(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                label = { Text("Ingresa la respuesta") },
                singleLine = true,

                )
        }
        Box(modifier = modifier.weight(3f)){
            Button(onClick = {
                var resp = respuesta.toInt()//respuesta del usuario a Int
                var resultado = comprobar(resp, mult)
                numero1 = getRandomNumber()
                numero2 = getRandomNumber()
                respuesta = ""
                if(resultado){
                    Toast.makeText(context, "Correcto", Toast.LENGTH_SHORT).show()
                }else{
                    Toast.makeText(context, "Incorrecto", Toast.LENGTH_SHORT).show()
                }
                //
            }, modifier.width(500.dp)) {
                Text("Comprobar respuesta")
            }
        }

    }
}

fun getResultado(res:Boolean){

}

@Composable
fun alertDialogDoc(titulo:String, texto:String) {
        val openDialog = remember { mutableStateOf(true) }

        if (openDialog.value) {
            androidx.compose.material3.AlertDialog(
                onDismissRequest = {
                    openDialog.value = false
                },
                title = {
                    Text(text = titulo)
                },
                text = {
                    Text(
                        texto
                    )
                },
                confirmButton = {
                    TextButton(
                        onClick = {
                            openDialog.value = false
                        }
                    ) {
                        Text("Aceptar")
                    }
                }
            )
        }
    }




    fun getRandomNumber(): Int {
    return Random.nextInt(0, 10)
}

fun comprobar(resUsuario:Int, resCorrecta:Int):Boolean{
    return resUsuario == resCorrecta
}




@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    BasicAppStateTheme {
        Greeting("Android")

    }
}