//package com.example.healplus.utils.helpers
//import android.content.Context
//import android.net.ConnectivityManager
//import android.net.Network
//import android.net.NetworkCapabilities
//import android.net.NetworkRequest
//import android.widget.Toast
//import com.example.healplus.utils.constants.ConnectionType
//import kotlinx.coroutines.channels.awaitClose
//import kotlinx.coroutines.flow.Flow
//import kotlinx.coroutines.flow.MutableStateFlow
//import kotlinx.coroutines.flow.StateFlow
//import kotlinx.coroutines.flow.asStateFlow
//import kotlinx.coroutines.flow.callbackFlow
//import javax.inject.Inject
//import javax.inject.Singleton
//
//@Singleton
//class NetworkManager @Inject constructor(
//    @ApplicationContext private val context: Context
//) {
//    private val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
//
//    private val _connectionStatus = MutableStateFlow<List<ConnectionType>>(emptyList())
//    val connectionStatus: StateFlow<List<ConnectionType>> = _connectionStatus.asStateFlow()
//
//    private val _isConnected = MutableStateFlow(false)
//    val isConnected: StateFlow<Boolean> = _isConnected.asStateFlow()
//
//    init {
//        startNetworkMonitoring()
//    }
//
//    /**
//     * Start monitoring network connectivity changes
//     */
//    private fun startNetworkMonitoring() {
//        val networkRequest = NetworkRequest.Builder()
//            .addCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
//            .build()
//
//        connectivityManager.registerNetworkCallback(networkRequest, object : ConnectivityManager.NetworkCallback() {
//            override fun onAvailable(network: Network) {
//                super.onAvailable(network)
//                updateConnectionStatus()
//            }
//
//            override fun onLost(network: Network) {
//                super.onLost(network)
//                updateConnectionStatus()
//                showNoInternetToast()
//            }
//
//            override fun onCapabilitiesChanged(
//                network: Network,
//                networkCapabilities: NetworkCapabilities
//            ) {
//                super.onCapabilitiesChanged(network, networkCapabilities)
//                updateConnectionStatus()
//            }
//        })
//
//        // Initial check
//        updateConnectionStatus()
//    }
//
//    /**
//     * Update connection status and emit to StateFlow
//     */
//    private fun updateConnectionStatus() {
//        val connections = getCurrentConnections()
//        _connectionStatus.value = connections
//        _isConnected.value = connections.isNotEmpty() && !connections.contains(ConnectionType.NONE)
//    }
//
//    /**
//     * Get current network connections
//     */
//    private fun getCurrentConnections(): List<ConnectionType> {
//        val connections = mutableListOf<ConnectionType>()
//
//        try {
//            val network = connectivityManager.activeNetwork
//            val networkCapabilities = connectivityManager.getNetworkCapabilities(network)
//
//            if (networkCapabilities != null) {
//                when {
//                    networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> {
//                        connections.add(ConnectionType.WIFI)
//                    }
//                    networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> {
//                        connections.add(ConnectionType.MOBILE)
//                    }
//                    networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> {
//                        connections.add(ConnectionType.ETHERNET)
//                    }
//                    else -> {
//                        connections.add(ConnectionType.NONE)
//                    }
//                }
//            } else {
//                connections.add(ConnectionType.NONE)
//            }
//        } catch (e: Exception) {
//            connections.add(ConnectionType.NONE)
//        }
//
//        return connections
//    }
//
//    /**
//     * Check if device is connected to internet
//     */
//    fun isConnected(): Boolean {
//        return try {
//            val network = connectivityManager.activeNetwork ?: return false
//            val networkCapabilities = connectivityManager.getNetworkCapabilities(network) ?: return false
//
//            networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) ||
//                    networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) ||
//                    networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)
//        } catch (e: Exception) {
//            false
//        }
//    }
//
//    /**
//     * Get network connectivity as Flow for reactive programming
//     */
//    fun getConnectivityFlow(): Flow<Boolean> = callbackFlow {
//        val networkRequest = NetworkRequest.Builder()
//            .addCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
//            .build()
//
//        val callback = object : ConnectivityManager.NetworkCallback() {
//            override fun onAvailable(network: Network) {
//                trySend(true)
//            }
//
//            override fun onLost(network: Network) {
//                trySend(false)
//            }
//        }
//
//        connectivityManager.registerNetworkCallback(networkRequest, callback)
//
//        // Send initial value
//        trySend(isConnected())
//
//        awaitClose {
//            connectivityManager.unregisterNetworkCallback(callback)
//        }
//    }
//
//    /**
//     * Show toast for no internet connection
//     */
//    private fun showNoInternetToast() {
//        Toast.makeText(context, "No Internet Connection", Toast.LENGTH_SHORT).show()
//    }
//
//    /**
//     * Get connection type as string
//     */
//    fun getConnectionType(): String {
//        return when {
//            _connectionStatus.value.contains(ConnectionType.WIFI) -> "WiFi"
//            _connectionStatus.value.contains(ConnectionType.MOBILE) -> "Mobile Data"
//            _connectionStatus.value.contains(ConnectionType.ETHERNET) -> "Ethernet"
//            else -> "No Connection"
//        }
//    }
//}