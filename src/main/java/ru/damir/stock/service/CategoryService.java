package ru.damir.stock.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.damir.stock.dto.CategoryDto;
import ru.damir.stock.dto.ProductDto;
import ru.damir.stock.dto.StatusResponse;
import ru.damir.stock.entity.Category;
import ru.damir.stock.exception.CategoryExistException;
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
        if (categoryRepository.findByName(categoryDto.getName()).isPresent()) {
            log.error("Current product {} is exists", categoryDto);
            throw new CategoryExistException("Такой категория уже существует");
        }
        Category savedCategory = CategoryMapper.toEntity(categoryDto);
        categoryRepository.save(savedCategory);
        return CategoryMapper.toDto(savedCategory);
    }

    /**
     * Получить категорию по названию<br>
     *
     * @param productDto Данные для поиска из запроса
     */
    public Category findCategory(ProductDto productDto) {
        Optional<Category> categoryOptional = categoryRepository.findByName(productDto.getCategoryName());
        if (categoryOptional.isEmpty()) {
            log.error("Category {} doesn't exist", productDto.getCategoryName());
            throw new CategoryExistException("Категории с таким названием не существует");
        }
        return categoryOptional.get();
    }

    public List<CategoryDto> getAllCategories() {
        Iterable<Category> allRoles = categoryRepository.findAll();
        return CategoryMapper.toDtoList(allRoles);
    }

    @Transactional
    public CategoryDto update(CategoryDto categoryDto, Long id) {
        Optional<Category> categoryOptional = categoryRepository.findById(id);
        if (categoryOptional.isEmpty()) {
            log.error("Category with id {} doesn't exist", id);
            throw new CategoryExistException("Категории с таким id не существует");
        }
        Category category = categoryOptional.get();
        category.setName(categoryDto.getName());
        categoryRepository.save(category);
        return CategoryMapper.toDto(category);
    }

    @Transactional
    public StatusResponse delete(Long id) {
        categoryRepository.deleteById(id);
        log.info("Product with id {} was deleted", id);
        return new StatusResponse("Категория успешно удалена");
    }

    @Transactional
    public StatusResponse deleteAll() {
        categoryRepository.deleteAll();
        log.info("All categories was deleted");
        return new StatusResponse("Категории успешно удалены");
    }
}
