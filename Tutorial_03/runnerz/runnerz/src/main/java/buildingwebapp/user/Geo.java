package buildingwebapp.user;

// This record represents geographic coordinates (longitude and latitude) used for location-based features and mapping.

public record Geo(
        Double lng,  // Longitude coordinate (east-west position, ranges from -180 to +180 degrees)
        Double lat   // Latitude coordinate (north-south position, ranges from -90 to +90 degrees)
) {
}