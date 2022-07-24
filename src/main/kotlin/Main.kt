val words = listOf("orange","surgery","child","injury","photo","decision",
                    "trainer","police","software","flight","recording",
                    "replacement","possibility","passion","painting",
                    "memory","meat","organization","salad","relationship")

val correctGuessed = hashSetOf<String>()
val incorrectGuessed = hashSetOf<String>()
var errors = 0

fun main() {
    /**
     * Main function for the hangman game
     */

    val selectedWord = words.random()
    runGame(selectedWord)
}

fun drawHangman(){
    // ASCII hanman pics array
    val hangmanPics = arrayListOf(
        "\n +--+\n |  |\n    |\n    |\n    |\n    |\n=========",
        "\n +--+\n |  |\n O  |\n    |\n    |\n    |\n=========",
        "\n +--+\n |  |\n O  |\n |  |\n    |\n    |\n=========",
        "\n +--+\n |  |\n O  |\n/|  |\n    |\n    |\n=========",
        "\n +--+\n |  |\n O  |\n/|\\ |\n    |\n    |\n =========",
        "\n +--+\n |  |\n O  |\n/|\\ |\n/   |\n    |\n=========",
        "\n +--+\n |  |\n O  |\n/|\\ |\n/ \\ |\n    |\n=========")

    println()
    println(hangmanPics[errors])
}

fun panel(selectedWord: String): Boolean {

    var finished = true

    drawHangman()

    println("Wrong letters: $incorrectGuessed")
    print("Lives:[${6-errors}] Errors:[$errors] Word:")

    for(i in selectedWord.indices){
        if (selectedWord[i].toString() in correctGuessed)
            print(selectedWord[i])
        else {
            finished = false
            print("_")
        }
    }

    println()
    return finished
}

fun runGame(selectedWord: String) {
    while(errors < 6) {

        if ( panel(selectedWord) ) break // if no letters left for guessing returns true and brake the loop

        print("Guess alphabet: ")
        val guess = readln()

        if(!isLetters(guess)) {
            println("incorrect input only characters a to z are accepted")
            continue
        }

        if(guess in selectedWord){
            println("Correct!")
            correctGuessed.add(guess)

        } else {
            println("No $guess in the word!")
            incorrectGuessed.add(guess)
            errors++
        }
    }

    if(errors == 6){
        drawHangman()
        println("Correct word was $selectedWord")
        println("Game over!")
    } else {
        println("Congratulations you made it!")
    }

    println("Press 1 to play agan, any other key to end")
    val selection = readln()
    if(selection.isNotEmpty() && selection.toInt() == 1){

        val newdWord = words.random()
        errors = 0
        correctGuessed.clear()
        incorrectGuessed.clear()

        runGame(newdWord)
    } else {
        println("Bye bye")
    }
}

fun isLetters(string: String): Boolean {
    return string.filter { it in 'A'..'Z' || it in 'a'..'z' }.length == string.length
}