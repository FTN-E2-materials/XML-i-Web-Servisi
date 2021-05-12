package model

import (
	"github.com/google/uuid"
	"gorm.io/gorm"
)

type Restaurant struct {
	ID        uuid.UUID  `json:"id"`
	Name      string     `json:"name" gorm:"unique;not null"`
	MenuItems []MenuItem `json:"menu_items"`
}

func (restaurant *Restaurant) BeforeCreate(scope *gorm.DB) error {
	restaurant.ID = uuid.New()
	return nil
}
