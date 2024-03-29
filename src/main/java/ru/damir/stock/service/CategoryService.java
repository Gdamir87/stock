package ru.damir.stock.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.damir.stock.dto.CategoryDto;
import ru.damir.stock.dto.StatusResponse;
import ru.damir.stock.entity.Category;
import ru.damir.stock.exception.CategoryNotExistException;
import ru.damir.stock.repository.CategoryRepository;
import ru.damir.stock.utils.CategoryMapper;

import java.util.List;

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
    public StatusResponse delete(Long id) {
        categoryRepository.deleteById(id);
        return new StatusResponse("Роль успешно удалена");
    }

    @Transactional
    public StatusResponse deleteAll() {
        categoryRepository.deleteAll();
        return new StatusResponse("Все роли успешно удалены");
    }
}
