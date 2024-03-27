package com.awesomecontactslibrary

import com.facebook.react.bridge.ReactApplicationContext
import com.facebook.react.bridge.ReactContextBaseJavaModule
import com.facebook.react.bridge.Promise

abstract class AwesomeContactsLibrarySpec internal constructor(context: ReactApplicationContext) :
  ReactContextBaseJavaModule(context) {
    abstract fun hasContactsPermission(): Boolean
    abstract fun requestContactsPermission(promise: Promise)
}
