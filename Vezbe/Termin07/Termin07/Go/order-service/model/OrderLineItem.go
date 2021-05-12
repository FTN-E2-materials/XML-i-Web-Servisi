package model

import (
	"github.com/google/uuid"
	"gorm.io/gorm"
)

type OrderLineItem struct {
	ID           uuid.UUID `json:"id"`
	MenuItemId   uuid.UUID `json:"item_id" gorm:"not null"`
	MenuItemName string    `json:"item_name"`
	Quantity     int       `json:"quantity" gorm:"not null"`
	OrderID      uuid.UUID
}

//TODO checnk the same thig mentioned in Consumer.go
func (item *OrderLineItem) BeforeCreate(scope *gorm.DB) error {
	item.ID = uuid.New()
	return nil
}
