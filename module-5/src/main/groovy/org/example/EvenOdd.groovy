package org.example

import java.util.logging.Logger

class EvenOdd {
    static final LOGGER = Logger.getLogger('EvenOdd')

    def isEven(number){
        if (number % 2 == 0){
            LOGGER.finer "$number is even"
            return true
        }
        LOGGER.finer "$number is odd"
        return false
    }
}
