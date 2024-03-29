package ru.damir.stock.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.damir.stock.dto.CategoryDto;
import ru.damir.stock.dto.ProductDto;
import ru.damir.stock.entity.Category;
import ru.damir.stock.exception.CategoryNotExistException;
import ru.damir.stock.repository.CategoryRepository;
import ru.damir.stock.utils.CategoryMapper;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class CategoryService {
    private final CategoryRepository categoryRepository;

    @Transactional
    public CategoryDto create(CategoryDto categoryDto) {
        Category savedCategory = CategoryMapper.toEntity(categoryDto);
        categoryRepository.save(savedCategory);
        return CategoryMapper.toDto(savedCategory);
    }

    /**
     * Получить категорию по названию<br>
     *
     * @param productDto Данные для поиска из запроса
     */
    @Transactional
    public CategoryDto find(ProductDto productDto) {
        Optional<Category> categoryOptional = categoryRepository.findByName(productDto.getCategoryName());
        if (categoryOptional.isEmpty()) {
            log.error("Category {} doesn't exist", productDto.getCategoryName());
            throw new CategoryNotExistException("Категории с таким названием не существует");
        }
        return CategoryMapper.toDto(categoryOptional.get());
    }

    @Transactional
    public List<CategoryDto> getAllCategories() {
        Iterable<Category> allRoles = categoryRepository.findAll();
        return CategoryMapper.toDtoList(allRoles);
    }

    @Transactional
    public CategoryDto update(CategoryDto categoryDto, Long id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(()
                        -> new CategoryNotExistException("Id %d of category is not found".formatted(id)));

        category.setName(categoryDto.getName());
        categoryRepository.save(category);
        return CategoryMapper.toDto(category);
    }

    @Transactional
    public void delete(Long id) {
        categoryRepository.deleteById(id);
        log.info("Product with id {} was deleted", id);
    }

    @Transactional
    public void deleteAll() {
        categoryRepository.deleteAll();
        log.info("All categories was deleted");
    }
}
