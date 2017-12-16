package ru.sbt.mipt.oop;

public class AlarmSystemStateOff implements AlarmSystemState {
    private final AlarmSystem alarmSystem;

    public AlarmSystemStateOff(AlarmSystem alarmSystem) {
        this.alarmSystem = alarmSystem;
        System.out.println("новое состояние охранной системы:Выключена");
    }

    @Override
    public AlarmSystemStates getState() {
        return AlarmSystemStates.OFF;
    }

    @Override
    public void turnOn() {

        alarmSystem.setAlarmSystemState(new AlarmSystemStateOn(alarmSystem));
        alarmSystem.getEventObserver().changeCommandHandlersStatus(false);

    }

    @Override
    public void turnOff() {

    }

    @Override
    public void onEvent(Event event) {

        alarmSystem.getEventObserver().handleEvent(event);
    }

    @Override
    public void enterPassword(String password) {

    }
}