package az.msinventory.controller;

import az.msinventory.dto.GetProductDto;
import az.msinventory.dto.PostProductDto;
import az.msinventory.dto.PutProductDto;
import az.msinventory.service.impl.ProductServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inventories")
@RequiredArgsConstructor
public class ProductController {
    private final ProductServiceImpl productService;

    @GetMapping("/{id}")
    public ResponseEntity<GetProductDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(productService.getById(id));
    }

    @GetMapping
    public ResponseEntity<List<GetProductDto>> getAll() {
        return ResponseEntity.ok(productService.getAll());
    }

    @PostMapping
    public ResponseEntity<GetProductDto> create(@RequestBody PostProductDto dto) {
        return ResponseEntity.ok(productService.create(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<GetProductDto> update(@PathVariable Long id,
                                                @RequestBody PutProductDto dto) {
        return ResponseEntity.ok(productService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        productService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}/reduce")
    public GetProductDto reduceQuantity(@PathVariable Long id,
                                        @RequestParam int quantity) {
        return productService.reduceQuantity(id, quantity);
    }

}
