package com.example.rupiahin

class Currency(val amount: Int) {
    fun getFormattedValue(): String {
        var result: String = ""
        var remainder: Int
        var dividen = amount

        do {
            remainder = dividen % 1000
            result = "${remainder}" + result
            dividen = dividen / 1000

            if(dividen > 0) {
                if(remainder < 100)
                    result = "0" + result
                if(remainder < 10)
                    result = "0" + result

                result = "." + result
            }
        } while (dividen > 0)

        return result
    }
}