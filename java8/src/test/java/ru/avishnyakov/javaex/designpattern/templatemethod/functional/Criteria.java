package ru.avishnyakov.javaex.designpattern.templatemethod.functional;

import ru.avishnyakov.javaex.designpattern.templatemethod.ApplicationDenied;

public interface Criteria {
    void check() throws ApplicationDenied;
}
