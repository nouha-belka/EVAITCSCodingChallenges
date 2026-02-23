package com.evaitcs.oopadvanced;

import java.io.Serializable;

/**
 * ============================================================================
 * CLASS: Address
 * TOPIC: Composition — This class is a COMPONENT used inside Company/Employee
 * ============================================================================
 *
 * COMPOSITION ("has-a" relationship):
 * A Company "has-a" Address. An Employee "has-a" Address.
 * Address is an independent class — it can exist on its own.
 * But when used inside another class, that's composition!
 *
 * Also implements Serializable so it can be saved to files.
 *
 * INTERVIEW TIP:
 * "Composition models 'has-a' relationships. I prefer composition over
 *  inheritance because it's more flexible — you can change components
 *  at runtime and avoid tight coupling."
 * ============================================================================
 */
public class Address implements Serializable {

    // Serialization version ID — used to verify class compatibility when
    // deserializing. If you change the class structure, update this number!
    private static final long serialVersionUID = 1L;

    // =========================================================================
    // TODO 1: Declare private fields:
    //   - street (String)
    //   - city (String)
    //   - state (String)
    //   - zipCode (String)
    //   - country (String)
    // =========================================================================


    // =========================================================================
    // TODO 2: Create a constructor that initializes all fields
    // Add validation: none of the fields should be null or empty
    // =========================================================================


    // =========================================================================
    // TODO 3: Create getters for all fields
    // =========================================================================


    // =========================================================================
    // TODO 4: Create setters WITH VALIDATION for street, city, state, zipCode
    // country should not have a setter (rarely changes)
    // =========================================================================


    /**
     * TODO 5: Override toString()
     * Format: "123 Main St, Springfield, IL 62701, USA"
     */
    @Override
    public String toString() {
        return ""; // Replace this line
    }

    /**
     * TODO 6: Override equals() and hashCode()
     * Two addresses are equal if ALL fields match.
     *
     * equals pattern:
     * @Override
     * public boolean equals(Object o) {
     *     if (this == o) return true;
     *     if (o == null || getClass() != o.getClass()) return false;
     *     Address address = (Address) o;
     *     return street.equals(address.street) && city.equals(address.city)
     *            && state.equals(address.state) && zipCode.equals(address.zipCode)
     *            && country.equals(address.country);
     * }
     */
    // YOUR equals() and hashCode() HERE

}

