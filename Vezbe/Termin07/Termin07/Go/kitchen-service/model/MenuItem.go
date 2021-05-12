package model

import (
	"github.com/google/uuid"
	"gorm.io/gorm"
)

type MenuItem struct {
	ID           uuid.UUID `json:"id"`
	Name         string    `json:"name" gorm:"unique;not null"`
	RestaurantID uuid.UUID `json:"restaurant"`
	Restaurant   Restaurant
}

func (menuItem *MenuItem) BeforeCreate(scope *gorm.DB) error {
	menuItem.ID = uuid.New()
	return nil
}
