package md.ecdsa

import util.readBytes
import util.readPem
import util.readPrivateKey
import util.readPublicKey
import java.nio.ByteBuffer
import java.security.*
import java.security.spec.ECGenParameterSpec

fun EC_generate_key() =
    KeyPairGenerator.getInstance("EC").apply {
        initialize(ECGenParameterSpec("secp256r1"))
    }.genKeyPair()

fun MD_ECDSA_sign(key: PrivateKey, data: ByteBuffer) =
    Signature.getInstance("SHA512withECDSA").apply {
        initSign(key)
        update(data)
    }.sign()

fun MD_ECDSA_sign(key: PrivateKey, data: ByteArray) =
    Signature.getInstance("SHA512withECDSA").apply {
        initSign(key)
        update(data)
    }.sign()

fun MD_ECDSA_verify(key: PublicKey, data: ByteBuffer, signature: ByteArray) =
    Signature.getInstance("SHA512withECDSA").apply {
        initVerify(key)
        update(data)
    }.verify(signature)

fun MD_ECDSA_verify(key: PublicKey, data: ByteArray, signature: ByteArray) =
    Signature.getInstance("SHA512withECDSA").apply {
        initVerify(key)
        update(data)
    }.verify(signature)

fun main(args: Array<String>) {
    val data = "My cool data".toByteArray()

    val keyPair = EC_generate_key()
    val signature = MD_ECDSA_sign(keyPair.private, data)
    val result = MD_ECDSA_verify(keyPair.public, data, signature)

    println(result)

    val readSignature = readBytes("./data.bin")
    val privateKey: PrivateKey? = readPrivateKey(readPem("./priv_key.pem"), "EC")
    val publicKey: PublicKey? = readPublicKey(readPem("./pub_key.pem"), "X.509")

    if (privateKey == null || publicKey == null) return
    val anotherResult = MD_ECDSA_verify(publicKey, data, readSignature)
    println(anotherResult)
}