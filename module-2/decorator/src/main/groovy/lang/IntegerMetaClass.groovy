package lang

class IntegerMetaClass extends DelegatingMetaClass {
    IntegerMetaClass(MetaClass metaClass) { super(metaClass) }

    IntegerMetaClass(Class theClass) { super(theClass) }
    Object invokeMethod(Object object, String name, Object[] args) {
        if (name =~ /isBiggerThan/) {
            def other = name.split(/isBiggerThan/)[1].toInteger()
            object > other
        } else {
            return super.invokeMethod(object,name, args);
        }
    }
}
