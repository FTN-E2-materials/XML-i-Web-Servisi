package repository

import (
	"kitchen-service/model"

	"gorm.io/gorm"
)

type MenuItemRepository struct {
	Database *gorm.DB
}

func (repo *MenuItemRepository) CreateMenuItem(menuItem *model.MenuItem) error {
	result := repo.Database.Create(menuItem)
	print(result.Error)
	print(result.RowsAffected)
	return nil
}

//TODO convert to error
func (repo *MenuItemRepository) ExistsByIdAndRestaurantID(id string, restaurantId string) bool {
	if err := repo.Database.First(&model.MenuItem{}, "id = ? AND restaurant_id = ?", id, restaurantId).Error; err != nil {
		return false
	}
	return true
}

func (repo *MenuItemRepository) FindById(menuId string) (model.MenuItem, error) {
	menuItem := model.MenuItem{}
	if result := repo.Database.First(&menuItem, "id = ?", menuId); result.Error != nil {
		return menuItem, result.Error
	}
	return menuItem, nil
}
