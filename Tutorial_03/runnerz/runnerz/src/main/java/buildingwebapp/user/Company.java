package buildingwebapp.user;

// This record represents company information associated with a user, typically used for business profiles or employment data.

public record Company(
        String name,        // Company name (e.g., "Acme Corporation")
        String catchPhrase, // Company slogan or motto (e.g., "Innovation at its finest")
        String bs          // Business strategy or description (often buzzword-heavy marketing text)
) {
}