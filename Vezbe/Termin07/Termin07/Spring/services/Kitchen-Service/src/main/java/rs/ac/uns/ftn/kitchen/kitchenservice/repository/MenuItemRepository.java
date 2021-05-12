package rs.ac.uns.ftn.kitchen.kitchenservice.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import rs.ac.uns.ftn.kitchen.kitchenservice.domain.MenuItem;

@Repository
public interface MenuItemRepository extends CrudRepository<MenuItem, Long> {

    boolean existsByIdAndRestaurantId(Long id, Long restaurantId);

}
