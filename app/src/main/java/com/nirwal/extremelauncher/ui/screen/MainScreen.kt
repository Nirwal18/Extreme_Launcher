package com.nirwal.extremelauncher.ui.screen

import android.Manifest
import android.app.WallpaperManager
import android.content.pm.PackageManager
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.app.ActivityCompat
import androidx.core.graphics.drawable.toBitmap
import org.koin.androidx.compose.koinViewModel

@Preview(showBackground = true)
@Composable
fun MainScreen() {
    val vm:MainViewModel = koinViewModel()
    val context = LocalContext.current
//    if (ActivityCompat.checkSelfPermission(
//            context,
//            Manifest.permission.READ_EXTERNAL_STORAGE
//        ) != PackageManager.PERMISSION_GRANTED
//    ) {
//        return
//    }
//    WallpaperManager.getInstance(context).drawable.toBitmap(1000,1000).asImageBitmap()
    val appList = vm.apps.collectAsState(arrayListOf()).value

    LazyColumn(Modifier.background(color = Color.Transparent)){
        items(appList.size){
            val app = appList[it]
            Column(modifier = Modifier.clickable {
                context.startActivity(context.getPackageManager().getLaunchIntentForPackage(app.packageName!!))

            }) {
                Image( app.icon!!.toBitmap(200,200).asImageBitmap(), null )
                Text(text = app.label!!)
            }
        }
    }
}