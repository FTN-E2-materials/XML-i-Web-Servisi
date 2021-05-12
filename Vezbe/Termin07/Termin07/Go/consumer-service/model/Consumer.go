package model

import (
	"github.com/google/uuid"
	"gorm.io/gorm"
)

type Consumer struct {
	ID       uuid.UUID `json:"id"`
	Email    string    `json:"email" gorm:"unique;not null"`
	Password string    `json:"password" gorm:"not null"`
	Name     string    `json:"name" gorm:"not null"`
	Surname  string    `json:"surname" gorm:"not null"`
}

//TODO check if needs to be done only for the first insert, not modifications
func (consumer *Consumer) BeforeCreate(scope *gorm.DB) error {
	consumer.ID = uuid.New()
	return nil
}
