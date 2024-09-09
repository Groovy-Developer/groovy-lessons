package org.example

import groovy.swing.SwingBuilder

import javax.swing.WindowConstants
import java.awt.FlowLayout

def builder = new SwingBuilder()
frame = builder.frame(
        layout: new FlowLayout(),
        size: [800, 600],
        defaultCloseOperation: WindowConstants.EXIT_ON_CLOSE
) {
    lbl = label "test"
    btn = button (actionPerformed: {
//        btn.text = 'Clicked'
        lbl.text = 'Clicked'
    })
}

frame.show()

