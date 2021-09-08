package cipher

import java.security.KeyPairGenerator
import javax.crypto.Cipher

fun main() {
    val data = "Donizete Junior Ribeiro Vida".toByteArray()
    val keyPairGenerator = KeyPairGenerator.getInstance("RSA")
    val key = keyPairGenerator.genKeyPair()
    val cipher = Cipher.getInstance("RSA")
    cipher.init(Cipher.ENCRYPT_MODE, key.public)
    val encrypt = cipher.doFinal(data)
    cipher.init(Cipher.DECRYPT_MODE, key.private)
    val decrypt = cipher.doFinal(encrypt)
    assert(decrypt.contentEquals(data))
}