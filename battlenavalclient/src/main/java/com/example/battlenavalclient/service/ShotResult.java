// This enum represents the possible results of a shot in a naval battle system
package com.example.battlenavalclient.service;

// Enum named ShotResult with four possible values: MISS, HIT, SUNK, and NONE
public enum ShotResult {
    // Represents a shot result where the shot missed the target
    MISS,

    // Represents a shot result where the shot successfully hit the target
    HIT,

    // Represents a shot result where the targeted ship has been sunk
    SUNK,

    // Represents a default or undefined shot result (may be used in certain cases)
    NONE
}
