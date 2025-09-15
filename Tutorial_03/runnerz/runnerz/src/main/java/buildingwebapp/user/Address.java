package buildingwebapp.user;

// This record represents a physical address with geographic coordinates, typically used as part of user profile information.

public record Address(
        String street,    // Street address (e.g., "123 Main St")
        String suite,     // Suite/apartment number (e.g., "Apt 4B")
        String city,      // City name
        String zipcode,   // Postal/ZIP code
        Geo geo          // Geographic coordinates (latitude/longitude)
) {
}