package com.app.marvelcharacters.models

import java.security.MessageDigest
import java.security.NoSuchAlgorithmException

class Constants {
    fun generateHashCode(timestamp: String, privateKey: String, publicKey: String): String {
        val MD5 = "MD5"
        val s = timestamp + privateKey + publicKey
        try {
            // Create MD5 Hash
            val digest = MessageDigest
                .getInstance(MD5)
            digest.update(s.toByteArray())
            val messageDigest = digest.digest()

            // Create Hex String
            val hexString = StringBuilder()
            for (aMessageDigest in messageDigest) {
                var h = Integer.toHexString(0xFF and aMessageDigest.toInt())
                while (h.length < 2) h = "0$h"
                hexString.append(h)
            }
            return hexString.toString()
        } catch (e: NoSuchAlgorithmException) {
            e.printStackTrace()
        }
        return ""
    }

    fun getImagePath(path: String, extension: String): String {
        return path.replace("http", "https") + "." + extension
    }

    companion object {
        const val PUBLIC_KEY = "73d9d605a65ddec831225c81b0fa6ffe"
        const val PRIVATE_KEY = "34bce2f2e5a07502c9469f03eb6529b34d8adf4a"
    }
}