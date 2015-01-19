package inf223;


class SemBoundedBuffer extends BoundedBuffer {
    Semaphore emptySlots, fullSlots;

    SemBoundedBuffer (int maxSize) {
        super(maxSize);




        // Initialize semaphores.

    }

    // This method must be protected against simultaneous accesses
    // from consumers. But **DO NOT CHANGE** the signature of this method.

    Object get() throws InterruptedException {
        Object value;




            // Suspend until a full slot is available

            value = super.get();




        // Release an empty slot

        return value;
    }

    // This method must be protected against simultaneous accesses
    // from producers. But **DO NOT CHANGE** the signature of this method.

    void put(Object value) throws InterruptedException {




        // Suspend until an empty slot is available

            super.put(value);




        // Release an empty slot

    }
}
