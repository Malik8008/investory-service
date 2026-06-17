package az.msinventory.dto;

import lombok.Data;

@Data
public class GetProductDto {
    Long id;
    String name;
    Integer quantity;
}
