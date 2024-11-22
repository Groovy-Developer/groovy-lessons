package org.example

class CCApp {
    def isApproved(user){
        def  score = new CreditHistory().getCreditScore(user.ssn)
        if (score > 600)
            return true
        return false
    }

    def isApproved2(creditHistory, user){
        def  score = creditHistory.getCreditScore(user.ssn)
        if (score > 600)
            return true
        return false
    }
}
