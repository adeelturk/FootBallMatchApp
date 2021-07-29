package com.turk.common.util

import android.util.Log
import com.turk.common.extension.tag
import java.io.IOException
import java.net.InetSocketAddress
import javax.net.SocketFactory


//https://github.com/mitchtabian/food2fork-compose/blob/master/app/src/main/java/com/codingwithmitch/food2forkcompose/interactors/app/DoesNetworkHaveInternet.kt
object DoesNetworkHaveInternet {

    // Make sure to execute this on a background thread.
    fun execute(socketFactory: SocketFactory): Boolean {
        return try{
            Log.d(tag(), "PINGING google.")
            val socket = socketFactory.createSocket() ?: throw IOException("Socket is null.")
            socket.connect(InetSocketAddress("8.8.8.8", 53), 1500)
            socket.close()
            Log.d(tag(), "PING success.")
            true
        }catch (e: IOException){
            Log.e(tag(), "No internet connection. ${e}")
            false
        }
    }
}