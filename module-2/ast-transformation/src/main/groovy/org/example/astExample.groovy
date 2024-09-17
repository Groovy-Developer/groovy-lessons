package org.example.ast

class Tree {
}

class Leaf {
    def a
}

@Newify([Tree, Leaf])
class TreeBuilder {
    Tree tree = Tree.new(Leaf.new('A'), Leaf.new('C'), Leaf.new('B'))
}

@Loggable
def test() {
    def LOG = '123'
}
