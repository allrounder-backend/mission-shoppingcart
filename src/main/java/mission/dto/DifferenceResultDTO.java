package mission.dto;

public record DifferenceResultDTO(
        int difference,
        int expectedPrice,
        boolean overBudget
) {}
