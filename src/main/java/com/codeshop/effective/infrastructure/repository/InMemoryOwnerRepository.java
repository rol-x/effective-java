package com.codeshop.effective.infrastructure.repository;

import com.codeshop.effective.domain.model.Owner;
import com.codeshop.effective.domain.repository.OwnerRepository;
import com.codeshop.effective.infrastructure.io.FileStorage;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.WeakHashMap;

@Repository
@RequiredArgsConstructor
public class InMemoryOwnerRepository implements OwnerRepository {
    private final FileStorage fileStorage;

    // 7. Usuwanie niepotrzebnych referencji do obiektów
    private Map<UUID, Owner> owners = new WeakHashMap<>();

    @Override
    public List<Owner> findAll() {
        return new ArrayList<>(owners.values());
    }

    @Override
    public Optional<Owner> findById(UUID id) {
        return Optional.ofNullable(owners.get(id));
    }

    @Override
    public Owner save(Owner owner) {
        owners.put(owner.getId(), owner);
        return owner;
    }

    public void saveToFile(String fileName) {
        fileStorage.save(owners, fileName);
    }

    public void loadFromFile(String fileName) {
        owners = fileStorage.load(fileName, Owner.class);
    }
}
