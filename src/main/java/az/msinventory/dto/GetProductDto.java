package az.msinventory.dto;

public record GetProductDto(
        Long id,
        String name,
        Integer quantity) {
}
