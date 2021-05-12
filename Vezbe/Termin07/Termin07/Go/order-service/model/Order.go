package model

import (
	"github.com/google/uuid"
	"gorm.io/gorm"
)

type Order struct {
	ID           uuid.UUID       `json:"id"`
	RestaurantID uuid.UUID       `json:"restaurant_id"`
	ConsumerID   uuid.UUID       `json:"consumer_id"`
	OrderStatus  int             `json:"order_status_enum"`
	OrderItems   []OrderLineItem `json:"items" gorm:"foreignKey:OrderID"`
}

//TODO checnk the same thig mentioned in Consumer.go
func (order *Order) BeforeCreate(scope *gorm.DB) error {
	order.ID = uuid.New()
	return nil
}
