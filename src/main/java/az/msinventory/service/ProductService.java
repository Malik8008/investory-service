package az.msinventory.service;

import az.msinventory.dto.GetProductDto;
import az.msinventory.dto.PostProductDto;
import az.msinventory.dto.PutProductDto;

import java.util.List;

public interface ProductService {
    GetProductDto getById(Long id);
    List<GetProductDto> getAll();
    GetProductDto reduceQuantity(Long id, int amount);
    GetProductDto create(PostProductDto postProductDto);
    GetProductDto update(Long id, PutProductDto putProductDto);
    void delete(Long id);
}
