package model

import (
	"github.com/google/uuid"
	"gorm.io/gorm"
)

type Ticket struct {
	ID           uuid.UUID        `json:"id"`
	TicketState  TicketState      `json:"ticket_state"`
	Items        []TicketLineItem `json:"items"`
	RestaurantID uuid.UUID        `json:"restaurant_id"`
	Restaurant   Restaurant
}

func (ticket *Ticket) BeforeCreate(store *gorm.DB) error {
	if ticket.ID == uuid.Nil {
		ticket.ID = uuid.New()
	}
	return nil
}

func (ticket *Ticket) AddItem(item TicketLineItem) {
	ticket.Items = append(ticket.Items, item)
}
