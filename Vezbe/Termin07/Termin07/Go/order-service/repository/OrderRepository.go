package repository

import (
	"fmt"

	"github.com/google/uuid"
	"github.com/veljkomaksimovic/order-service/model"
	"gorm.io/gorm"
)

type OrderRepository struct {
	Database *gorm.DB
}

func (repo *OrderRepository) CreateOrder(order *model.Order) error {
	result := repo.Database.Create(order)
	fmt.Println(result.RowsAffected)
	return nil
}

func (repo *OrderRepository) UpdateOrder(orderId uuid.UUID, status int) error {
	result := repo.Database.Model(&model.Order{}).Where("id = ?", orderId).Update("order_status", status)
	//TODO convert to logs
	fmt.Println(result.RowsAffected)
	return nil
}
