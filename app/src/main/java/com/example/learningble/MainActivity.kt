package com.example.learningble

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.app.ActivityCompat
import com.example.learningble.bluetooth.ChatServer
import com.example.learningble.presentation.ChatCompose
import com.example.learningble.presentation.DeviceScanCompose
import com.example.learningble.states.DeviceConnectionState
import com.example.learningble.ui.theme.LearningBLETheme
import com.example.learningble.viewmodels.DeviceScanViewModel
import com.karumi.dexter.Dexter
import com.karumi.dexter.MultiplePermissionsReport
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.multi.MultiplePermissionsListener

private const val TAG = "MainActivity"

class MainActivity : ComponentActivity() {

    private val viewModel: DeviceScanViewModel by viewModels()

    override fun onStop() {
        super.onStop()
        ChatServer.stopServer()
    }

    override fun onResume() {
        super.onResume()
        Dexter.withContext(this)
            .withPermissions(
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.ACCESS_FINE_LOCATION
            )
            .withListener(object : MultiplePermissionsListener {
                override fun onPermissionsChecked(report: MultiplePermissionsReport) {
                    if (ActivityCompat.checkSelfPermission(
                            this@MainActivity,
                            Manifest.permission.BLUETOOTH_SCAN
                        ) != PackageManager.PERMISSION_GRANTED
                    ) {
                        return
                    }
                    Toast.makeText(
                        this@MainActivity,
                        "Permission ${report.areAllPermissionsGranted()}",
                        Toast.LENGTH_LONG
                    ).show()
                    ChatServer.startServer(application, this@MainActivity)
                    viewModel.startScan()
                }

                override fun onPermissionRationaleShouldBeShown(
                    permissions: List<PermissionRequest?>?,
                    token: PermissionToken?
                ) {

                }
            })
            .check()
    }

    @RequiresApi(Build.VERSION_CODES.S)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        setContent {
            LearningBLETheme {
                
                Scaffold(topBar = {
                    TopAppBar(
                        title = {
                            Text(text = "Bluetooth Chat App")
                        },
                        backgroundColor = MaterialTheme.colors.primary,
                        contentColor = Color.White,
                        elevation = 10.dp
                    )
                }) {


                    Surface(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(10.dp),
                        color = MaterialTheme.colors.background
                    ) {
                        val deviceScanningState by viewModel.viewState.observeAsState()

                        val deviceConnectionState by ChatServer.deviceConnection.observeAsState()

                        var isChatOpen by remember {
                            mutableStateOf(false)
                        }

                        Box(
                            contentAlignment = Alignment.TopCenter,
                            modifier = Modifier.fillMaxSize()
                        ) {
                            if (deviceScanningState != null && !isChatOpen || deviceConnectionState == DeviceConnectionState.Disconnected) {
                                Column {
                                    Text(
                                        text = "Choose a device to chat with:",
                                        fontSize = 20.sp,
                                        fontWeight = FontWeight.Bold
                                    )
                                    Spacer(modifier = Modifier.height(10.dp))
                                    DeviceScanCompose.DeviceScan(deviceScanViewState = deviceScanningState!!) {
                                        isChatOpen = true
                                    }
                                }

                            } else if(deviceScanningState != null && deviceConnectionState is DeviceConnectionState.Connected) {
                                ChatCompose.Chats((deviceConnectionState as DeviceConnectionState.Connected).device.name)
                            }
                            else{
                                Text(text = "Nothing")
                            }
                        }
                    }
                }
            }
        }
    }
}