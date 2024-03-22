package com.works.getir_odev_1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import java.io.File

fun sum(a: Int, b: Int): Int {
    return a + b
}

fun sumSum(a: Int, b: Int) = a + b

fun printSum(a: Int, b: Int) {
    println("Sayfa 10, toplamı: ${sum(a, b)}")
}

////////////////////////////////////////////////////////

val PI = 3.14
val a: Int = 1
val b = 2
var x = 5

fun incrementX() {
    x += 1
}
/////////////////////////////////////////////////////////

open class Shape

class Box(val height: Double, val width: Double) : Shape() {
    val perimeter = (height + width) * 2
}
///////////////////////////////////////////////////////////

open class Color(val name: String)

class Boxes(val height: Double, val width: Double, color: Color) : Color(color.name) {
    fun printDetails() {
        println("Kutunun boyutları - Yükseklik: $height, Genişlik: $width, Renk: $name")
    }
}
////////////////////////////////////////////////////////////////////////////////////////////////

fun maxOf(num1: Int, num2: Int): Int = if (num1 > num2) num1 else num2

/////////////////////////////////////////////////////////////////////////////////////////

val fruits = listOf("mango", "pineapple", "papaya")

//////////////////////////////////////////////////
val vegetables = listOf("carrot", "lettuce", "broccoli")
var index = 0

///////////////////////////////

val lowerBound = 5
val upperBound = 7
val rangeCheck = if (lowerBound in 1..upperBound+1) "Fits in range" else "Out of range"

val fruit = listOf("apple", "banana", "cherry")
val rangeCheckList = if (-1 !in 0..fruit.lastIndex) "-1 is out of range" else "-1 is in range"
val sizeCheckList = if (fruit.size !in fruit.indices) "List size is out of valid list indices range" else "List size is within the valid indices range"

val forLoopResults = (1..5).joinToString(" ")
val forLoopStepResults = (1..10 step 2).joinToString(" ")
val forLoopDownToResults = (9 downTo 0 step 3).joinToString(" ")

fun describe(obj: Any): String =
    when (obj) {
        42 -> "cevap"
        "dünya" -> "gezegen"
        is Double -> "double"
        !is Int -> "integer değil"
        else -> "başka birşey"
    }

///////////////////////////////////////////////////////////////////////////////////

val vegetable = listOf("carrot", "cabbage", "cauliflower", "potato")

///////////////////////////////////////////////////////////////////////////////////

val text: String? = null

////////////////////////////////

fun lengthOrNull(obj: Any): Int? {
    if (obj is String && obj.length > 0) {
        return obj.length
    }
    if (obj !is String) return null
    return null
}
///////////////////////////////////////////////////

class MyContainer {
    val size = 0

    val isEmpty: Boolean
        get() = size == 0

    val contentDescription: String
        get() = "sayfa 34."
}

/////////////////////////////////////////////////////////////////

fun greet(
    personName: String = "Arkadaş",
    salutation: String = "Merhaba"
) {
    println("$salutation, $personName!")
}

////////////////////////////////////////////////////

val emailList = listOf("info@example.com", "contact@example.org", "support@sample.net")

///////////////////////////////////////////////////////////////////////////////////////////////

val readOnlyList = listOf("elma", "muz", "kiraz")
val readOnlyMap = mapOf("elma" to 1, "muz" to 2, "kiraz" to 3)
//////////////////////////////////////////////////////////////////////////////////////////////

fun String.addExclamation(): String {
    return this + "!"
}
/////////////////////////////////////////////////////////////////

object ResourceHolder {
    init {
        println("Kaynak tutucu sınıf başlatıldı.")
    }

    fun loadResource() {
        println("Kaynak sınıfından bir kaynak yüklendi.")
    }
}

////////////////////////////////////////////////////////////////////////////

fun getFolderSize(): Int {


    return 42
}



abstract class Vehicle {
    abstract fun startEngine(): String
}

class Car : Vehicle() {
    override fun startEngine(): String {
        return "Araba motoru çalıştırılıyor!"
    }
}
//////////////////////////////////////////////////////////////

fun mapMoodToColor(mood: String): String {
    return when (mood) {
        "happy" -> "Yellow"
        "sad" -> "Blue"
        "angry" -> "Red"
        else -> throw IllegalArgumentException("Geçersiz ruh hali değeri")
    }
}

//////////////////////////////////////////////////////////////////////////////

val t = 3
val result = if (t == 1) {
    "bir"
} else if (t == 2) {
    "iki"
} else {
    "diğer"
}

///////////////////////////////////////////////////////////////////////////

fun arrayOfTwos(size: Int): IntArray {
    return IntArray(size).apply { fill(2) }
}

fun giveMeFive() = 5
///////////////////////////////////////////////////////////////////////////

class Robot {
    fun powerOn() {
        println("Robot güçlendirildi.")
    }

    fun powerOff() {
        println("Robot gücü kesildi.")
    }

    fun rotate(degrees: Double) {
        println("Robot $degrees derece döndü.")
    }

    fun move(steps: Double) {
        println("Robot $steps adım ilerledi.")
    }
}

class Rectangle {
    var width: Int = 0
    var height: Int = 0
    var color: Long = 0
}
//////////////////////////////////////////////////////////

inline fun <reified T> printType(value: T) {
    val typeName = T::class.simpleName
    println("Değerin türü: $typeName")
}

//////////////////////////////////////////////////////////////

fun printWelcomeMessage(name: String = "Dostum", greeting: String = "Hoş geldin") {
    println("$greeting, $name!")
}

// Infix Functions
infix fun Int.multiplyBy(x: Int): Int = this * x

// Operator Functions
operator fun String.times(n: Int): String = this.repeat(n)

// Functions with vararg Parameters
fun printAll(vararg messages: String) {
    for (m in messages) println(m)
}
////////////////////////////////////////////////////////////////////


operator fun String.get(range: IntRange): String {
    return this.substring(range)
}
///////////////////////////////////////////////////////

class MutableStack<E>(vararg items: E) {
    private val elements = items.toMutableList()

    fun push(element: E) = elements.add(element)

    fun peek(): E = elements.last()

    fun pop(): E = elements.removeAt(elements.size - 1)

    fun isEmpty() = elements.isEmpty()

    fun size() = elements.size

    override fun toString() = "MutableStack${elements.joinToString(", ", "(", ")")}"
}
/////////////////////////////////////////////////////////////////////////////////////////////////////////////

open class Animal {
    open fun makeSound() {
        println("Bir ses çıkarır!")
    }
}

class Cat : Animal() {
    override fun makeSound() {
        println("miyav miyav!")
    }
}
///////////////////////////////////////////////////////////

open class Plant(val type: String, val year: Int) {
    init {
        println("$type türündeki bitki, $year yılında dikildi.")
    }

    open fun photosynthesize() {
        println("Bitki fotosentez yapıyor.")
    }
}

class Flower(type: String, year: Int, val color: String) : Plant(type, year) {
    override fun photosynthesize() {
        println("Renkli çiçek fotosentez yapıyor ve $color renginde!")
    }

    fun bloom() {
        println("Çiçek açıyor!")
    }
}
//////////////////////////////////////////////////////////////////////////

fun cases(obj: Any) {
    when (obj) {
        "Merhaba" -> println("Selam!")
        2 -> println("İki")
        0L -> println("Sıfır long")
        "Elveda" -> println("Hoşça kal!")
        else -> println("Tanımsız")
    }
}
////////////////////////////////////////////////////////////////////////////

data class Book(val title: String, val author: String, val year: Int)

///////////////////////////////////////////////////////////////////////////////

data class Student(val name: String, val number: Int, val grade: Int)

fun main() {
    println("Sayfa 9, Merhaba Dünya! Ben Yiğit.")
    printSum(5, 10)
    val c: Int
    c = 3

    incrementX() // x'i artırır, değeri şimdi 6 olmalı

    println("Sayfa 11 ve sonuç:")
    println("a: $a, b: $b, c: $c, PI: $PI, x: $x")

    val box = Box(4.5, 7.3)
    println("Sayfa 12 ve sonuç:")
    println("Kutunun çevresi: ${box.perimeter}")

    val myBox = Boxes(3.5, 6.0, Color("Kırmızı"))
    println("Sayfa 13 ve sonuç:")
    myBox.printDetails()

    var number = 10
    val text1 = "sayı $number"

    number = 20
    val text2 = "${text1.replace("10", "20")}, şimdi $number"

    println("Sayfa 14 ve sonuç:")
    println(text2)

    val result = maxOf(9, 5)
    println("Sayfa 15 ve sonuç:")
    println("Büyük sayı: $result")



    println("Sayfa 16 ve sonuç:")
    for (fruit in fruits) {
        println(fruit)
    }

    for (index in fruits.indices) {
        println("Öğe $index: ${fruits[index]}")
    }

    println("Sayfa 17 ve sonuç:")
    while (index < vegetables.size) {
        println("Öğe $index: ${vegetables[index]}")
        index++
    }

    println("Sayfa 18 ve sonuç:")
    println(describe(42))
    println(describe("dünya"))
    println(describe(3.14))
    println(describe(true))


    println("Sayfa 19 ve sonuç:")
    println(rangeCheck)
    println(rangeCheckList)
    println(sizeCheckList)
    println(forLoopResults)
    println(forLoopStepResults)
    println(forLoopDownToResults)

    println("Sayfa 20 ve sonuç:")
    vegetable
        .filter { it.startsWith("c") }
        .sortedBy { it }
        .map { it.toUpperCase() }
        .forEach { println(it) }

    println("Sayfa 21 ve sonuç:")
    if (text == null) {
        println("text is null")
    } else {
        println("text is not null")
    }

    val myObj = "Hello, World!"

    println("Sayfa 22 ve sonuç:")
    val length = lengthOrNull(myObj)
    if (length != null) {
        println("String'in uzunluğu: $length")
    } else {
        println("Bu bir string değil veya boş.")
    }

    val container = MyContainer()

    println("Adlandırma kuralları ve sonuç:")
    println("Container boş mu? ${container.isEmpty}")
    println(container.contentDescription)

    println("Sayfa 38 ve sonuç:")
    greet()
    greet("Ali")
    greet(salutation = "Selam", personName = "Ayşe")

    println("Sayfa 40 ve sonuç:")
    if ("info@example.com" in emailList) {
        println("info@example.com adresi listede mevcut.")
    } else {
        println("info@example.com adresi listede yok.")
    }

    if ("hello@world.com" in emailList) {
        println("hello@world.com adresi listede mevcut.")
    } else {
        println("hello@world.com adresi listede yok.")
    }

    println("Sayfa 42 ve 43 ve sonuç:")
    println("ReadOnly List:")
    readOnlyList.forEach { println(it) }
    println("ReadOnly Map:")
    readOnlyMap.forEach { (key, value) -> println("$key -> $value") }

    val animalsMap = mapOf("kedi" to "mırlak", "köpek" to "havhav", "kuş" to "cikcik")

    println("Sayfa 44 ve sonuç:")
    for ((animal, sound) in animalsMap) {
        println("$animal -> $sound")
    }

    val myLazyValue: String by lazy {
        println("Bu sadece ilk çağrıldığında yazdırılacak.")
        "Merhaba, Zürafa!"
    }

    println("Sayfa 45 ve sonuç:")
    println(myLazyValue)
    println(myLazyValue)

    val myString = "Merhaba Dünya"

    println("Sayfa 46 ve sonuç:")
    println(myString.addExclamation())


    println("Sayfa 47 ve sonuç:")
    ResourceHolder.loadResource()

    println("Sayfa 48 ve sonuç:")

    // Vehicle sınıfından nesne oluşturulamaz çünkü o soyuttur.
    // Bir araba nesnesi oluşturalım ve motoru çalıştıralım.
    val myCar = Car()
    println(myCar.startEngine())

    val folder = File("SomePath").listFiles()

    println("Sayfa 49 ve 50 ve sonuç:")
    println("Klasör boyutu: ${folder?.size ?: "klasör boş veya mevcut değil"}")

    val folderSize = folder?.size ?: run {
        val someSize = getFolderSize()
        someSize * 2
    }
    println(folderSize)

    println("Sayfa 55,56,57 ve sonuç:")
    try {
        val myMoodColor = mapMoodToColor("happy")
        println("Ruh hali rengi: $myMoodColor")
    } catch (e: IllegalArgumentException) {
        println(e.message)
    }

    println("Sayfa 58 ve sonuç:")
    println(result)


    fun giveMeFive() = 5
    println("Sayfa 59 ve 60 ve sonuç:")
    println(arrayOfTwos(5).joinToString())
    println("Ve cevap: ${giveMeFive()}")

    val myRobot = Robot().apply {
        powerOn()
        move(10.0)
        rotate(90.0)
        move(10.0)
        powerOff()
    }

    val myRectangle = Rectangle().apply {
        width = 6
        height = 8
        color = 0x00FF00
    }

    println("Sayfa 61 ve 62 ve sonuç:")
    println("Robot hareket etti.")
    println("Dikdörtgen - En: ${myRectangle.width}, Boy: ${myRectangle.height}, Renk: ${"#${myRectangle.color.toString(16)}"}")

    println("Sayfa 64 ve sonuç:")
    printType(42)
    printType("Merhaba Kotlin")
    printType(3.14)

    var message = "Kotlin Öğreniyorum"
    message = message.also {
        println("Değişkenin orijinal değeri: $it")
        it.reversed() // 'also' içinde yapılan değişiklik 'message' değişkenine atanmadığı sürece kalıcı olmaz.
    }

    println("Sayfa 65 ve sonuç:")
    println(message)

    val quote = "Küçük bir adım, büyük bir sıçrama olabilir."

    println("Sayfa 69 ve sonuç:")
    // String içerisinde belirli bir aralıktaki alt dizeyi al
    println(quote[0..15]) // "Küçük bir adım"

    val alwaysPresent: String = "Bu değişken null olamaz"
    // alwaysPresent = null // Bu satır hata verecek, bu yüzden yorum satırına alındı.

    var nullableVar: String? = "Bu değişken null olabilir"
    nullableVar = null // Bu geçerli çünkü değişken nullable olarak tanımlandı.

    var inferredNonNull = "Derleyici bu değişkeni non-null olarak varsayar"
    // inferredNonNull = null // Bu satır hata verecek, bu yüzden yorum satırına alındı.

    fun strLength(notNull: String): Int {
        return notNull.length
    }

    println("Sayfa 72 ve sonuç:")
    println(strLength(alwaysPresent))
    // println(strLength(nullableVar)) // Bu satır hata verecek, çünkü nullableVar null olabilir.
    if (nullableVar != null) {
        println(strLength(nullableVar)) // Bu güvenli bir çağrıdır.
    } else {
        println("nullableVar null değerine sahip.")
    }

    val stack = MutableStack(1, 2, 3)

    println("Sayfa 75 ve sonuç:")
    println(stack)
    stack.push(4)
    println(stack)
    println("En üstteki eleman: ${stack.peek()}")
    println("Çıkarılan eleman: ${stack.pop()}")
    println(stack)
    println("Yığın boş mu? ${stack.isEmpty()}")
    println("Yığın boyutu: ${stack.size()}")


    val animal: Animal = Cat()
    println("Sayfa 77 ve sonuç:")
    animal.makeSound()

    val plant = Flower("Gül", 2021, "kırmızı")
    println("Sayfa 79 ve sonuç:")
    plant.photosynthesize()
    plant.bloom()


    cases("Merhaba")
    cases(2)
    cases(0L)
    cases("Elveda")

    println("Sayfa 80 ve sonuç:")

    val fruits = listOf("elma", "muz", "çilek")

    println("Sayfa 82 ve sonuç:")
    for (fruit in fruits) {
        println("Hmm, $fruit çok lezzetli!")
    }

    var cookiesEaten = 0

    fun eatCookie() {
        println("Bir kurabiye yedim!")
    }

    println("Sayfa 83 ve sonuç:")
    while (cookiesEaten < 3) {
        eatCookie()
        cookiesEaten++
    }

    var applesEaten = 0
    var applesPicked = 0

    fun pickApple() {
        println("Bir elma topladım!")
    }

    println("Sayfa 84 ve sonuç:")
    while (applesEaten < 3) {
        println("Bir elma yedim.")
        applesEaten++
    }

    do {
        pickApple()
        applesPicked++
    } while (applesPicked < applesEaten)

    val y = 7
    if (y in 3..7) {
        println("y 3 ile 7 arasında")
    }

    println()

    if (y !in 8..10) {
        println("y 8 ile 10 arasında değil")
    }

    println("Sayfa 87 ve sonuç:")

    val p: Int = 3
    val l: Int = 3
    val j: Int = 7

    println(p == l) // true
    println(p == j) // false

    val k: Int = 3
    val lCopy: Int = k
    val kNullable: Int? = k

    println(k === lCopy) // true - Kotlin'de eşit değerler için '===' referans eşitliğini kontrol etse bile
    // primitif türler için aynı referansı kullanır ve bu yüzden true sonucu verir.
    println(k === kNullable) // true - 'kNullable' nullable olsa da, burada aynı referansı gösterir,
    // bu nedenle referans karşılaştırması true döner.

    println("Sayfa 88 ve sonuç:")

    val book1 = Book("Kotlin Temelleri", "Ali Yılmaz", 2021)
    val book2 = Book("Kotlin Temelleri", "Ali Yılmaz", 2021)

    println("equals(): ${book1 == book2}") // true, çünkü data class otomatik olarak equals() üretir

    println("hashCode(): ${book1.hashCode()} - ${book2.hashCode()}") // aynı hash kodunu üretir

    println("toString(): $book1") // Book(title=Kotlin Temelleri, author=Ali Yılmaz, year=2021) formatında çıktı verir

    val copyBook = book1.copy(year = 2022)
    println("copy(): $copyBook") // Book(title=Kotlin Temelleri, author=Ali Yılmaz, year=2022) formatında çıktı verir

    println("Sayfa 91 ve sonuç:")

    val student1 = Student("Ege", 101, 88)

    // componentN() fonksiyonları ile destructuring kullanımı
    val (studentName, studentNumber, studentGrade) = student1

    println("Öğrenci Adı: $studentName")
    println("Öğrenci Numarası: $studentNumber")
    println("Öğrenci Notu: $studentGrade")

    println("Sayfa 92 ve sonuç:")







}

