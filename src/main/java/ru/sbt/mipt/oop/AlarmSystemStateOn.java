package ru.sbt.mipt.oop;

public class AlarmSystemStateOn implements AlarmSystemState {

    private final AlarmSystem alarmSystem;

    public AlarmSystemStateOn(AlarmSystem alarmSystem) {
        this.alarmSystem = alarmSystem;
        System.out.println("новое состояние охранной системы:Активирована");
    }

    @Override
    public AlarmSystemStates getState() {
        return AlarmSystemStates.ON;
    }

    @Override
    public void turnOn() {
        //do nothing
    }

    @Override
    public void turnOff() {
        alarmSystem.setAlarmSystemState(new AlarmSystemStateWaitForPassword(alarmSystem));
    }

    @Override
    public void onEvent(Event event) {
        alarmSystem.setAlarmSystemState(new AlarmSystemStateWaitForPassword(alarmSystem));
        //command handlers are disabled here
        alarmSystem.getEventObserver().handleEvent(event);
    }

    @Override
    public void enterPassword(String password) {
        //do nothing
    }
}