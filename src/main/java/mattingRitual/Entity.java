package mattingRitual;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public abstract class Entity {
    protected String name;
    protected List<Entity> preferenceList;
    protected int capacity;
    protected List<Entity> waitingList;

    public Entity(String name, int capacity) {
        this.name = name;
        this.preferenceList = new LinkedList<>();
        this.capacity = capacity;
        this.waitingList = new LinkedList<>();
    }

    public String getName() {
        return name;
    }

    public List<Entity> getPreferenceList() {
        return preferenceList;
    }

    public int getCapacity() {
        return capacity;
    }

    public List<Entity> getWaitingList() {
        return waitingList;
    }

    public void addToPreferenceList(Entity e) {
        this.preferenceList.add(e);
    }

    public void addToWaitingList(Entity e) {
        this.waitingList.add(e);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Entity entity = (Entity) o;
        return name.equals(entity.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}