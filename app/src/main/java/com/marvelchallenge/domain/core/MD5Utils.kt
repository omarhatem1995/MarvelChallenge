package com.marvelchallenge.domain.core

import java.security.MessageDigest
import java.security.NoSuchAlgorithmException

object MD5Utils {
    fun getMd5Key(ts: String): String? {
        val hash: String? = null
        val input: String = ts+  Constants.PRIVATE_KEY + Constants.PUPLIC_KEY

        val MD5 = "MD5"
        try {
            // Create MD5 Hash
            val digest = MessageDigest
                .getInstance(MD5)
            digest.update(input.toByteArray())
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
        return null
    }

}