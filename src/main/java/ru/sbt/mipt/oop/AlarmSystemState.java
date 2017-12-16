package ru.sbt.mipt.oop;

public interface AlarmSystemState {
    AlarmSystemStates getState();

    void turnOn();

    void turnOff();

    void onEvent(Event event);

    void enterPassword(String password);
}