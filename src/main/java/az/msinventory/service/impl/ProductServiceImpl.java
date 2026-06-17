package az.msinventory.service.impl;

import az.msinventory.dto.GetProductDto;
import az.msinventory.dto.PostProductDto;
import az.msinventory.dto.PutProductDto;
import az.msinventory.entity.Product;
import az.msinventory.exception.IdNotFoundException;
import az.msinventory.exception.NoEnoughException;
import az.msinventory.repository.ProductRepository;
import az.msinventory.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final ModelMapper modelMapper;

    @Override
    public GetProductDto getById(Long id) {
        Product product = productRepository.findByIdAndIsDeletedFalse(id)
                .orElseThrow(() -> new IdNotFoundException("Product with id: " + id + " not found"));
        return modelMapper.map(product, GetProductDto.class);
    }

    @Override
    public List<GetProductDto> getAll() {
        return productRepository.findAllByIsDeletedFalse()
                .stream().map(pr-> modelMapper.map(pr, GetProductDto.class)).toList();
    }

    @Override
    public GetProductDto reduceQuantity(Long id, int amount) {
        Product product = productRepository.findByIdAndIsDeletedFalse(id)
                .orElseThrow(() -> new IdNotFoundException("Product with id: " + id + " not found"));
        if (product.getQuantity() < amount) {
            throw new NoEnoughException("Not enough stock");
        }

        product.setQuantity(product.getQuantity() - amount);
        Product stockProduct = productRepository.save(product);
        return modelMapper.map(stockProduct, GetProductDto.class);
    }

    @Override
    public GetProductDto create(PostProductDto postProductDto) {
        Product product = new Product();
        product.setName(postProductDto.name());
        product.setQuantity(postProductDto.quantity());
        product.setIsDeleted(false);
        Product newProduct = productRepository.save(product);
        return modelMapper.map(newProduct, GetProductDto.class);
    }

    @Override
    public GetProductDto update(Long id, PutProductDto putProductDto) {
        Product existProduct = productRepository.findByIdAndIsDeletedFalse(id)
                .orElseThrow(() -> new IdNotFoundException("Product with id: " + id + " not found"));
        existProduct.setName(putProductDto.name());
        existProduct.setQuantity(putProductDto.quantity());
        Product updatedProduct = productRepository.save(existProduct);
        return modelMapper.map(updatedProduct, GetProductDto.class);
    }

    @Override
    public void delete(Long id) {
        Product existProduct = productRepository.findByIdAndIsDeletedFalse(id)
                .orElseThrow(() -> new IdNotFoundException("Product with id: " + id + " not found"));
        existProduct.setIsDeleted(true);
        productRepository.save(existProduct);
    }
}
