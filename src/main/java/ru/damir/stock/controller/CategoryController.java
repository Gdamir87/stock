package ru.damir.stock.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.damir.stock.dto.CategoryDto;
import ru.damir.stock.dto.StatusResponse;
import ru.damir.stock.service.CategoryService;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/categories")
public class CategoryController {
    private final CategoryService categoryService;

    /**
     * Создание новой категории
     * @param categoryDto Новая категория
     * @return categoryDto
     */
    @PostMapping("/create")
    public CategoryDto create(@Valid @RequestBody CategoryDto categoryDto) {
        log.info("[API] Request to create new category {}", categoryDto);
        return categoryService.create(categoryDto);
    }

    /**
     * Получение списка всех категорий
     * @return Список всех категорий
     */
    @GetMapping("/findAll")
    public List<CategoryDto> findAll() {
        log.info("[API] Request to get all categories");
        return categoryService.getAllCategories();
    }

    /**
     * Изменение существующей категории
     * @param categoryDto данные на обновление
     * @param id Данные id для получения товара
     * @return categoryDto
     */
    @PostMapping("/{id}")
    public CategoryDto update(@PathVariable Long id, @RequestBody CategoryDto categoryDto) {
        log.info("API] Request to update category with id {}", id);
        return categoryService.update(categoryDto, id);
    }

    /**
     * Удалить категорию по id
     * @param id категории
     * @return статус
     */
    @DeleteMapping("/delete/{id}")
    public StatusResponse delete(@PathVariable Long id) {
        log.info("[API] Request to delete category with id {}", id);
        categoryService.delete(id);
        return new StatusResponse("Категория успешно удалена");
    }

    /**
     * Удаление всех категорий
     * @return статус
     */
    @DeleteMapping
    public StatusResponse deleteAll() {
        log.info("[API] Request to delete all categories");
        categoryService.deleteAll();
        return new StatusResponse("Категории успешно удалены");
    }
}
