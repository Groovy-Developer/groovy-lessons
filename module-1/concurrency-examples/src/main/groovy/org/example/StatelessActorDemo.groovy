package org.example

import groovyx.gpars.actor.DynamicDispatchActor

import static groovyx.gpars.actor.Actors.actor

def decryptor = actor {
    loop {
        react { message ->
            if (message instanceof String) reply message.reverse()
            else stop()
        }
    }
}

def console = actor {
    decryptor.send 'lellarap si yvoorG'
    react {
        println 'Decrypted message: ' + it
        decryptor.send false
    }
}

[decryptor, console]*.join()


final MyStatelessActor actor = new MyStatelessActor();
actor.start();
actor.send("Hello");
actor.sendAndWait(10);

actor.sendAndContinue(10.0) {
    System.out.println("Received a reply " + s);
}

class MyStatelessActor extends DynamicDispatchActor {
    void onMessage(final String msg) {
        System.out.println("Received " + msg);
        replyIfExists("Thank you");
    }

    void onMessage(final Integer msg) {
        System.out.println("Received a number " + msg);
        replyIfExists("Thank you");
    }

    void onMessage(final Object msg) {
        System.out.println("Received an object " + msg);
        replyIfExists("Thank you");
    }
}
