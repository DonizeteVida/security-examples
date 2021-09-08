package util

import com.sun.xml.internal.messaging.saaj.util.ByteInputStream
import org.bouncycastle.util.io.pem.PemReader
import java.io.File
import java.io.FileReader
import java.security.KeyFactory
import java.security.PrivateKey
import java.security.PublicKey
import java.security.cert.CertificateFactory
import java.security.spec.PKCS8EncodedKeySpec

//https://gist.github.com/lbalmaceda/9a0c7890c2965826c04119dcfb1a5469
fun readPem(name: String) : ByteArray = PemReader(FileReader(name)).use {
    return@use it.readPemObject().content
}

fun readBytes(name: String) : ByteArray = File(name).readBytes()

fun readPrivateKey(data: ByteArray, algorithm: String) : PrivateKey? = try {
    KeyFactory.getInstance(algorithm).run {
        generatePrivate(PKCS8EncodedKeySpec(data))
    }
} catch (e: Throwable) {
    null
}

fun readPublicKey(data: ByteArray, algorithm: String) : PublicKey? = try {
    CertificateFactory.getInstance(algorithm).run {
        generateCertificate(ByteInputStream(data, data.size))
    }.publicKey
} catch (e: Throwable) {
    null
}