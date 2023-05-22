package com.nirwal.extremelauncher.ui.screen

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.drawable.Drawable
import android.os.Build
import android.util.Log
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.flow


class MainViewModel(val context: Context):ViewModel() {


    val apps = flow<ArrayList<AppInfo>> {

        val pManager: PackageManager = context.getPackageManager()

        val appsList = ArrayList<AppInfo>()
        val i = Intent(Intent.ACTION_MAIN, null)
        i.addCategory(Intent.CATEGORY_LAUNCHER)

        val allApps= if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU){
            pManager.queryIntentActivities(i, PackageManager.ResolveInfoFlags.of(PackageManager.MATCH_ALL.toLong()))
        }else{
            pManager.queryIntentActivities(i,PackageManager.GET_ACTIVITIES)
        }


        for (ri in allApps) {
            val app = AppInfo()
            app.label = ri.loadLabel(pManager) as String?
            app.packageName = ri.activityInfo.packageName
            Log.i(" Log package ", app.packageName.toString())
            app.icon = ri.activityInfo.loadIcon(pManager)
            appsList.add(app)
        }
        emit(appsList)
    }

    fun getAppList(){
    }


}

data class AppInfo(
    var icon:Drawable? = null,
    var label:String? = null,
    var packageName:String?= null

)