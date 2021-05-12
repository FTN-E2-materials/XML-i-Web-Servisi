package repository

import (
	"fmt"
	"kitchen-service/model"

	"github.com/google/uuid"
	"gorm.io/gorm"
)

type TicketRepository struct {
	Database *gorm.DB
}

func (repo *TicketRepository) ExistsById(ticketID string) bool {

	if err := repo.Database.First(&model.Ticket{}, "id = ?", ticketID).Error; err != nil {
		return false
	}
	return true
}

func (repo *TicketRepository) FindById(ticketID string) *model.Ticket {
	ticket := &model.Ticket{}
	repo.Database.First(&ticket, "id = ?", ticketID)
	return ticket
}

func (repo *TicketRepository) CreateTicket(ticket *model.Ticket) error {
	result := repo.Database.Create(ticket)
	fmt.Println(result.RowsAffected)
	if result.RowsAffected == 0 {
		return fmt.Errorf("ticket not created")
	}
	fmt.Println("Ticket Created")
	return nil
}

func (repo *TicketRepository) UpdateTicket(ticketId uuid.UUID, status model.TicketState) error {
	result := repo.Database.Model(&model.Ticket{}).Where("id = ?", ticketId).Update("ticket_state", status)
	//TODO convert to logs
	fmt.Println(result.RowsAffected)
	fmt.Println("updating")
	return nil
}
