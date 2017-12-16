package ru.sbt.mipt.oop;

public class AlarmSystemStateAlarm implements AlarmSystemState {
    private final AlarmSystem alarmSystem;

    public AlarmSystemStateAlarm(AlarmSystem system) {
        alarmSystem = system;
        System.out.println("новое состояние охранной системы:Аварийная сигнализация");
    }

    @Override
    public AlarmSystemStates getState() {
        return AlarmSystemStates.ALARM;
    }

    @Override
    public void turnOn() {
        //do nothing
    }

    @Override
    public void turnOff() {
        //do nothing
    }

    @Override
    public void onEvent(Event event) {

        alarmSystem.getEventObserver().handleEvent(event);
    }

    @Override
    public void enterPassword(String password) {
        if (alarmSystem.isPasswordCorrect(password)) {
            alarmSystem.setAlarmSystemState(new AlarmSystemStateOff(alarmSystem));
            alarmSystem.getEventObserver().changeCommandHandlersStatus(true);

        }
    }
}


