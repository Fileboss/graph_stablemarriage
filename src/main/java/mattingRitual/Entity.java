package mattingRitual;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public abstract class Entity {
    private String name;
    private List<String> preferenceList;
    private int capacity;
    private List<String> waitingList;

    public Entity(String name, List<String> preferenceList, int capacity) {
        this.name = name;
        this.preferenceList = preferenceList;
        this.capacity = capacity;
        this.waitingList = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public List<String> getPreferenceList() {
        return preferenceList;
    }

    public int getCapacity() {
        return capacity;
    }

    public List<String> getWaitingList() {
        return waitingList;
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
