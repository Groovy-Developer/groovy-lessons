package org.example

class PerfectNumber {
    def isPerfect(number) {
        if (number <= 0) return false
        def sum = 0
        for (divisor in 1..<number) {
            if (number % divisor == 0)
                sum += divisor
        }
        if (sum == number) return true
        return false
    }
}
