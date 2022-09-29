package com.me.pokedex.ui

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import android.util.Log
import android.view.animation.OvershootInterpolator
import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.me.pokedex.R
import com.me.pokedex.commons.*
import com.me.pokedex.presentation.MainViewModel
import com.me.pokedex.router.DestinationScreen
import com.me.pokedex.ui.theme.OrangeLight
import com.me.pokedex.ui.theme.OrangeNormal
import com.me.pokedex.ui.theme.OrangeSunset
import com.me.pokedex.ui.theme.typography
import kotlinx.coroutines.*
import org.koin.androidx.compose.get

private const val TAG = "munky.LoadingScreen"
private val items = PokeImageResources.items

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun oldSplashScreen(
    viewModel: MainViewModel = get(),
    onScreenLoaded: onDismissTypeSuspend
) {
    val job = Job()
    val scope = CoroutineScope(Dispatchers.IO + job)

    val pokemonItem = remember { mutableStateOf(items[0])}
    var musicStarted by remember { mutableStateOf(false) }

    val onImageLoaded : (PokeImage) -> Unit = { item ->
        scope.launch(Dispatchers.Main) {
            pokemonItem.value = item
        }
    }



    ListItem(pokemonItem.value, modifier = Modifier.fillMaxSize())
    Log.d(TAG, "SplashScreen")

}






@Composable
fun ListItem(pokeImg: PokeImage, modifier : Modifier = Modifier) {
    Log.d(TAG, "ListItem - " + stringResource(pokeImg.pokeNameResourceId) )
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Image (
            modifier = Modifier.size(170.dp, 200.dp),
            painter = painterResource(id = pokeImg.pokeImageResourceId),
            contentScale = ContentScale.Fit,
            contentDescription = stringResource(pokeImg.pokeNameResourceId),
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = stringResource(pokeImg.pokeNameResourceId),
            style = typography.h1
        )
    }
}



@Composable
fun SplashScreen(navController: NavController) {

    val scaleAnimation: Animatable<Float, AnimationVector1D> =
        remember { Animatable(initialValue = 0f) }

    AnimationSplashContent(
        scaleAnimation = scaleAnimation,
        navController = navController,
        durationMillisAnimation = 6000,
        delayScreen = 3000L
    )

    DesignSplashScreen(
        imagePainter = painterResource(id = R.drawable.ic_pichu),
        scaleAnimation = scaleAnimation
    )
}

@Composable
fun AnimationSplashContent(
    scaleAnimation: Animatable<Float, AnimationVector1D>,
    navController: NavController,
    durationMillisAnimation: Int,
    delayScreen: Long
) {

    LaunchedEffect(key1 = true) {
        scaleAnimation.animateTo(
            targetValue = 0.5F,
            animationSpec = tween(
                durationMillis = durationMillisAnimation,
                easing = {
                    OvershootInterpolator(3F).getInterpolation(it)
                }
            )
        )

        delay(timeMillis = delayScreen)
        navController.navigate(route =
        DestinationScreen.MainScreenDest.route) {
            popUpTo(route =
            DestinationScreen.SplashScreenDest.route) {
                inclusive = true
            }
        }
    }
}


@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun DesignSplashScreen(
    modifier: Modifier = Modifier,
    imagePainter: Painter,
    scaleAnimation: Animatable<Float, AnimationVector1D>,
) {

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    colors = listOf(
                        OrangeNormal,
                        OrangeSunset,
                        OrangeLight,
                    )
                )
            ),
        contentAlignment = Alignment.TopCenter
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Image(
                painter = imagePainter,
                contentDescription = stringResource(id = R.string.app_name),
                modifier = modifier
                    .size(550.dp)
                    .scale(scale = scaleAnimation.value),
            )
            Text(
                text = stringResource(id = R.string.loading),
                color = Color.White,
                fontSize = 40.sp,
                fontWeight = FontWeight.ExtraBold,
                fontFamily = FontFamily.Serif,
                textAlign = TextAlign.Center,
                modifier = modifier.scale(scale = scaleAnimation.value)
            )
            LinearProgressIndicator()
        }
    }

}
