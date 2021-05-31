package mattingRitual;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class Entity {
    /** The name of the Entity. **/
    protected String name;

    /** The preference list contains each Entity prefered in order of preference. **/
    protected List<Entity> preferenceList;

    /** The maximum number of entity that is relevent in the waitingList.
     * The waitingList can have more elements than capacity but only the elements at index
     * below capacity are accepted
     */
    protected int capacity;

    /** The waiting list contains the Entities that wants to be accepted by this/self
     * At the end of the stableMarriage algorithm its size is <= capacity
     */
    protected List<Entity> waitingList;

    /** Create an Entity with an empty preferenceList and waitingList.
     *
     * @param name the name of this Entity
     * @param capacity the number of Entties that are accepted in the waiting list
     */
    public Entity(String name, int capacity) {
        this.name = name;
        this.preferenceList = new LinkedList<>();
        this.capacity = capacity;
        this.waitingList = new LinkedList<>();
    }

    /** Return the name of the Entity.
     *
     * @return the name of the Entity
     */
    public String getName() {
        return name;
    }

    /** Return the list of preferences of this Entity.
     *
     * @return the list of preferences
     */
    public List<Entity> getPreferenceList() {
        return preferenceList;
    }

    /** Return the capacity of the waitingList.
     *
     * @return capacity of the waitingLIst
     */
    public int getCapacity() {
        return capacity;
    }

    /** Return the waitingList.
     *
     * @return the waitingList
     */
    public List<Entity> getWaitingList() {
        return waitingList;
    }

    /** Add to the preferenceList the Entity e.
     *
     * @param e the Entity to add
     */
    public void addToPreferenceList(Entity e) {
        this.preferenceList.add(e);
    }

    /** Add to the waitingList the Entity e.
     *
     * @param e the Entity to add
     */
    public void addToWaitingList(Entity e) {
        this.waitingList.add(e);
    }

    /** Remove and return the least prefered Entity from the waitingList.
     *
     * @return the least prefered Entity of the waitingList
     */
    public Entity popWorseFromWaitingList() {
        Entity worse = waitingList.get(0);
        for (Entity e : waitingList) {
            if (preferenceList.lastIndexOf(e) > preferenceList.lastIndexOf(worse)) {
                worse = e;
            }
        }
        waitingList.remove(worse);
        return worse;

    }

    /** Return and remove the prefered Entity of the preferenceList.
     *
     * @return the most prefered Entity of the preferenceList
     */
    public Entity popPrefered() {
        return this.preferenceList.remove(0);
    }

    /** Return the representation as a String of the waitingList.
     *
     * @return the String representation
     */
    public String toStringWaitingList() {
        return(name + " | " + waitingList);

    }

    /** Decrease the capacity of the waitingList
     *
     */
    public void decreaseCapacity() {
        capacity--;
    }

    /** Increase the capacity of the waitingList
     *
     */
    public void increaseCapacity() {
        capacity++;
    }

    public boolean isAssignable() {
        return !this.preferenceList.isEmpty();
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

    @Override
    public String toString() {
        return name;
    }
}
