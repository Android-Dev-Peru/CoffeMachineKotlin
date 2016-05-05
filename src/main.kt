import org.testng.annotations.Test

/**
 * Created by juliogaleano on 5/4/16.
 */

class tests{

    @Test fun requestWithoutMoney() {
        val coffeeMachine = CoffeeMachine()

        val status = coffeeMachine.select(Coffee.TINTO)
        assert(status == "Insert money")
    }

    @Test fun tintoRequestSuccess() {
        val coffeeMachine = CoffeeMachine()
        coffeeMachine.insert(1000)
        coffeeMachine.insert(500)
        coffeeMachine.insert(50)

        val status = coffeeMachine.select(Coffee.TINTO)
        assert(status == "${Coffee.TINTO} delivered and your change is 0")
    }

    @Test fun expresoRequestSuccess() {
        val coffeeMachine = CoffeeMachine()
        coffeeMachine.insert(1000)
        coffeeMachine.insert(1000)
        coffeeMachine.insert(500)

        val status = coffeeMachine.select(Coffee.EXPRESO)
        assert(status == "${Coffee.EXPRESO} delivered and your change is 0")
    }

    @Test fun lateRequestSuccess() {
        val coffeeMachine = CoffeeMachine()
        coffeeMachine.insert(1000)
        coffeeMachine.insert(500)
        coffeeMachine.insert(200)
        coffeeMachine.insert(100)

        val status = coffeeMachine.select(Coffee.LATE)
        assert(status == "${Coffee.LATE} delivered and your change is 0")
    }
}

enum class Coffee(name:String, val price: Int) {
    TINTO("tinto", 1550),
    EXPRESO("Expreso", 2500),
    LATE("Late", 1800)
}

class CoffeeMachine {
    var sumCoins = 0;

    fun insert(coinValue: Int) {
        sumCoins += coinValue;
    }

    fun select(selection: Coffee): String {
        if( selection == Coffee.TINTO && sumCoins >= selection.price) {
            return "${Coffee.TINTO} delivered and your change is ${sumCoins - selection.price}"
        }
        else if( selection == Coffee.EXPRESO && sumCoins >= selection.price) {
            return "${Coffee.EXPRESO} delivered and your change is ${sumCoins - selection.price}"
        }
        else if( selection == Coffee.LATE && sumCoins >= selection.price) {
            return "${Coffee.LATE} delivered and your change is ${sumCoins - selection.price}"
        }
        else if(sumCoins==0)
            return "Insert money"
        return "";
    }


}
