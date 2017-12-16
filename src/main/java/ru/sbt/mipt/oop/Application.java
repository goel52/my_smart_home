package ru.sbt.mipt.oop;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

public class Application {

    public static void main(String... args) throws IOException {
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("application.xml");
        EventObserver eventObserver = (EventObserver) ctx.getBean("eventObserver");
        AlarmSystem alarmSystem = new AlarmSystem("123", eventObserver);
        alarmSystem.turnOn();
        EventProducer eventProducer = new EventProducer(alarmSystem);
        eventProducer.runEventCycle();
    }

    public static void sendCommand(SensorEvent command) {
        System.out.println("Pretend we're sending commands " + command);
    }
}
