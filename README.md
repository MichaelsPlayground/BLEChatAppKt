# BLEChatApp
created chat app with bluetooth low energy apis



https://user-images.githubusercontent.com/50947867/198204748-eb19b166-38a6-406b-bfd6-47e2e6e46d0c.mp4



https://user-images.githubusercontent.com/50947867/198204758-7812b52a-5b3f-4848-bda0-048b85044124.mp4



```plaintext
2022-11-01 21:16:00.179  9354-9354  AndroidRuntime          com.example.learningble              E  FATAL EXCEPTION: main
                                                                                                    Process: com.example.learningble, PID: 9354
                                                                                                    java.lang.SecurityException: Need android.permission.BLUETOOTH_CONNECT permission for AttributionSource { uid = 10578, packageName = com.example.learningble, attributionTag = null, token = android.os.BinderProxy@170b053, next = null }: GattService registerServer
                                                                                                    	at android.os.Parcel.createExceptionOrNull(Parcel.java:2438)
                                                                                                    	at android.os.Parcel.createException(Parcel.java:2422)
                                                                                                    	at android.os.Parcel.readException(Parcel.java:2405)
                                                                                                    	at android.os.Parcel.readException(Parcel.java:2347)
                                                                                                    	at android.bluetooth.IBluetoothGatt$Stub$Proxy.registerServer(IBluetoothGatt.java:3361)
                                                                                                    	at android.bluetooth.BluetoothGattServer.registerCallback(BluetoothGattServer.java:496)
                                                                                                    	at android.bluetooth.BluetoothManager.openGattServer(BluetoothManager.java:325)
                                                                                                    	at android.bluetooth.BluetoothManager.openGattServer(BluetoothManager.java:286)
                                                                                                    	at android.bluetooth.BluetoothManager.openGattServer(BluetoothManager.java:244)
                                                                                                    	at com.example.learningble.bluetooth.ChatServer.setupGattServer(ChatServer.kt:117)
                                                                                                    	at com.example.learningble.bluetooth.ChatServer.startServer(ChatServer.kt:76)
                                                                                                    	at com.example.learningble.MainActivity$onStart$1.onPermissionsChecked(MainActivity.kt:53)
                                                                                                    	at com.karumi.dexter.DexterInstance$1.run(Unknown Source:43)
                                                                                                    	at com.karumi.dexter.MainThread.execute(Unknown Source:6)
                                                                                                    	at com.karumi.dexter.DexterInstance.checkMultiplePermissions(Unknown Source:71)
                                                                                                    	at com.karumi.dexter.DexterInstance.checkPermissions(Unknown Source:0)
                                                                                                    	at com.karumi.dexter.Dexter.check(Unknown Source:10)
                                                                                                    	at com.example.learningble.MainActivity.onStart(MainActivity.kt:64)
                                                                                                    	at android.app.Instrumentation.callActivityOnStart(Instrumentation.java:1455)
                                                                                                    	at android.app.Activity.performStart(Activity.java:8315)
                                                                                                    	at android.app.ActivityThread.handleStartActivity(ActivityThread.java:4136)
                                                                                                    	at android.app.servertransaction.TransactionExecutor.performLifecycleSequence(TransactionExecutor.java:221)
                                                                                                    	at android.app.servertransaction.TransactionExecutor.cycleToPath(TransactionExecutor.java:201)
                                                                                                    	at android.app.servertransaction.TransactionExecutor.executeLifecycleState(TransactionExecutor.java:173)
                                                                                                    	at android.app.servertransaction.TransactionExecutor.execute(TransactionExecutor.java:97)
                                                                                                    	at android.app.ActivityThread$H.handleMessage(ActivityThread.java:2443)
                                                                                                    	at android.os.Handler.dispatchMessage(Handler.java:106)
                                                                                                    	at android.os.Looper.loopOnce(Looper.java:226)
                                                                                                    	at android.os.Looper.loop(Looper.java:313)
                                                                                                    	at android.app.ActivityThread.main(ActivityThread.java:8751)
                                                                                                    	at java.lang.reflect.Method.invoke(Native Method)
                                                                                                    	at com.android.internal.os.RuntimeInit$MethodAndArgsCaller.run(RuntimeInit.java:571)
                                                                                                    	at com.android.internal.os.ZygoteInit.main(ZygoteInit.java:1135)
```