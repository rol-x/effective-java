package com.codeshop.effective.infrastructure.repository;

import com.codeshop.effective.domain.model.Cat;
import com.codeshop.effective.domain.repository.CatRepository;
import com.codeshop.effective.infrastructure.io.FileStorage;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.WeakHashMap;

@Repository
@RequiredArgsConstructor
public class InMemoryCatRepository implements CatRepository {
    private final FileStorage fileStorage;

    // 7. Usuwanie niepotrzebnych referencji do obiektów
    private Map<UUID, Cat> cats = new WeakHashMap<>();

    @Override
    public Optional<Cat> findById(UUID id) {
        return Optional.ofNullable(cats.get(id));
    }

    @Override
    public Cat save(Cat cat) {
        cats.put(cat.getId(), cat);
        return cat;
    }

    public void saveToFile(String fileName) {
        fileStorage.save(cats, fileName);
    }

    public void loadFromFile(String fileName) {
        cats = fileStorage.load(fileName);
    }
}
