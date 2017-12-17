package ru.sbt.mipt.oop;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import static ru.sbt.mipt.oop.EntityType.*;

public class SmartHome implements Actionable {
    Collection<Room> rooms;

    public SmartHome() {
        rooms = new ArrayList<>();
    }

    public SmartHome(Collection<Room> rooms) {
        this.rooms = rooms;
    }

    public void addRoom(Room room) {
        rooms.add(room);
    }

    public Collection<Room> getRooms() {
        return rooms;
    }
    public Iterator getRoomsIterator() {
        return rooms.iterator();
    }

    @Override
    public void executeAction( Action action) {
        for (Room room : rooms) {
            room.executeAction(action);
        }
        action.execute(this);
    }
}
