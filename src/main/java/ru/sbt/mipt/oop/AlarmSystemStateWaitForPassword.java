package ru.sbt.mipt.oop;

public class AlarmSystemStateWaitForPassword implements AlarmSystemState {
    private final AlarmSystem alarmSystem;

    public AlarmSystemStateWaitForPassword(AlarmSystem system) {
        alarmSystem = system;
        System.out.println("новое состояние охранной системы:Ожидает ввода пароля");
    }

    @Override
    public AlarmSystemStates getState() {
        return AlarmSystemStates.WAIT_FOR_PASSWORD;
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
        //command handlers are disabled here
        alarmSystem.getEventObserver().handleEvent(event);
    }

    @Override
    public void enterPassword(String password) {
        if (alarmSystem.isPasswordCorrect(password)) {
            alarmSystem.setAlarmSystemState(new AlarmSystemStateOff(alarmSystem));
            alarmSystem.getEventObserver().changeCommandHandlersStatus(true);
            //now handlers are able to send commands again
        } else {
            alarmSystem.setAlarmSystemState(new AlarmSystemStateAlarm(alarmSystem));
        }
    }
}
