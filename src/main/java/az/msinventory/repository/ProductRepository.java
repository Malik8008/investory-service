package az.msinventory.repository;

import az.msinventory.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product,Long> {
    Optional<Product> findByIdAndIsDeletedFalse(Long id);
    List<Product> findAllByIsDeletedFalse();
}
