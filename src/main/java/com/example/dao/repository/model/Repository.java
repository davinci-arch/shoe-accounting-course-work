package com.example.dao.repository.model;

import com.example.model.Category;
import com.example.model.FootwearAbstract;
import com.example.model.types.TypeFootwear;

import java.util.List;
import java.util.Optional;

public interface Repository {

    Optional<FootwearAbstract> getFootwearById(Long id);

    List<FootwearAbstract> getFootwearByCategory(Category category);

    List<FootwearAbstract> getFootwearByType(TypeFootwear type);

    void save(FootwearAbstract footwear);

    void remove(int id);

    void update(FootwearAbstract footwear);

    FootwearAbstract getLast();
}
