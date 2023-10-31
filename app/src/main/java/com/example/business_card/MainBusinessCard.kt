package com.example.business_card

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.ui.Alignment
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.business_card.ui.theme.Business_CardTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Business_CardTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Card()
                }
            }
        }
    }
}

/**
 * Funcion Main que en una columna agrupa la parte superior e inferior de la coolumna
 */
@Composable
fun Card(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color(212, 245, 255))
    ) {

        TopColumn()
        BottomColumn()
    }
}

/**
 * Funcion que declara como será la columna superior de la tarjeta llamando a la funcion TopColumnStructure()
 */
@Composable
private fun TopColumn(){
    TopColumnStructure(
        name = stringResource(R.string.name),
        occupation = stringResource(R.string.occupation),
        button = {
                     CompanyLinkButton()
                     },
        imageId = R.drawable.fotopersonal,
        company = stringResource(R.string.company_name)
    )
}

/**
 * Funcion que declara como será la columna inferior de la tarjeta
 */
@Composable
private fun BottomColumn(
) {

    Column {

        PhoneCallButton(imageId = R.drawable.icons8_whatsapp_64, textButton = stringResource(R.string.contact))
        SocialNetworksButtons(
            imageId = R.drawable.icons8_google_maps_64,
            textButton = stringResource(R.string.direction),
            urlSocial = stringResource(R.string.google_maps_url)
        )
        EmailButtonLink(imageId = R.drawable.icons8_gmail_64, textButton = stringResource(R.string.email_address))
        SocialNetworksButtons(
            imageId = R.drawable.icons8_twitter_64,
            urlSocial = stringResource(R.string.twitter_url),
            textButton = stringResource(R.string.twitter)
        )
        SocialNetworksButtons(
            imageId = R.drawable.icons8_linkedin_64,
            urlSocial = stringResource(R.string.linkedin_url),
            textButton = stringResource(R.string.linkedin)
        )
    }

}

/**
 * Funcion que declara como debe ser la estructura de la targeta
 */
@Composable
private fun TopColumnStructure(
    name: String,
    occupation: String,
    company: String,
    //Declaro que esta variable es de tipo: funcion que es @Composable y devuelve -> Unit, es decir nada
    //Basicamente asignamos una funcion a una variable :)
    button: @Composable ()  -> Unit,
    imageId: Int,
    modifier: Modifier = Modifier
) {

    Column (
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(imageId),
            contentDescription = null,
            modifier = modifier
                .align(Alignment.CenterHorizontally)
                .size(200.dp)
                .padding(20.dp)
        )
        Text(
            text = name,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold,
            modifier = modifier
                .align(Alignment.CenterHorizontally)

        )
        Text(
            text = occupation,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold,
            modifier = modifier
                .align(Alignment.CenterHorizontally)
                .padding(20.dp)
        )
        Text(
            text = company,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold,
            modifier = modifier
                .align(Alignment.CenterHorizontally)
        )
        button()

    }
}

/**
 * Boton que te lleva a una URI ya predefinida en estte caso la empresa en la que se supone que trabaja la persona de la tarjeta
 */
@Composable
private fun CompanyLinkButton(
    textButton: String = stringResource(R.string.company_button_text),
    urlCompany: String = stringResource(R.string.company_url)
) {
    val context = LocalContext.current

    Button(
        onClick = {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(urlCompany))
            context.startActivity(intent)
        },
        modifier = Modifier
            .fillMaxWidth()
            .clickable { }
            .padding(20.dp)
    ) {
        Text(
            text = textButton,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .align(Alignment.CenterVertically)
        )
    }
}

/**
 * Botones basicos con estilo que te llevan a una URI especifica
 */
@Composable
private fun SocialNetworksButtons(
    imageId: Int,
    textButton: String,
    urlSocial: String,

) {
    //AQUI, NO SE SI LO HE ENTENDIDO BIEN, PERO SEGUN CIERTAS FUENTES, CUANDO DESEEMOS HACER UNA ACCION ESPECIFICA COMO EL ABRIR UN ENLACE, HACER CLICK EN UN BOTON, U OTRAS TAREAS 'ESPECIFICAS'
    //DEBEMOS CREAR UN CONTEXTO PARA LA APP, PORQUE¿? PUES SEGUN TENGO ENTENDIDO, EN MI CASO, SI QUIERO QUE ABRIR 'X' ENLACE, ESTE ENLACE AL SER EXTERNO DEBE RECIBIR CONTEXTO ACTUAL DE LA APP ANDROID
    //PARA QUE LA ACCION QUE HACE 'INTENT' SE PRODUZCA, ES POR ESTO QUE EN LA VARIABLE 'CONTEXT' ASIGNAMOS EL CONTEXTO ACTUAL DEL LOCAL (EN ESE CASO ESTA APP)
    val context = LocalContext.current

    Button(
        onClick = {
            //LA OPERACION DE ESTE INTENT SERÁ QUE APARTIR DE LA URL, SE NOS INTENTE MOSTRAR/DIRIGIR A LA DIRECCION EN ESTE CASO WEB ESPECIFICADA EN LA VARIABLE urlSocial
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(urlSocial))
            context.startActivity(intent)
        },
        modifier = Modifier
            .fillMaxWidth()
            .clickable { },
        colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent)
    ) {
        Image(
            painter = painterResource(imageId),
            contentDescription ="",
            modifier = Modifier
                .weight(0.75f)
                .size(34.dp)

        )
        Text(
            text = textButton,
            fontWeight = FontWeight.Bold,
            style = TextStyle (color = Color.Black),
            modifier = Modifier
                .weight(2.25f)
                .align(Alignment.CenterVertically)
                .padding(start = 22.dp)
        )
    }
}

/**
 * Funcion que es un boton con una direccion de correo y estilos ya predefinidos
 * Este boton cuando se llama te lleva directamente a la app GMAIL a que puedas enviar un correo a la direccion predefinida
 */
@Composable
fun EmailButtonLink(
    imageId: Int,
    textButton: String,
    emailAddress: String = stringResource(R.string.personal_mail_address)
) {
    val context = LocalContext.current

    val uri = Uri.parse("mailto:$emailAddress")
    //LA OPERACION DE ESTE INTENT SERÁ QUE APARTIR DE LA URL, SE NOS INTENTE DIRIGIR A ESCRIBIR UN CORREO A LA DIRECCION ESPECIFICADA EN LA VARIABLE emailAddress
    val intent = Intent(Intent.ACTION_SENDTO, uri)

    Button(
        onClick = {
            context.startActivity(intent)
        },
        colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent)
    ) {
        Image(
            painter = painterResource(imageId),
            contentDescription = "",
            modifier = Modifier
                .weight(0.75f)
                .size(34.dp)

        )
        Text(
            text = textButton,
            fontWeight = FontWeight.Bold,
            style = TextStyle(color = Color.Black),
            modifier = Modifier
                .weight(2.25f)
                .align(Alignment.CenterVertically)
                .padding(start = 22.dp)
        )
    }
}

/**
 * Funcion que es un boton con un numero y estilos ya predefinidos
 * Este boton cuando se llama te lleva directamente a la app telefono con el numero predefinido
 */
@Composable
fun PhoneCallButton(
    imageId: Int,
    textButton: String,
    phoneNumber: String = stringResource(R.string.personal_phone_number)
) {
    val context = LocalContext.current

    val uri = Uri.parse("tel:$phoneNumber")
    //LA OPERACION DE ESTE INTENT SERÁ QUE APARTIR DEl NUMERO DADO EN LA VARIABLE phoneNumber, SE NOS INTENTE DIRIGIR AL DIAL DE LA APP DEL SISTEMA TELEFONO Y ESTE SE ESCRIBIR SOLO EN EL CAMPO NUMERICO DEL DIAL PARA QUE SE
    //PUEDA AGREGAR EL NUMERO O LLAMAR DIRECTAMENTE
    val intent = Intent(Intent.ACTION_DIAL, uri)

    Button(
        onClick = {
            context.startActivity(intent)
        },
        colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent)
    ) {
        Image(
            painter = painterResource(imageId),
            contentDescription = "",
            modifier = Modifier
                .weight(0.75f)
                .size(34.dp)

        )
        Text(
            text = textButton,
            fontWeight = FontWeight.Bold,
            style = TextStyle(color = Color.Black),
            modifier = Modifier
                .weight(2.25f)
                .align(Alignment.CenterVertically)
                .padding(start = 22.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun CardPreview() {
    Business_CardTheme {
        Card()
    }
}