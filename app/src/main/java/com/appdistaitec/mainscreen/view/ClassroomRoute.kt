package com.appdistaitec.mainscreen.view

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.CameraPositionState
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.rememberCameraPositionState


@Composable
fun ClassroomRoute(){
    val anai=LatLng(-2.128900803907176, -79.93093854876815)
    val defaultCameraPosition=CameraPosition.fromLatLngZoom(anai,18f)
    val cameraPositionState= rememberCameraPositionState{
        position=defaultCameraPosition
    }
    var isMapLoaded by remember { mutableStateOf(false)}
    Box (
        modifier = Modifier
                .fillMaxSize()
    ){
        GoogleMapView(
            modifier=Modifier.matchParentSize(),
            cameraPositionState=cameraPositionState,
            onMapLoaded = {
                isMapLoaded=true
            }
        )
        if(!isMapLoaded){
            AnimatedVisibility(
                modifier = Modifier.matchParentSize(),
                visible = !isMapLoaded,
                enter = EnterTransition.None,
                exit = fadeOut()
            ) {
                CircularProgressIndicator(
                    modifier = Modifier
                        .background(MaterialTheme.colorScheme.background)
                        .wrapContentSize()
                )

            }
        }
    }
}

@Composable
fun GoogleMapView(
    modifier: Modifier,
    cameraPositionState: CameraPositionState = rememberCameraPositionState(),
    onMapLoaded: () -> Unit = {},
    content: @Composable () -> Unit ={},
) {
    GoogleMap(
        modifier = modifier,
        cameraPositionState=cameraPositionState,
        onMapLoaded=onMapLoaded
    ){
        content()
    }
}
