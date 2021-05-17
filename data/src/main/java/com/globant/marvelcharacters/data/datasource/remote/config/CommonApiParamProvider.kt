package com.globant.marvelcharacters.data.datasource.remote.config

import com.globant.marvelcharacters.data.BuildConfig
import java.math.BigInteger
import java.security.MessageDigest

class CommonApiParamProvider {

    internal fun generateHashKey(timestamp: Long): String {
        return generateHash(timestamp.toString() + BuildConfig.API_CONFIG_PRIVATE_KEY + BuildConfig.API_CONFIG_PUBLIC_KEY)
    }

    internal fun getTimeStamp(): Long {
        return System.currentTimeMillis() / 1000
    }

    private fun generateHash(originalText: String): String {
        val digest = MessageDigest.getInstance(HASH_ALGORITHM)
        digest.update(originalText.toByteArray())
        val messageDigest = digest.digest()
        val bigInt = BigInteger(1, messageDigest)
        var hash = bigInt.toString(16)
        while (hash.length < 32) {
            hash = "0$hash"
        }
        return hash
    }

    companion object {
        private const val HASH_ALGORITHM = "MD5"
    }

}