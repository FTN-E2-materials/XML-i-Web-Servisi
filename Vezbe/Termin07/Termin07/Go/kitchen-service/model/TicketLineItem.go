package model

import (
	"github.com/google/uuid"
	"gorm.io/gorm"
)

type TicketLineItem struct {
	ID         uuid.UUID `json:"id"`
	MenuItemID uuid.UUID `json:"item" gorm:"not null"`
	//TODO ovo bi mozda trebalo ubaciti u dto
	MenuItem MenuItem
	Quantity int       `json:"quantity" gorm:"not null"`
	TicketID uuid.UUID `json:"ticket_id" gorm:"not null"`
}

func (ticketLineItem *TicketLineItem) BeforeCreate(scope *gorm.DB) error {
	ticketLineItem.ID = uuid.New()
	return nil
}
