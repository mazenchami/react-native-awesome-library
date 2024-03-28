package com.awesomecontactslibrary

import android.Manifest
import android.content.pm.PackageManager
import com.facebook.react.bridge.Arguments
import com.facebook.react.bridge.ReactApplicationContext
import com.facebook.react.bridge.ReactMethod
import com.facebook.react.bridge.Promise
import com.facebook.react.modules.core.PermissionAwareActivity
import com.facebook.react.modules.core.PermissionListener

class AwesomeContactsLibraryModule internal constructor(context: ReactApplicationContext) :
  AwesomeContactsLibrarySpec(context), PermissionListener {
  private var promise: Promise? = null
  private val requestCode: Int = 101

  override fun getName(): String {
    return NAME
  }

  @ReactMethod
  override fun hasContactsPermission(): Boolean {
    val permission = reactApplicationContext.checkSelfPermission(Manifest.permission.READ_CONTACTS)
    
    return permission == PackageManager.PERMISSION_GRANTED
  }

  @ReactMethod
  override fun requestContactsPermission(promise: Promise) {
    if (this.promise != null) {
      return
    }
    val activity = reactApplicationContext.currentActivity as PermissionAwareActivity
    this.promise = promise
    activity.requestPermissions(arrayOf(Manifest.permission.READ_CONTACTS), this.requestCode, this)
  }

  companion object {
    const val NAME = "AwesomeContactsLibrary"
  }

  override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>?, results: IntArray?): Boolean {
    if (requestCode != this.requestCode || this.promise == null || results == null || results.isEmpty()) {
        return false
    }
    val readContactsPermissionResult = results[0]
    this.promise?.resolve(readContactsPermissionResult == PackageManager.PERMISSION_GRANTED)
    return true
  }
}
