package repository

import (
	"fmt"
	"kitchen-service/model"

	"gorm.io/gorm"
)

type RestaurantRepository struct {
	Database *gorm.DB
}

func (repo *RestaurantRepository) ExistsById(restaurantID string) bool {

	if err := repo.Database.First(&model.Restaurant{}, "id = ?", restaurantID).Error; err != nil {
		return false
	}
	return true
}

func (repo *RestaurantRepository) FindById(restaurantID string) *model.Restaurant {

	restaurant := &model.Restaurant{}
	repo.Database.First(&restaurant, "id = ?", restaurantID)
	return restaurant
}

func (repo *RestaurantRepository) CreateRestaurant(restaurant *model.Restaurant) error {
	result := repo.Database.Create(restaurant)
	//TODO convert to logs
	print(result.Error.Error())
	fmt.Println(result.RowsAffected)
	return nil
}
