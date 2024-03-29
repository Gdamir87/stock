package ru.damir.stock.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.damir.stock.dto.CategoryDto;
import ru.damir.stock.dto.StatusResponse;
import ru.damir.stock.entity.Category;
import ru.damir.stock.service.CategoryService;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/categories")
public class CategoryController {
    private final CategoryService categoryService;

    /**
     * Создание новой роли
     *
     * @param categoryDto Новая категория
     * @return categoryDto
     */
    @PostMapping("/create")
    public CategoryDto create(@Valid @RequestBody CategoryDto categoryDto) {
        log.info("[API] start create new role {}", categoryDto);
        return categoryService.create(categoryDto);
    }

    /**
     * Получение списка всех ролей
     *
     * @return Список всех категорий
     */
    @GetMapping("/findAll")
    public List<CategoryDto> findAll() {
        log.info("[API] start find all roles");
        return categoryService.getAllCategories();
    }

    /**
     * Изменение существующей роли
     *
     * @param categoryDto данные на обновление
     * @param id      роли
     * @return categoryDto
     */
    @PostMapping("/update/{id}")
    public CategoryDto update(@RequestBody CategoryDto categoryDto, @PathVariable Long id) {
        log.info("[API] start update role {}", categoryDto);
        return categoryService.update(categoryDto, id);
    }

    /**
     * Удаление роли по id
     *
     * @param id категории
     * @return статус
     */
    @DeleteMapping("/delete/{id}")
    public StatusResponse delete(@PathVariable Long id) {
        log.info("[API] start delete role_id {}", id);
        return categoryService.delete(id);
    }

    /**
     * Удаление всех ролей
     *
     * @return статус
     */
    @DeleteMapping
    public StatusResponse deleteAll() {
        log.info("[API] start delete all roles");
        return categoryService.deleteAll();
    }
}
