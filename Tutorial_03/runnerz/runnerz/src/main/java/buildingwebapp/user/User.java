package buildingwebapp.user;

// This record represents a complete user profile with personal information, contact details, address, and company affiliation.

public record User(
        Integer id,      // Unique identifier for the user
        String name,     // Full name of the user (e.g., "John Doe")
        String username, // Unique username for login/identification (e.g., "johndoe")
        String email,    // Email address for contact and authentication
        Address address, // Physical address with geographic coordinates
        String phone,    // Phone number for contact
        String website,  // Personal or professional website URL
        Company company  // Company/employer information
) {
}